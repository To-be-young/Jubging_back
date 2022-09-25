package com.example.jubging.Controller;

import com.example.jubging.DTO.UserPageDTO;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.UserService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/userpage/plogging-status")
    public SingleResult<UserPageDTO> getUserPloggingStatus(HttpServletRequest request){
        log.info("[마이페이지 기능]");
        return responseService.getSingleResult(userService.getUserPage(request));
    }
}