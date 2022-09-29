package com.example.jubging.Service;

import com.example.jubging.DTO.PageDTO;
import com.example.jubging.DTO.PostDTO;
import com.example.jubging.DTO.PostResultDTO;
import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.Qualification;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.CommunityPostingRepository;
import com.example.jubging.Repository.QualificationRepository;
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
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityPostingRepository communityPostingRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final QualificationRepository qualificationRepository;

    @Transactional
    public CommunityPost posting(HttpServletRequest request, PostDTO postDTO){
        Long userId = jwtTokenProvider.getUserId(request);
        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);
        CommunityPost communityPost = postDTO.toEntity(user.getId());

        communityPostingRepository.save(communityPost);

        postDTO.getQualification().forEach(d->
                qualificationRepository.save( new Qualification(communityPost,d))
                );
        this.updateRecruiting();
        return communityPost;
    }

    @Transactional
    public CommunityPost delete(Long postId){
        CommunityPost communityPost = communityPostingRepository.findById(postId).orElseThrow(null);
        communityPostingRepository.delete(communityPost);
        this.updateRecruiting();
        return communityPost;
    }

    // 플로깅 모집 리스트 조회
    @Transactional
    public PageDTO getPostList(int page){
        this.updateRecruiting();
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "postId"));
        Page<CommunityPost> postPage =  communityPostingRepository.findAll(pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;
    }
    @Transactional
    public PostResultDTO getPost(Long postId) {
        this.updateRecruiting();
        CommunityPost communityPost = communityPostingRepository.findByPostId(postId).orElseThrow();
        List<Qualification> qualification= qualificationRepository.getQualification(postId);
        List<String> qualificationDTO = new ArrayList<String>();
        qualification.stream().map(h->qualificationDTO.add(h.getInstruction())).collect(Collectors.toList());
        User user = userRepository.findById(communityPost.getUserId()).orElseThrow();
        return new PostResultDTO(user.getUserId(), communityPost.getTitle(), communityPost.getContent(), qualificationDTO.size(), qualificationDTO,communityPost.getGatheringTime().toString(), communityPost.getEndingTime(), communityPost.getGatheringPlace(), communityPost.getCapacity(), communityPost.getParticipant(), communityPost.getEtc(), communityPost.getPostImage(),communityPost.isRecruiting());
    }
    @Transactional
    public PageDTO getMyPost(HttpServletRequest request,int page) {
        this.updateRecruiting();
        Long userId = jwtTokenProvider.getUserId(request);
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "postId"));
        Page<CommunityPost> postPage = communityPostingRepository.findByUserId(userId,pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;

    }

//    @Transactional
//    public void updateRecruiting(){
//        String formatedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));
//        communityPostingRepository.updateRecruiting(LocalDateTime.now());
//    }
    @Transactional
    public void updateRecruiting(){
        Page<CommunityPost> communityPostingre
    }

}