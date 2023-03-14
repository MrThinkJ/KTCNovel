package com.mrthinkj.kythucac.controller.poster;

import com.mrthinkj.kythucac.annotation.CheckBookAccount;
import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dang")
public class ManageChapterController {
    @Autowired
    ChapterService chapterService;

    @GetMapping("/novel/{bookName}/chapter/add")
    @CheckBookAccount
    public String showFormAddChapter(@ModelAttribute("userAccount") Account account,
                                     @PathVariable String bookName,
                                     Model model) {
        model.addAttribute("chapter", new Chapter());
        return "post-chapter";
    }

    @PostMapping("/novel/{bookName}/chapter/add/process")
    @CheckBookAccount
    public @ResponseBody String addChapter(@ModelAttribute("userAccount") Account account,
                                           @PathVariable String bookName,
                                           @ModelAttribute("chapter") Chapter chapter,
                                           @RequestParam("file") MultipartFile file) {
        String content;
        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            content = extractor.getText();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        String status = chapterService.addNewChapter(bookName, chapter, content);
        return status;
    }

    @GetMapping("/novel/{bookName}/chapter/{chapterIndex}/update")
    @CheckBookAccount
    public String showFormUpdateChapter(@ModelAttribute("userAccount") Account account,
                                        @PathVariable String bookName,
                                        @PathVariable String chapterIndex,
                                        Model model) {
        Chapter chapter = chapterService.getChapterByBookIdAndChapterIndexUpdate(bookName, chapterIndex, account);
        model.addAttribute("chapter", chapter);
        return "update-chapter";
    }

    @PostMapping("/novel/{bookName}/chapter/update/process")
    @CheckBookAccount
    public @ResponseBody String updateChapter(@ModelAttribute("userAccount") Account account,
                                              @PathVariable String bookName,
                                              @ModelAttribute("chapter") Chapter chapter,
                                              @RequestParam("file") MultipartFile file) {
        String content;
        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            content = extractor.getText();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        String status = chapterService.updateChapter(bookName, chapter, content);
        return status;
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbiddenException() {
        return "403";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "404";
    }


}
