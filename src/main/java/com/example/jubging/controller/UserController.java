package com.example.jubging.controller;

import com.example.jubging.model.dto.UserPageDTO;
import com.example.jubging.model.response.SingleResult;
import com.example.jubging.service.UserService;
import com.example.jubging.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping("/user-emails/exists")
    public SingleResult<Boolean> checkEmailDuplicate(@RequestParam("email") String email ){
        return responseService.getSingleResult(userService.checkEmailDuplicate(email));
        // ResponseEntity.ok(userService.checkEmailDuplicate(userId));
    }

    @PostMapping("/user-nickname/exists")
    public SingleResult<Boolean> checkNicknameDuplicate(@RequestParam("nickname") String nickname){
        return responseService.getSingleResult(userService.checkNicknameDuplicate(nickname));
    }

    @GetMapping("/user-page")
    public SingleResult<UserPageDTO> getUserPage(@RequestParam("userId") String userId){
        log.info("[마이페이지 기능]");
        log.info(userId);
        return responseService.getSingleResult(userService.getUserPage(userId));
    }
}