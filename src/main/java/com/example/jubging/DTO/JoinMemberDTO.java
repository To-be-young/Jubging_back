package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.JoinMember;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinMemberDTO {
    private Long postId;
    private Long userId;
    public JoinMember toEntity(CommunityPost communityPost,Long userId){
        return JoinMember.builder()
                .postId(communityPost)
                .userId(userId)
                .build();
    }
}
