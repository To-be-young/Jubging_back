package com.example.jubging.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPostDTO {
    private Long postId;
    private String title;
    private Long userId;
    private LocalDateTime creationDate;
    private String content;
    private LocalDateTime gatheringTime;
    private String endingTime;
    private String gatheringPlace;
    private int capacity;
    private int participant;
    private String etc;
    private String postImage;
    private boolean recruiting;
}
