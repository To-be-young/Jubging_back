package com.example.jubging.Model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(PathwayId.class)
@Table(name = "pathway")
public class Pathway {
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PloggingRecords_recordId")
    private PloggingRecords recordId;

    @Id
    private String time;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;
}
