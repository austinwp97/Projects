package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Reimbursement() {
    }

    public Reimbursement(int reimbursementId, String description, double amount, String status, User user) {
        this.reimbursementId = reimbursementId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReimbursementId() {
        return reimbursementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
