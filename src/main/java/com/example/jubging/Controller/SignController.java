package com.example.jubging.Controller;

import com.example.jubging.DTO.EmailVerifyDTO;
import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.Service.AuthService;
import com.example.jubging.Service.EmailService;
import com.example.jubging.common.Exception.EmailValidCodeException;
import com.example.jubging.DTO.Response.SingleResult;
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
//    private final SignService signService;
    private final ResponseService responseService;
    private final EmailService emailService;
    private final AuthService authService;

    /**
     * 로그인 api
     * @param loginDTO
     *      Id, Password를 key로 하는 DTO
     * @return
     *      Access Token과 Refresh Token을 발급
     */
    @PostMapping("/login")
    public SingleResult<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        log.info("[login 요청]");

        TokenDTO token = authService.loginUser(loginDTO);

//        TokenDTO token = signService.login(loginDTO);
        return responseService.getSingleResult(token);
    }

    /**
     * 회원가입 API
     * @param signUDTO
     *      userId, password, nickname 인자로 받음
     */
    @PostMapping("/signup")
    public SingleResult<String> signUp(@RequestBody @Valid SignUpDTO signUDTO) {
        log.info("[회원가입]");
//        signService.signUp(signUDTO);
        authService.signup(signUDTO);
        return responseService.getSingleResult(signUDTO.getUserId());
    }

    /**
     * 이메일 인증을 위한 코드를 보내는 API
     * @param email
     *      인증할 Email을 인자로 받음
     * @throws Exception
     */
    @PostMapping("/email")
    public SingleResult<String> sendValidationCode(@RequestBody Map<String, String> email) throws Exception{
        log.info(email.get("email"));
        emailService.sendSimpleMessage(email.get("email"));

        return responseService.getSingleResult("send mail");
    }

    /**
     * 이메일 인증 확인을 위한 API
     * @param emailVerifyDTO
     *      email, code를 인자로 받는다.
     */
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

    /**
     * 이메일 인증 코드 재발급 API
     * @param email
     *      재발급할 이메일을 parameter로 받음
     * @throws Exception
     */
    @PostMapping("/refreshCode")
    public SingleResult<?> refreshValidationCode(@RequestBody Map<String, String> email) throws Exception{
        emailService.refreshVerifyEmailCode(email.get("email"));
        return responseService.getSingleResult("reissue Email");
    }

}
