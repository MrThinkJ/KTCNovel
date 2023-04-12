package com.mrthinkj.kythucac.annotation;

import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookNotChapterAspect {

    @Around("@annotation(com.mrthinkj.kythucac.annotation.BookNotChapter)")
    public Object checkBookAccount(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String bookName = (String) args[0];
        if(bookName.equals("icon"))
            throw new NullPointerException("This book not exist.");
        return joinPoint.proceed();
    }
}
