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
public class AccountAspect {
    @Around("@annotation(com.mrthinkj.kythucac.annotation.CheckAccount)")
    public Object checkBookAccount(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Account account = (Account) args[0];
        if (account == null)
            throw new ForbiddenException("You are not logged in.");
        return joinPoint.proceed();
    }
}
