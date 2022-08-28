package com.example.jubging.Controller;

import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.DTO.SignUpDTO;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.SignService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
    private final SignService signService;
    private final ResponseService responseService;

    //    TODO
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


}
