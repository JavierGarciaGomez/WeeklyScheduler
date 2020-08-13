package com.JGG.WeeklyScheduler.controller;

import com.JGG.WeeklyScheduler.model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

public class CalendarController2 implements Initializable {
    public BorderPane borderPane;

    public GridPane gridPane;
    public DatePicker datePicker;

    public CalendarController2() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVariables();
        initGrid();
        initWeekDaysHeaderPanes();
        loadWeekDaysHeaderLabels();
        initHoursHeader();
        initAppointmentsGrid();

        //todo
        // Initialize the grid

        //initAppointmentsGrid();

        //todo change method name

        // todo Appointment appointment = new Appointment("ANV", "pet", "client", "Montejo", "Consulta", "Garrapatas", "2020-12-08", "08:00");
        // todo tue11.setText(appointment.getVeterinarian()+"\n "+appointment.getPetName()+"\n "+appointment.getClientName()+"\n "+appointment.getService());
    }

    private void initVariables() {
        if (Model.getInstance().selectedLocalDate == null) {
            Model.getInstance().selectedLocalDate = LocalDate.now();
        }
        Model.getInstance().setMondayDate();
        Model.getInstance().setLastDayOfMonth();
        System.out.println("Monday date and lastday of month setted: " + Model.getInstance().mondayOfTheWeek + " " + Model.getInstance().lastDayOfMonth);
    }

    private void initGrid() {
        int rows = 13;
        int cols = 8;
        // setConstraints todo create a method
        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < cols; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        // todo delete
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                //Add a VBox for each cell
                VBox vBox = new VBox();
                //todo change the name and add styles
                vBox.getStyleClass().add("calendar_pane");
                // todo check if neccesary vBox.setMinWidth(wee);

                String cssLayout = "-fx-border-color: red;\n" +
                        "-fx-border-insets: 5;\n" +
                        "-fx-border-width: 3;\n" +
                        "-fx-border-style: dashed;\n";
                vBox.setStyle(cssLayout);

                vBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
                    addAppointments(vBox);
                }));
                // set vGrow to always expand or shrink
                GridPane.setVgrow(vBox, Priority.ALWAYS);
                // Add the Vbox to the grid
                gridPane.add(vBox, j, i);

            }
        }
    }

    private void initWeekDaysHeaderPanes() {
        //todo change comments and variablenames
        // loop for each day
        for (int i = 1; i < gridPane.getColumnCount(); i++) {
            StackPane stackPane = new StackPane();
            // Make panes take up equal space
            HBox.setHgrow(stackPane, Priority.ALWAYS);
            stackPane.setMaxWidth(Double.MAX_VALUE);
            // Note: After adding a label to this, it tries to resize itself..
            // So I'm setting a minimum width.
            stackPane.setMinWidth(gridPane.getPrefWidth() / 8);
            // Add it to the header
            gridPane.add(stackPane, i, 0);
            // Add weekday name
        }
        // todo delete
        gridPane.setGridLinesVisible(true);
    }

    private void loadWeekDaysHeaderLabels() {
        int dayDateLabel = Model.getInstance().mondayOfTheWeek.getDayOfMonth();
        int lastDayOfMonth = Model.getInstance().lastDayOfMonth.getDayOfMonth();
        for (int i = 1; i < gridPane.getColumnCount(); i++) {
            // retrieve pane for each grid
            StackPane dayHeader = (StackPane) getNodeFromGridPane(gridPane, i, 0);
            dayHeader.getChildren().clear();
            // todo style
            dayHeader.setStyle("-fx-backgroud-color: white");
            dayHeader.setStyle("-fx-font: 14px \"System\" ");

            //change day if gets to the lasy day of the month
            if (dayDateLabel > lastDayOfMonth) dayDateLabel = 1;
            Label lbl = new Label(Model.getInstance().weekDaysNames[i-1] + "\n" + Integer.toString(dayDateLabel));

            lbl.setStyle("-fx-text-fill:darkslategray");

            dayHeader.getChildren().add(lbl);
            dayDateLabel++;
        }

        //lblCount++;
    }


    private void initHoursHeader() {
        //todo change comments and variablenames
        // 7 days in a week
        int hours = 12;
        // Weekday names
        String[] availableHours = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
        // loop for each hour
        for (int i = 1; i < gridPane.getRowCount(); i++) {
            // Make new pane
            StackPane stackPane = new StackPane();
            // Make panes take up equal space
            // todo review styles
            stackPane.getStyleClass().add("weekday-header");
            stackPane.setStyle("-fx-backgroud-color: white");
            stackPane.setStyle("-fx-font: 14px \"System\" ");

            HBox.setHgrow(stackPane, Priority.ALWAYS);
            stackPane.setMaxWidth(Double.MAX_VALUE);
            // Note: After adding a label to this, it tries to resize itself..
            // So I'm setting a minimum width.
            stackPane.setMinWidth(gridPane.getPrefWidth() / gridPane.getRowCount());
            // Create label and add it
            Label lbl = new Label(availableHours[i-1]);
            lbl.setPadding(new Insets(5));
            lbl.setStyle("-fx-text-fill:darkslategray");
            stackPane.getChildren().add(lbl);
            // add the pane to the grid
            gridPane.add(stackPane, 0, i);
            // Add weekday name
        }
    }

    private void initAppointmentsGrid() {
        int rows = 12;
        int cols = 7;






        for (int i = 1; i < gridPane.getRowCount(); i++) {
            for (int j = 1; j < gridPane.getColumnCount(); j++) {
                //Add a VBox for each cell
                VBox vBox = new VBox();
                //todo change the name and add styles
                vBox.getStyleClass().add("calendar_pane");
                // todo check if neccesary vBox.setMinWidth(wee);
                /*vBox.setMaxWidth(daysOfWeekHeader.getPrefWidth() / 7);
                System.out.println(daysOfWeekHeader.getWidth());
                System.out.println(daysOfWeekHeader.getPrefWidth());
                System.out.println(daysOfWeekHeader.getMaxWidth());
                System.out.println(daysOfWeekHeader.getMinWidth());*/

                //todo delete label, just for testing
                Label label = new Label();
                label.setText("Label");
                label.setAlignment(Pos.CENTER);
                label.setMaxWidth(Double.MAX_VALUE);

                label.setAlignment(Pos.CENTER);
                label.setTextAlignment(TextAlignment.CENTER);
                label.setMaxWidth(Double.MAX_VALUE);


                //

                vBox.getChildren().add(label);

                vBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
                    addAppointments(vBox);
                }));
                // set vGrow to always expand or shrink
                GridPane.setVgrow(vBox, Priority.ALWAYS);
                // Add the Vbox to the grid
                gridPane.add(vBox, j, i);
            }
        }
    }


    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    private void updateSchedule() {
        Model.getInstance().selectedLocalDate = datePicker.getValue();
        System.out.println("The date selected is " + Model.getInstance().selectedLocalDate);

        initVariables();
        loadWeekDaysHeaderLabels();
    }

    private void addAppointments(VBox vBox) {
        // Todo
        System.out.println("Pushed");
    }


    private LocalDate getFirstWeekDay(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    }

    private void retrieveData() {
    }


}
