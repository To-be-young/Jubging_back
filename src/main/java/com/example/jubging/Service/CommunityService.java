package com.example.jubging.Service;

import com.example.jubging.DTO.PageDTO;
import com.example.jubging.DTO.PostDTO;
import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.CommunityPostingRepository;
import com.example.jubging.Repository.UserRepository;
import com.example.jubging.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityPostingRepository communityPostingRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void posting(HttpServletRequest request, PostDTO postDTO){
        Long userId = jwtTokenProvider.getUserId(request);
        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);
        CommunityPost communityPost = postDTO.toEntity(user.getId());
        communityPostingRepository.save(communityPost);
    }

    @Transactional
    public CommunityPost delete(Long postId){
        CommunityPost communityPost = communityPostingRepository.findById(postId).orElseThrow(null);
        communityPostingRepository.delete(communityPost);
        return communityPost;
    }

    // 플로깅 모집 리스트 조회
    @Transactional
    public PageDTO getPostList(int page){
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "postId"));
        Page<CommunityPost> postPage =  communityPostingRepository.findAll(pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;
    }

    public CommunityPost getPost(Long postId) {
        return communityPostingRepository.findByPostId(postId).orElseThrow();
    }

    public PageDTO getMyPost(HttpServletRequest request,int page) {
        Long userId = jwtTokenProvider.getUserId(request);
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "postId"));
        Page<CommunityPost> postPage = communityPostingRepository.findByUserId(userId,pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;

    }
}