package com.JGG.WeeklyScheduler.controller;

import com.JGG.WeeklyScheduler.dao.AppointmentDAO;
import com.JGG.WeeklyScheduler.dao.UserDAO;
import com.JGG.WeeklyScheduler.entity.Appointment;
import com.JGG.WeeklyScheduler.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ResourceBundle;

public class ManageAppointmentController implements Initializable {
    public GridPane rootPane;
    public ComboBox<String> cboVet;
    public ComboBox<String> cboBranch;
    //Controller
    private CalendarController2 calendarController2;

    public Button btnRegister;
    public Button btnCancel;
    public TextField txtClient;
    public TextField txtPet;
    public TextField txtService;
    public DatePicker datePicker;
    public Spinner<Integer> spinHour;
    public Spinner<Integer> spinMin;
    public TextArea txtMotive;
    // todo
    // These fields are for mouse dragging of window


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setValue(Model.getInstance().AppointmentDate);
        spinHour.getValueFactory().setValue(Model.getInstance().AppontimenTime.getHour());

        // fill the comboboxex
        try {
            ObservableList<String> userNames = new UserDAO().getUsersNames();
            ObservableList<String> branchNames = FXCollections.observableArrayList("Urban", "Habror", "Montejo");
            this.cboVet.setItems(userNames);
            this.cboBranch.setItems(branchNames);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(Model.getInstance().appointmentToEdit!=null){
            cboVet.getSelectionModel().select(Model.getInstance().appointmentToEdit.getVeterinarian());
            txtPet.setText(Model.getInstance().appointmentToEdit.getPetName());
            txtClient.setText(Model.getInstance().appointmentToEdit.getClientName());
            cboBranch.getSelectionModel().select(Model.getInstance().appointmentToEdit.getBranch());
            txtService.setText(Model.getInstance().appointmentToEdit.getClientName());
            txtMotive.setText(Model.getInstance().appointmentToEdit.getMotive());
            datePicker.setValue(Model.getInstance().appointmentToEdit.getDate());
            spinHour.getValueFactory().setValue(Model.getInstance().appointmentToEdit.getTime().getHour());
            spinMin.getValueFactory().setValue(Model.getInstance().appointmentToEdit.getTime().getMinute());

        }
    }

    public void initData(CalendarController2 calendarController2) {
        this.calendarController2 = calendarController2;
    }

    public void register() {
        String veterinarian = cboVet.getSelectionModel().getSelectedItem();
        String petName = txtPet.getText();
        String clientName = txtClient.getText();
        String branch = cboBranch.getSelectionModel().getSelectedItem();
        String service = txtService.getText();
        String motive = txtMotive.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = LocalTime.of(spinHour.getValue(), spinMin.getValue());

        String errorList = "The appointment couldn't be registered, because of the following errors :\n";
        boolean isValid = true;
        if (branch.equals("")) {
            errorList += "The branch mustn't be empty\n";
            isValid = false;
        }
        if (petName.equals("")) {
            errorList += "The pet name mustn't be empty\n";
            isValid = false;
        }
        if (service.equals("")) {
            errorList += "The service mustn't be empty\n";
            isValid = false;
        }
        if (date == null) {
            errorList += "The date mustn't be empty\n";
            isValid = false;
        }
        if (time == null) {
            errorList += "The time mustn't be empty\n";
            isValid = false;
        }
        if (isValid) {
            // TODO test 20200810... Before user.addUser();
            Appointment appointment = new Appointment(veterinarian, petName, clientName, branch, service, motive, date, time);
            if(Model.getInstance().appointmentToEdit!=null){
                appointment.setId(Model.getInstance().appointmentToEdit.getId());
                new AppointmentDAO().createAppointment(appointment);
                Model.getInstance().appointmentToEdit=null;
            } else{
                appointment.setId(0);
                new AppointmentDAO().createAppointment(appointment);
            }
            calendarController2.updateSchedule();
            exit();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(errorList);
            alert.showAndWait();
        }
    }

    public void delete() {
        if(Model.getInstance().appointmentToEdit==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("It can't be deleted because the register is not created");
            alert.showAndWait();
        } else{
            new AppointmentDAO().deleteAppointment(Model.getInstance().appointmentToEdit);
            calendarController2.updateSchedule();
            exit();

        }
    }

    public void exit() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
