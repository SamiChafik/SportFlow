package com.example.sportflow.model;

public class User {
    private int id;
    private String first_name, last_name, birth_date, email, password, role, sport, speciality;

    public User() {
    }

    public User(int id, String first_name, String last_name, String birth_date, String email, String password, String role, String sport, String speciality) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sport = sport;
        this.speciality = speciality;
    }

    public User(String first_name, String last_name, String birth_date, String email, String password, String role, String sport, String speciality) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.email = email;
        this.password = password;
        this.role = role;
        this.sport = sport;
        this.speciality = speciality;
    }

    public User(int id, String last_name, String first_name, String birth_date, String email, String password, String role) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
