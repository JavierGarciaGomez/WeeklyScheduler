package com.JGG.WeeklyScheduler.controller;

import com.JGG.WeeklyScheduler.dao.AppointmentDAO;
import com.JGG.WeeklyScheduler.dao.UserDAO;
import com.JGG.WeeklyScheduler.entity.Appointment;
import com.JGG.WeeklyScheduler.entity.User;
import com.JGG.WeeklyScheduler.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
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
    private double xOffset;
    private double yOffset;


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

    public void register(ActionEvent event) {
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

    public void checkDigits(TextField textField, String regex) {
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            change.setText(change.getText().replaceAll(regex, ""));
            return change;
        });
        textField.setTextFormatter(formatter);
    }

    public void validateNumbers(KeyEvent keyEvent) {
/*
        DecimalFormat format = new DecimalFormat("#.0");
        txtId.setTextFormatter(new TextFormatter<>(c ->
        {
            System.out.println(c.getText());
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                return c;
            }
        }));
*/
    }

    public void generateUserName() {
        /*try{
            String name = txtName.getText();
            String lastName = txtlastName.getText();
            String fullName = name + " " + lastName;
            String[] nameElements = fullName.split("\\s+");

            char firstChar = nameElements[0].charAt(0);
            char secondChar = nameElements[nameElements.length - 2].charAt(0);
            char thirdChar = nameElements[nameElements.length - 1].charAt(0);
            String userName = (firstChar + Character.toString(secondChar) + thirdChar).toUpperCase();

            txtUser.setText(userName);
        } catch (IndexOutOfBoundsException ignored){

        }*/
    }

    public void addPicture(User user) {/*
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/AddImage.fxml"));
            Parent root = fxmlLoader.load();
            AddImageController controller = fxmlLoader.getController();
            controller.initData(user);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();

            Stage thisStage = (Stage) btnCancel.getScene().getWindow();
            thisStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }


    public void exit() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
