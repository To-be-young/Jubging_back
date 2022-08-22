package com.example.jubging.Controller;

import com.example.jubging.DTO.LoginDTO;
import com.example.jubging.DTO.TokenDTO;
import com.example.jubging.DTO.UserDTO;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.Service.RecordService;
import com.example.jubging.Service.response.ResponseService;
import com.example.jubging.Service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
    private final UserRepository userRepository;
    private final SignService signService;
    private final ResponseService responseService;
    private final RecordService recordService;

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
