package com.example.jubging.Service;

import com.example.jubging.DTO.*;
import com.example.jubging.Exception.CUserNotFoundException;
import com.example.jubging.Exception.CommunityCapacityException;
import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.JoinMember;
import com.example.jubging.Model.Qualification;
import com.example.jubging.Model.User;
import com.example.jubging.Repository.CommunityPostingRepository;
import com.example.jubging.Repository.JoinMemberRepository;
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
    private final JoinMemberRepository joinMemberRepository;
    private final UserService userService;

    @Transactional
    public CommunityPost posting(HttpServletRequest request, PostDTO postDTO){
        Long userId = jwtTokenProvider.getUserId(request);
        User user = userRepository.findById(userId)
                .orElseThrow(CUserNotFoundException::new);
        CommunityPost communityPost = postDTO.toEntity(user.getId(), user.getNickname());

        communityPostingRepository.save(communityPost);

        postDTO.getQualification().forEach(d->
                qualificationRepository.save( new Qualification(communityPost,d))
                );
        return communityPost;
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
        Page<CommunityPost> postPage = communityPostingRepository.findAll(pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;
    }
    @Transactional
    public PostResultDTO getPost(Long postId) {
        CommunityPost communityPost = communityPostingRepository.findByPostId(postId).orElseThrow();
        List<Qualification> qualification= qualificationRepository.getQualification(postId);
        List<String> qualificationDTO = new ArrayList<String>();
        qualification.stream().map(h->qualificationDTO.add(h.getInstruction())).collect(Collectors.toList());
        User user = userRepository.findById(communityPost.getUserId()).orElseThrow();
        return new PostResultDTO(user.getUserId(), user.getNickname(),communityPost.getTitle(), communityPost.getContent(), qualificationDTO.size(), qualificationDTO,communityPost.getGatheringTime().toString(), communityPost.getEndingTime(), communityPost.getGatheringPlace(), communityPost.getCapacity(), communityPost.getParticipant(), communityPost.getEtc(), communityPost.getPostImage(),communityPost.isRecruiting());
    }
    @Transactional
    public PageDTO getMyPost(HttpServletRequest request,int page) {
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
    public JoinMember joinCommunity(HttpServletRequest request, Long postId){
        Long userId = jwtTokenProvider.getUserId(request);
        CommunityPost communityPost = communityPostingRepository.findByPostId(postId).orElseThrow();
        if(communityPost.getCapacity()<=communityPost.getParticipant()){
            throw new CommunityCapacityException();
        }
        communityPost.countParticipant();

        return joinMemberRepository.save(new JoinMember(communityPost,userId));
    }

    @Transactional
    public PageDTO getMyJoinCommunity(HttpServletRequest request, int page){
        Long userId = jwtTokenProvider.getUserId(request);
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "postId"));
        Page<JoinMember> postPage = joinMemberRepository.findJoinMembersByUserId(userId, pageRequest);
        PageDTO pageDTO = new PageDTO(postPage.getTotalPages(),postPage.getTotalElements(),postPage.getSize(),page,postPage.getContent());
        return pageDTO;
    }

    @Transactional
    public List<ResultJoinMemberDTO> getMyCommunityJoinMember(HttpServletRequest request, Long postId){
        Long userId = jwtTokenProvider.getUserId(request);
        if(userId!=(communityPostingRepository.findByPostId(postId).orElseThrow().getUserId())){
            throw new CUserNotFoundException();
        }
        List<JoinMember> joinMember = joinMemberRepository.findJoinMembersByPostId_PostId(postId);
        List<ResultJoinMemberDTO> joinMemberDTO= joinMember.stream().map(h->new ResultJoinMemberDTO(userRepository.findById(h.getUserId()).orElseThrow().getUserId(),userRepository.findById(h.getUserId()).orElseThrow().getNickname())).collect(Collectors.toList());
        return joinMemberDTO;
    }

}