package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "crimes")
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crime_type", nullable = false)
    private String crimeType;

    @Column(nullable = false)
    private String description;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fir_id")
    private Fir fir;

    public Crime() {

    }

    public Long getId() {
        return id;
    }

    public Fir getFir() {
        return fir;
    }

    public void setFir(Fir fir) {
        this.fir = fir;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    @Override
    public String toString() {
        return "Crime{"
                + "id=" + id
                + ", crimeType='" + crimeType + '\''
                + ", description='" + description + '\''
                + ", dateTime=" + dateTime
                + ", location='" + location + '\''
                + ", fir=" + fir
                + '}';
    }
}
