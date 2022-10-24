package com.example.jubging.Model;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", unique = true)
    private User user;
}
