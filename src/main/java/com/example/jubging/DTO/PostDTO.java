package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String userId;
    private String title;
    private String content;
    // 활동조건
    private List<String> qualification;

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


    public CommunityPost toEntity(Long userId,String nickname){
        return CommunityPost.builder()
                .userId(userId)
                .title(title) // 임시로 넣은 값 나중에 시작시간 종료시간 구현하면 그떄 변경
                .creationDate(LocalDateTime.now())
                .nickname(nickname)
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
}