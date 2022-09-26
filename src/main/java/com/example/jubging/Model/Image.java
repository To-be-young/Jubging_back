package com.example.jubging.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Embeddable
public class Image {
    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileOriName;

    @Column(nullable = false)
    private String fileUrl;}
