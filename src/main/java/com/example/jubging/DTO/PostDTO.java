package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class PostDTO {
    private String userId;
    private String title;
    private String content;
    // 활동조건
    private List<QualificationDTO> qualification;
    // 집결 시간
    private String gatheringTime;
    // 활동 시간
    private String endingTime;
    // 집결장소
    private String gatheringPlace;
    // 모집 정원
    private int capacity;
    // 참여 인원
    private int participant;
    // 기타
    private String etc;
    // 이미지
    private String postImage;
    // 모집중
    private boolean recruiting;


    public CommunityPost toEntity(Long userId){
        return CommunityPost.builder()
                .userId(userId)
                .title(title) // 임시로 넣은 값 나중에 시작시간 종료시간 구현하면 그떄 변경
                .creationDate(LocalDateTime.now())
                .content(content)
                .gatheringTime(gatheringTime)
                .capacity(capacity)
                .gatheringPlace(gatheringPlace)
                .endingTime(endingTime)
                .etc(etc)
                .postImage(postImage)
                .recruiting(true)
                .build();
    }

    public PostDTO(String userId, String title, String content, List<QualificationDTO> qualification, String gatheringTime, String endingTime, String gatheringPlace, int capacity, int participant, String etc, String postImage, boolean recruiting) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.qualification = qualification;
        this.gatheringTime = gatheringTime;
        this.endingTime = endingTime;
        this.gatheringPlace = gatheringPlace;
        this.capacity = capacity;
        this.participant = participant;
        this.etc = etc;
        this.postImage = postImage;
        this.recruiting = recruiting;
    }
}