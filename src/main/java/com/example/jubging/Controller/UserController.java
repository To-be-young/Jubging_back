package com.example.jubging.Controller;

import com.example.jubging.DTO.EditUserInfoDTO;
import com.example.jubging.DTO.user.UserInfoDTO;
import com.example.jubging.DTO.user.UserPageDTO;
import com.example.jubging.DTO.Response.SingleResult;
import com.example.jubging.Service.UserService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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


    @PostMapping("/edit-user")
    public SingleResult<String> editUserInfo(HttpServletRequest request, @RequestBody EditUserInfoDTO editUserInfoDTO){
        userService.editUserInfo(request, editUserInfoDTO);
        return responseService.getSingleResult("업데이트 되었습니다.");
    }

    /**
     * 유저 정보 불러오기
     * @param request
     * @return
     */
    @GetMapping("/get-user-info")
    public SingleResult<UserInfoDTO> getUserInfo(HttpServletRequest request){
        return responseService.getSingleResult(userService.getUserInfo(request));
    }

    @GetMapping("/get-user-nick")
    public SingleResult<Map<String, String>> getUserNick(HttpServletRequest request){
        Map<String, String> result = Map.of(
                "nickname",userService.getUserNickname(request),
                "userId", userService.getUserId(request)
        );

        return responseService.getSingleResult(result);
    }
}