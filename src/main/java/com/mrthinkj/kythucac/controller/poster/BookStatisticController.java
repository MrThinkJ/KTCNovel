package com.mrthinkj.kythucac.controller.poster;

import com.mrthinkj.kythucac.annotation.CheckAccount;
import com.mrthinkj.kythucac.annotation.CheckBookAccount;
import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterPurchaseDTO;
import com.mrthinkj.kythucac.service.book.BookStatisticService;
import com.mrthinkj.kythucac.service.convert.Convert;
import com.mrthinkj.kythucac.service.file.Excel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dang")
public class BookStatisticController {
    @Autowired
    BookStatisticService bookStatisticService;
    @Autowired
    Excel excel;
    @Autowired
    Convert convert;

    @GetMapping("/novel/statistic")
    public String showStatistic(@ModelAttribute("userAccount") Account account,
                                Model model){
        model.addAttribute("bookStatisticList", bookStatisticService.getBookStatisticList(account));
        return "poster/book-statistic";
    }

    @GetMapping("/novel/statistic/{bookName}")
    @CheckBookAccount
    public String getBookStatistic(@ModelAttribute("userAccount")Account account,
                                   @PathVariable String bookName,
                                   Model model){
         bookStatisticService.getRevenueLastTenDays(bookName);
         model.addAttribute("chapterList", bookStatisticService.getLast10ChapterPurchase(bookName));
         model.addAttribute("bookName", convert.getBookName(bookName));
         model.addAttribute("bookLink", bookName);
         model.addAttribute("revenue", bookStatisticService.getRevenueLastTenDays(bookName));
         return "poster/book-detail-statistic";
    }

    @GetMapping(value = "/novel/statistic/{bookName}/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @CheckBookAccount
    public ResponseEntity<InputStreamResource> downloadExcel(@ModelAttribute("userAccount") Account account,
                                                             @PathVariable String bookName) throws IOException {
        Workbook workbook = excel.chapterPurchaseData(bookName);
        // Convert workbook to byte arr to download
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        // header
        HttpHeaders headers = new HttpHeaders();
        // Content Disposition: not display in browser
        headers.add("Content-Disposition", "attachment; filename="+bookName+".xlsx");
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbiddenException(){
        return "exception/access-denied";
    }

    @ExceptionHandler(NullPointerException.class) // new annotation
    public String handleNullPointerException(){
        return "exception/not-found";
    }
}
