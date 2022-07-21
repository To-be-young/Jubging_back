package com.example.jubging.controller;

import com.example.jubging.config.security.JwtTokenProvider;
import com.example.jubging.model.dto.UserDTO;
import com.example.jubging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
//    private final ResponseService responseService;

//    TODO
    @GetMapping("/login")
    public void login() {

    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDTO userDTO) {
        this.userService.signUp(userDTO);
        return ResponseEntity.ok().build();
    }

}
