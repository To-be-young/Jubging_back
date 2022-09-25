package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String userId;
    private String title;
    private String content;
    // 모집 날자
    private String gatheringTime;
    // 모집 정원
    private int capacity;
    // 활동조건
    private String qualification;
    // 집결장소
    private String gatheringPlace;
    // 문의
    private String inquiry;

    public CommunityPost toEntity(Long userId){
        return CommunityPost.builder()
                .userId(userId)
                .title(title) // 임시로 넣은 값 나중에 시작시간 종료시간 구현하면 그떄 변경
                .creationDate(LocalDateTime.now())
                .content(content)
                .gatheringTime(gatheringTime)
                .capacity(capacity)
                .qualification(qualification)
                .gatheringPlace(gatheringPlace)
                .inquiry(inquiry)
                .build();
    }

}
