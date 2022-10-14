package com.example.jubging.common.handler;

import com.example.jubging.common.Exception.*;
import com.example.jubging.DTO.Response.CommonResult;
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
        e.printStackTrace();
        return responseService.getFailResult(500, "Unknown error");
    }

//    회원가입 중복 아이디 가입시 발생시키는 예외
    @ExceptionHandler(DataIntegrityViolationException.class)
    public CommonResult singUpDuplicateUserIdException(Exception e){
        log.info("[회원가입 아이디 중복 오류 발생]");
        return responseService.getFailResult(1000, "[BAD REQUEST] 중복 오류발생");
    }

//    회원가입 no value
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult signUpNoValueException(Exception e){
        log.info("[회원가입시 필요한 값 없음]");
        return responseService.getFailResult(1001, "[BAD REQUEST] 필요한 값 확인해주세요");
    }

    @ExceptionHandler(EmailValidCodeException.class)
    public CommonResult notFoundValidationCode(Exception e){
        log.info("[이메일 인증 실패]");
        return responseService.getFailResult(1002, "[이메일 인증 실패] 이메일 인증 코드를 다시 확인해 주세요.");
    }

    @ExceptionHandler(CommunityCapacityException.class)
    public CommonResult communityCapacityException(Exception e){
        log.info("[가입인원초과]");
        return responseService.getFailResult(2001, "[이메일 인증 실패] 이메일 인증 코드를 다시 확인해 주세요.");
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(Exception e){
        log.info("[인증오류]");
        return responseService.getFailResult(000, "[인증오류]");
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    public CommonResult emailDuplicatedException(Exception e){
        return responseService.getFailResult(1003, "[이메일 중복 오류]");
    }

    @ExceptionHandler(NicknameDuplicaitedException.class)
    public CommonResult nicknameDuplicaitedException(Exception e){
        return responseService.getFailResult(1004, "[닉네임 중복오류]");
    }

}
