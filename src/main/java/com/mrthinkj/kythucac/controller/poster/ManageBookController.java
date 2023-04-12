package com.mrthinkj.kythucac.controller.poster;

import com.mrthinkj.kythucac.annotation.CheckAccount;
import com.mrthinkj.kythucac.annotation.CheckBookAccount;
import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Type;
import com.mrthinkj.kythucac.model.system.Transaction;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import com.mrthinkj.kythucac.service.convert.Convert;
import com.mrthinkj.kythucac.service.filter.TypeService;
import com.mrthinkj.kythucac.service.poster.PosterService;
import com.mrthinkj.kythucac.service.system.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dang")
public class ManageBookController {
    @Autowired
    TypeService typeService;
    @Autowired
    PosterService posterService;
    @Autowired
    BookService bookService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    Convert convert;
    @Autowired
    TransactionService transactionService;

    @GetMapping()
    public String getListBookByPoster(@ModelAttribute("userAccount") Account account,
                                      Model model) {
        model.addAttribute("bookList", posterService.getListBookByPoster(account));
        return "poster/manage-book";
    }

    @GetMapping("/transaction")
    public String showTransaction(@ModelAttribute("userAccount") Account account,
                                  Model model) {
        model.addAttribute("transactionList", transactionService.getListTransactionByAccount(account));
        return "poster/transaction";
    }

    @GetMapping("/novel/cash-out/{bookName}")
    @CheckBookAccount
    public String showCashOutForm(@ModelAttribute("userAccount") Account account,
                                  @PathVariable String bookName,
                                  Model model) {
        model.addAttribute("bookName", convert.getBookName(bookName));
        model.addAttribute("bookLink", bookName);
        return "poster/cash-out";
    }

    @PostMapping("/novel/cash-out/{bookName}/process")
    @CheckBookAccount
    public @ResponseBody String cashOut(@ModelAttribute("userAccount") Account account,
                                        @PathVariable String bookName,
                                        @Valid @ModelAttribute("transaction") Transaction transaction,
                                        BindingResult result) {
        if (result.hasErrors())
            return "blank";
        String status = transactionService.addNewTransaction(transaction, account, bookName);
        return status;
    }

    @GetMapping("/novel/{bookName}")
    @CheckBookAccount
    public String showBook(@ModelAttribute("userAccount") Account account,
                           @PathVariable String bookName,
                           Model model) {
        Book book = convert.findBookByName(bookName);
        List<ChapterSimple> chapterList = chapterService.getChapterSimpleListByBookName(bookName);
        model.addAttribute("book", book);
        model.addAttribute("chapterList", chapterList);
        return "poster/book-detail";
    }

    @GetMapping("/novel/add")
    public String showFormAddBook(@ModelAttribute("userAccount") Account account,
                                  Model model) {
        model.addAttribute("book", new Book());
        if (!model.containsAttribute("type"))
            model.addAttribute("type", new Type());
        List<Type> typeList = typeService.findAllType();
        model.addAttribute("typeList", typeList);
        return "poster/add-book";
    }

    @PostMapping("/novel/add/process")
    public @ResponseBody String addNewBook(@Valid @ModelAttribute("book") Book book,
                                           @ModelAttribute("userAccount") Account account,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        List<Type> typeList = book.getTypeList();
        String status = bookService.addNewBook(book, typeList, account);
        return status;
    }

    @GetMapping("/novel/update/{bookName}")
    @CheckBookAccount
    public String showFormUpdateBook(@ModelAttribute("userAccount") Account account,
                                     @PathVariable String bookName,
                                     Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute(book);
        if (!model.containsAttribute("type"))
            model.addAttribute("type", new Type());
        List<Type> typeList = typeService.findAllType();
        model.addAttribute("typeList", typeList);
        return "poster/update-book";
    }

    @PostMapping("/novel/update/{bookName}/process")
    public @ResponseBody String updateBook(@ModelAttribute("userAccount") Account account,
                                           @PathVariable String bookName,
                                           @Valid @ModelAttribute("book") Book book,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        Book oldBook = convert.findBookByName(bookName);
        List<Type> typeList = book.getTypeList();
        bookService.updateBook(book, typeList, account, oldBook);
        return "success";
    }

    @PostMapping("/novel/delete/{bookName}")
    @CheckBookAccount
    public String deleteBook(@ModelAttribute("userAccount") Account account,
                                           @PathVariable String bookName){
        Book book = bookService.getBook(bookName);
        if (book != null){
            bookService.deleteBook(book);
            return "/nguoi-dang";
        }
        return "exception/not-found";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbiddenException() {
        return "exception/access-denied";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException() {
        return "exception/not-found";
    }

}
