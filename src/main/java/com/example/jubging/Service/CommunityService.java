package com.example.jubging.Service;

import com.example.jubging.DTO.PostDTO;
import com.example.jubging.Exception.CEmailLoginFailedException;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.CommunityPostingRepository;
import com.example.jubging.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityPostingRepository communityPostingRepository;

    @Transactional
    public void posting(PostDTO postDTO){
        User user = userRepository.findByUserId(postDTO.getUserId())
                .orElseThrow(CEmailLoginFailedException::new);
        CommunityPost communityPost = postDTO.toEntity(user.getId());
        communityPostingRepository.save(communityPost);
    }

    @Transactional
    public CommunityPost delete(Long postId){
        CommunityPost communityPost = communityPostingRepository.findById(postId).orElseThrow(null);
        communityPostingRepository.delete(communityPost);
        return communityPost;
    }
}
