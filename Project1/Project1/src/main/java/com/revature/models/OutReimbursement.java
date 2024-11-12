package com.revature.models;

public class OutReimbursement {

    private int reimbursementId;
    private String description;
    private double amount;
    private String status;
    private int userId;

    public OutReimbursement() {
    }

    public OutReimbursement(int reimbursementId, String description, double amount, String status, int userId) {
        this.reimbursementId = reimbursementId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OutReimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
