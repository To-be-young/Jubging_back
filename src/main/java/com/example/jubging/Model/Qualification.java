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
@IdClass(QualificationId.class)
@Table(name = "qualification")
public class Qualification {
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CommunityPost_postId")
    private CommunityPost postId;

    @Id
    @Column(name = "instruction", nullable = false)
    private String instruction;
}
