package com.JGG.WeeklyScheduler.dao;


import com.JGG.WeeklyScheduler.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public List<Appointment> getAllApointments(){
        // Database replicator
        Appointment appointment = new Appointment("VET", "pet", "Client", "Montejo", "Consulta", "Garrapatas", LocalDate.now(), LocalTime.now());
        Appointment appointment2 = new Appointment("VET2", "pet", "Client", "Montejo", "Consulta", "Garrapatas", LocalDate.now().plusDays(2), LocalTime.now().plusHours(1));
        Appointment appointment3 = new Appointment("VET2", "pet", "Client", "Montejo", "Consulta", "Garrapatas", LocalDate.of(2020,8,11), LocalTime.of(17,0));

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        appointments.add(appointment2);
        appointments.add(appointment3);

        // Returning
        return appointments;
    }

    public List<Appointment> getAppointmenByDateTime(LocalDate localDate, LocalTime localTime){
        List<Appointment> allApointments = this.getAllApointments();
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment a:allApointments){
            if(localDate.equals(a.getDate())){
                if(localTime.getHour()==a.getTime().getHour()){
                    appointments.add(a);
                }
            }
        }
        return appointments;
    }



}
