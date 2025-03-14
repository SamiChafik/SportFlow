package com.example.sportflow.model;

public class Seance {

    private int seance_id, member_id, entraineur_id;
    private String seance_name, seance_date;

    public Seance() {}

    public Seance(int seance_id, int member_id, int entraineur_id, String seance_name, String seance_date) {
        this.seance_id = seance_id;
        this.member_id = member_id;
        this.entraineur_id = entraineur_id;
        this.seance_name = seance_name;
        this.seance_date = seance_date;
    }

    public Seance(int member_id, int entraineur_id, String seance_name, String seance_date) {
        this.member_id = member_id;
        this.entraineur_id = entraineur_id;
        this.seance_name = seance_name;
        this.seance_date = seance_date;
    }

    public Seance(String seance_name, String seance_date, int member_id, int entraineur_id) {
        this.seance_name = seance_name;
        this.seance_date = seance_date;
        this.member_id = member_id;
        this.entraineur_id = entraineur_id;
    }

    public int getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getEntraineur_id() {
        return entraineur_id;
    }

    public void setEntraineur_id(int entraineur_id) {
        this.entraineur_id = entraineur_id;
    }

    public String getSeance_name() {
        return seance_name;
    }

    public void setSeance_name(String seance_name) {
        this.seance_name = seance_name;
    }

    public String getSeance_date() {
        return seance_date;
    }

    public void setSeance_date(String seance_date) {
        this.seance_date = seance_date;
    }
}
