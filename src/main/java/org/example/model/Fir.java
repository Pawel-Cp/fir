package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fir")
public class Fir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fir_number", nullable = false, unique = true)
    private String firNumber;

    @Column(name = "date_field")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "crime_id")
    private Crime crime;

    @OneToOne(fetch = FetchType.LAZY)
    private Complaint complaint;

    public Fir() {

    }

    @PrePersist
    public void generateFirNumber() {
        this.firNumber = String.format("%05d", id);
    }

    public Long getId() {
        return id;
    }

    public String getFirNumber() {
        return firNumber;
    }

    public void setFirNumber(String firNumber) {
        this.firNumber = firNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Crime getCrime() {
        return crime;
    }

    public void setCrime(Crime crime) {
        this.crime = crime;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    @Override
    public String toString() {
        return "Fir{"
                + "id=" + id
                + ", firNumber=" + firNumber
                + ", date=" + date
                + ", crime=" + crime
                + ", complaint=" + complaint
                + '}';
    }
}
