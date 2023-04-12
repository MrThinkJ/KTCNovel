package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.Gender;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.BookshelfService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import com.mrthinkj.kythucac.service.user.AccountService;
import com.mrthinkj.kythucac.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/tai-khoan")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    BookReadService bookReadService;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    ChapterService chapterService;

    private final Path imageStorageDir;

    @Autowired
    public AccountController(@Value("${-Dimage-storage.dir}") Path imageStorageDir) {
        this.imageStorageDir = imageStorageDir;
    }
    @PostConstruct
    public void ensureDirectoryExists() throws IOException {
        if (!Files.exists(this.imageStorageDir)) {
            Files.createDirectories(this.imageStorageDir);
        }
    }

    @GetMapping("/cai-dat")
    public String showSettingForm(@ModelAttribute("userAccount") Account account,
                                  Model model){
        model.addAttribute("user", account.getUser());
        model.addAttribute("gender", Gender.getGenderFromString(account.getUser().getGender().toString()));
        return "user/setting";
    }

    @GetMapping("/nap-tien")
    public String showRechargePage(@ModelAttribute("userAccount") Account account,
                                   Model model){
        model.addAttribute("user", account.getUser());
        model.addAttribute("currency", account.getCurrency());
        return "user/recharge";
    }

    @GetMapping("/nguyen-thach")
    public String showCurrencyPage(@ModelAttribute("userAccount") Account account,
                                   @RequestParam(value = "page", required=false) Integer page,
                                   Model model){
        if (page == null)
            page = 0;
        if (page>=1)
            page-=1;
        int size = 10;
        model.addAttribute("user", account.getUser());
        model.addAttribute("gender", Gender.getGenderFromString(account.getUser().getGender().toString()));
        model.addAttribute("chapterPurchaseList", chapterService.getListChapterPurchaseByAccount(account, size, page));
        return "user/currency";
    }

    @PostMapping("/setting/update-avatar")
    public String updateAvatar(@ModelAttribute("userAccount") Account account,
                               @RequestParam("croppedImage")MultipartFile file,
                               HttpServletRequest httpServletRequest) throws IOException {
        final String fileExtension = Optional.ofNullable(file.getOriginalFilename())
                .flatMap(AccountController::getFileExtension)
                .orElse("");

        final String targetFileName = account.getId() + "." + fileExtension;
        final Path targetPath = this.imageStorageDir.resolve(targetFileName);

        try (InputStream in = file.getInputStream()) {
            try (OutputStream out = Files.newOutputStream(targetPath, StandardOpenOption.CREATE)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        }
        String imagePath = "/tai-khoan/image/"+account.getId()+".jpg";
        userService.updateAvatar(account, imagePath);
        HttpSession session = httpServletRequest.getSession();
        Account newAccount = accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        session.removeAttribute("userAccount");
        session.setAttribute("userAccount", newAccount);
        return "redirect:/tai-khoan/cai-dat";
    }

    @Value("${image.user.directory}")
    private String imageDirectory;

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        Path file = Paths.get(imageDirectory).resolve(filename);
        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(resource);
    }

    @PostMapping("/setting/update-user")
    public @ResponseBody String updateUser(@ModelAttribute("userAccount") Account account,
                             @Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             HttpServletRequest request){
        if (result.hasErrors()){
            return "error";
        }
        userService.updateUser(account, user);
        Account newAccount = accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        HttpSession session = request.getSession();
        session.removeAttribute("userAccount");
        session.setAttribute("userAccount", newAccount);
        return "success";
    }

    private static Optional<String> getFileExtension(String fileName) {
        final int indexOfLastDot = fileName.lastIndexOf('.');

        if (indexOfLastDot == -1) {
            return Optional.empty();
        } else {
            return Optional.of(fileName.substring(indexOfLastDot + 1));
        }
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/404");
    }
}
