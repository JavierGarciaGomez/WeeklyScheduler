package com.JGG.WeeklyScheduler.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String veterinarian;
    private String petName;
    private String clientName;
    private String branch;
    private String service;
    private String motive;
    private LocalDate date;
    private LocalTime time;

    public Appointment() {
    }

    public Appointment(String veterinarian, String petName, String clientName, String branch, String service, String motive, LocalDate date, LocalTime time) {
        this.veterinarian = veterinarian;
        this.petName = petName;
        this.clientName = clientName;
        this.branch = branch;
        this.service = service;
        this.motive = motive;
        this.date = date;
        this.time = time;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "veterinarian='" + veterinarian + '\'' +
                ", petName='" + petName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", branch='" + branch + '\'' +
                ", service='" + service + '\'' +
                ", motive='" + motive + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}