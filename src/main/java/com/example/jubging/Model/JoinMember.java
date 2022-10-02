package com.example.jubging.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(JoinMemberId.class)
@Table(name = "joinMember")
public class JoinMember {
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CommunityPost_PostId")
    private CommunityPost postId;

    @Id
    private Long userId;
}
