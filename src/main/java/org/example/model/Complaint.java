package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "complainant_user_id")
    private User complainantUser;

    @OneToOne(fetch = FetchType.LAZY)
    private Fir fir;

    private String contactInfo;

    private String statement;

    private LocalDate date;



    public Long getId() {
        return id;
    }

    public User getComplainantName() {
        return complainantUser;
    }

    public void setComplainantName(User complainantUser) {
        this.complainantUser = complainantUser;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Fir getFirs() {
        return fir;
    }

    public void setFir(Fir firs) {
        this.fir = firs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PENDING, RESOLVED
    }


    @Override
    public String toString() {
        return "Complaint{"
                + "id=" + id
                + ", complainantName='" + complainantUser + '\''
                + ", contactInfo='" + contactInfo + '\''
                + ", statement='" + statement + '\''
                + ", date=" + date
                + ", fir=" + fir
                + ", status=" + status
                + '}';
    }
}
