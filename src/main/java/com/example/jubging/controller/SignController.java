package com.example.jubging.controller;

import com.example.jubging.model.dto.LoginDTO;
import com.example.jubging.model.dto.TokenDTO;
import com.example.jubging.model.dto.UserDTO;
import com.example.jubging.model.response.SingleResult;
import com.example.jubging.repository.UserRepository;
import com.example.jubging.service.response.ResponseService;
import com.example.jubging.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
    private final UserRepository userRepository;
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
    public SingleResult<String> signUp(@RequestBody UserDTO userDTO) {
        log.info("[회원가입]");
        signService.signUp(userDTO);
        return responseService.getSingleResult(userDTO.getUserId());
    }

}
