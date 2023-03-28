package com.mrthinkj.kythucac.annotation;

import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookAccountAspect {
    @Autowired
    Convert convert;

    @Around("@annotation(com.mrthinkj.kythucac.annotation.CheckBookAccount)&& args(account,..)")
    public Object checkBookAccount(ProceedingJoinPoint joinPoint, Account account) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String bookName = (String) args[1];
        Book book = convert.findBookByName(bookName);
        if(book == null)
            throw new NullPointerException("This book not exist.");
        if (account == null || book.getAccount().getId() != account.getId())
            throw new ForbiddenException("You are not allowed to access this book.");
        return joinPoint.proceed();
    }
}
