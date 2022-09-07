package com.example.jubging.Controller;

import com.example.jubging.DTO.EmailVerifyDTO;
import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.Exception.EmailValidCodeException;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.EmailService;
import com.example.jubging.Service.SignService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
    private final SignService signService;
    private final ResponseService responseService;
    private final EmailService emailService;

    @PostMapping("/login")
    public SingleResult<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        log.info("[login 요청]");
        TokenDTO token = signService.login(loginDTO);
        return responseService.getSingleResult(token);
    }

    @PostMapping("/signup")
    public SingleResult<String> signUp(@RequestBody @Valid SignUpDTO signUDTO) {
        log.info("[회원가입]");
        signService.signUp(signUDTO);
        return responseService.getSingleResult(signUDTO.getUserId());
    }

    // Todo
    // send mail
    @GetMapping("/email")
    public SingleResult<String> emailAuth(@RequestBody Map<String, String> email) throws Exception{
        log.info(email.get("email"));
        emailService.sendSimpleMessage(email.get("email"));

        return responseService.getSingleResult("send mail");
    }

    @PostMapping("/verifyCode") // 이메일 인증 코드 검증
    public SingleResult<?> verifyCode(@RequestBody EmailVerifyDTO emailVerifyDTO) {
        boolean verifingCode = emailService.verifyEmailCode(emailVerifyDTO.getEmail(), emailVerifyDTO.getCode());
        log.info("인증여부: " + verifingCode);

        if(verifingCode){
            return responseService.getSingleResult("true");
        }
        else{
            throw new EmailValidCodeException();
        }
    }
}
