package com.example.jubging.DTO;

import com.example.jubging.Model.CommunityPost;
import com.example.jubging.Model.Qualification;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDTO {
    private String instruction;

    public Qualification toEntity(CommunityPost communityPost){
        return Qualification.builder()
                .postId(communityPost)
                .instruction(instruction)
                .build();
    }
}
