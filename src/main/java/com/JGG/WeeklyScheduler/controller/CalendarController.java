package com.JGG.WeeklyScheduler.controller;

import com.JGG.WeeklyScheduler.entity.Appointment;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {


    public BorderPane borderPane;
    public Label tue11;
    private LocalDate firstWeekDay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstWeekDay = this.getFirstWeekDay(LocalDate.now());
        System.out.println(firstWeekDay);
        // todo Appointment appointment = new Appointment("ANV", "pet", "client", "Montejo", "Consulta", "Garrapatas", "2020-12-08", "08:00");
        // todo tue11.setText(appointment.getVeterinarian()+"\n "+appointment.getPetName()+"\n "+appointment.getClientName()+"\n "+appointment.getService());
    }

    public CalendarController() {
    }

    private LocalDate getFirstWeekDay(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    }

    private void retrieveData(){

    }




}
