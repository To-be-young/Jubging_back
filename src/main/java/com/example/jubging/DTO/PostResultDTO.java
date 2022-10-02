package com.example.jubging.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PostResultDTO {
    private String userId;
    private String nickname;
    private String title;
    private String content;
    // 활동 조건 갯수
    private int qualificationCount;
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

    public PostResultDTO(String userId, String ninckname,String title, String content, int qualificationCount, List<String> qualification, String gatheringTime, String endingTime, String gatheringPlace, int capacity, int participant, String etc, String postImage, boolean recruiting) {
        this.userId = userId;
        this.nickname=ninckname;
        this.title = title;
        this.content = content;
        this.qualificationCount = qualificationCount;
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
