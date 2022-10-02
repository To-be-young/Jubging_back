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
public class JoinMemberId implements Serializable {
    private Long postId;
    private Long userId;

    public JoinMemberId(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
