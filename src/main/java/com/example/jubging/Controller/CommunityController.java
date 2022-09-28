package com.example.jubging.Controller;

import com.example.jubging.DTO.PageDTO;
import com.example.jubging.DTO.PostDTO;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.CommunityService;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService communityService;
    private final ResponseService responseService;
    @PostMapping("/posting")
    public SingleResult<PostDTO>post(HttpServletRequest request, @RequestBody PostDTO postDTO){
        log.info("[플로깅 모집 게시]");
        communityService.posting(request, postDTO);
        return responseService.getSingleResult(postDTO);
    }
    @PostMapping("/delete")
    public SingleResult<CommunityPost> delete(@RequestParam("postId") Long postId){

        return responseService.getSingleResult(communityService.delete(postId));
    }
    @GetMapping("/get-postList")
    public SingleResult<PageDTO> getPostList(@RequestParam(required = false,defaultValue = "0",value = "page")int page){
        log.info("[플로깅 모집 리스트]");
        return responseService.getSingleResult(communityService.getPostList(page));
    }
    @GetMapping("/get-post")
    public SingleResult<CommunityPost> getPost(@RequestParam("postId")Long postId){
        log.info("[플로깅 모집 상세조회]");
        return responseService.getSingleResult(communityService.getPost(postId));

    }
    @GetMapping("/get-myPost")
    public SingleResult<PageDTO> getMyPost(HttpServletRequest request,@RequestParam(required = false,defaultValue = "0",value = "page")int page){
        log.info("[내가 모집한 플로깅 모집 리스트]");
        return responseService.getSingleResult(communityService.getMyPost(request,page));
    }
}
