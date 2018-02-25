package com.example.agasthya.travelbooking;
public class Messages {

    private String from;
    private String name;
    private String to;

    public Messages() {
    }

    public Messages(String from, String to, String name) {
        this.from = from;
        this.name = name;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from =from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to= to;
    }
}
