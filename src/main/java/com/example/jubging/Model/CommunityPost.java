package com.example.jubging.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "communityPosting")
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    // 제목
    @Column(name = "title", nullable = false)
    private String title;
    // user 테이블 id
    @Column(name ="userId",nullable = false)
    private Long userId;
    // 작성 일자
    @Column(name ="creationDate",nullable = false)
    private LocalDateTime creationDate;
    // 한줄소개
    @Column(name = "content",nullable = false)
    private String content;
    // 활동조건
    @Column(name = "qualification",nullable = false)
    private String qualification;
    // 집결 시간
    @Column(name="gatheringTime",nullable = false)
    private String gatheringTime;
    // 종료 시간
    @Column(name="endingTime",nullable = false)
    private String endingTime;
    // 집결장소
    @Column(name = "Place",nullable = false)
    private String gatheringPlace;
    // 모집 정원
    @Column(name = "capacity",nullable = false)
    private int capacity;
    // 참여인원
    @Column(name="participant")
    private int participant;
    // 기타
    @Column(name = "etc",nullable = false)
    private String etc;
    // 이미지
    @Column(name = "postImage",nullable = false)
    private String postImage;
    // 모집중
    @Column(name = "recruiting",nullable = false)
    private boolean recruiting;


}