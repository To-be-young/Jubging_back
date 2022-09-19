package com.example.jubging.Controller;

import com.example.jubging.DTO.PostDTO;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.CommunityService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;
    private final ResponseService responseService;
    @PostMapping("/posting")
    public SingleResult<PostDTO>post(@RequestBody PostDTO postDTO){
        communityService.posting(postDTO);
        return responseService.getSingleResult(postDTO);
    }
    @PostMapping("/delete")
    public SingleResult<CommunityPost> delete(@RequestParam("postId") Long postId){

        return responseService.getSingleResult(communityService.delete(postId));
    }
}
