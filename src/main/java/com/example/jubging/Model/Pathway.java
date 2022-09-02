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

    public PloggingRecords getRecordId() {
        return recordId;
    }

    public void setRecordId(PloggingRecords recordId) {
        this.recordId = recordId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
