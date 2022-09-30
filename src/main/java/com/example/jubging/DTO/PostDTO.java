package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String userId;
    private String title;
    private String content;
    // 활동조건
    private String qualification;
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
                .qualification(qualification)
                .gatheringTime(gatheringTime)
                .capacity(capacity)
                .gatheringPlace(gatheringPlace)
                .endingTime(endingTime)
                .etc(etc)
                .postImage(postImage)
                .recruiting(true)
                .build();
    }

}