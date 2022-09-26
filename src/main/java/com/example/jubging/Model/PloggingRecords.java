package com.example.jubging.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "plogging_records")
public class PloggingRecords {
    @Id
    @Column(name="recordId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "distance", nullable = false)
    private double distance;

    @Column(name = "activity_time", nullable = false)
    private String activityTime;

    public Long getRecordId() {
        return recordId;
    }
}
