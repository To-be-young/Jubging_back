package com.example.jubging.Model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class PathwayId implements Serializable {
    private Long recordId;
    private String time;

    public PathwayId(Long recordId, String time){
        this.recordId = recordId;
        this.time = time;
    }
}
