package com.revature.models;


public class LoginDTO {




    public LoginDTO() {

    }

    public LoginDTO(int passenger_id, String first_name) {
        this.passenger_id = passenger_id;
        this.first_name = first_name;
    }

    private int passenger_id;

    private String first_name;

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "passenger_id=" + passenger_id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
