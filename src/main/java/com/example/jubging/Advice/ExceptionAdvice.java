package com.example.jubging.Advice;

import com.example.jubging.Response.CommonResult;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

//    회원가입 중복 아이디 가입시 발생시키는 예외
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult singUpOverlapUserId(Exception e){
        log.info("[회원가입 아이디 중복 오류 발생]");
        return responseService.getFailResult(500, "회원가입 중복 오류 발생");
    }
}
