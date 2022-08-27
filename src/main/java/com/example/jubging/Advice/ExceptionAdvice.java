package com.example.jubging.Advice;

import com.example.jubging.Response.CommonResult;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

//     기본 오류
    @ExceptionHandler(Exception.class)
    public CommonResult defaultException(Exception e){
        log.info(String.valueOf(e));
        return responseService.getFailResult(500, "Unknown error");
    }

//    회원가입 중복 아이디 가입시 발생시키는 예외
    @ExceptionHandler(DataIntegrityViolationException.class)
    public CommonResult singUpDuplicateUserIdException(Exception e){
        log.info("[회원가입 아이디 중복 오류 발생]");
        return responseService.getFailResult(400, "[BAD REQUEST] 회원가입 중복 오류 발생");
    }

//    회원가입 no value
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult signUpNoValueException(Exception e){
        log.info("[회원가입시 필요한 값 없음]");
        return responseService.getFailResult(400, "[BAD REQUEST] 필요한 값 확인해주세요");
    }


}
