package com.JGG.WeeklyScheduler.controller;

import com.JGG.WeeklyScheduler.entity.HibernateConnection;
import com.JGG.WeeklyScheduler.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public GridPane rootPane;
    //Controller
    private CalendarController2 calendarController2;

    public Button btnRegister;
    public Button btnCancel;
    public TextField txtVet;
    public TextField txtBranch;
    public TextField txtClient;
    public TextField txtPet;
    public TextField txtService;
    public DatePicker datePicker;
    public Spinner spinHour;
    public Spinner spinMin;
    public TextField txtMotive;
    // todo
    // These fields are for mouse dragging of window
    private double xOffset;
    private double yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initData(CalendarController2 calendarController2) {
        this.calendarController2 = calendarController2;
    }

    public void register(ActionEvent event) {
        boolean isValid = true;
/*
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String lastName = txtlastName.getText();
        String userName = txtUser.getText().toUpperCase();
        String pass = txtPass.getText();
        boolean isActive = chkActive.isSelected();
        String errorList = "No se ha podido registrar el usuario, porque se encontraron los siguientes errores:\n";

        User user = new User(id, name, lastName, userName, pass, isActive);

        try {

            if (!user.checkAvailableId()) {
                errorList += "Id ya registrado\n";
                isValid = false;
            }
            if (name.length() <= 3) {
                errorList += "El nombre no puede tener menos de tres caracteres\n";
                isValid = false;
            }
            if (lastName.length() <= 3) {
                errorList += "El apellido no puede tener menos de tres caracteres\n";
                isValid = false;
            }
            if (userName.length() != 3) {
                errorList += "El usuario se debe conformar por tres caracteres\n";
                isValid = false;
            }
            if (!user.checkAvailableUser()) {
                errorList += "Usuario ya registrado\n";
                isValid = false;
            }
            if (pass.length() < 4 || pass.length() > 11) {
                errorList += "El password debe tener entre 4 y 10 caracteres\n";
                isValid = false;
            }
            if (isValid) {
                // TODO test 20200810... Before user.addUser();
                user.createUser();
                addPicture(user);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(errorList);
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
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


    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
