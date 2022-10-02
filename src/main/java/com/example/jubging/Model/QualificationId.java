package com.example.jubging.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class QualificationId implements Serializable {
    private Long postId;
    private String instruction;

    public QualificationId(Long postId, String instruction) {
        this.postId = postId;
        this.instruction = instruction;
    }
}
