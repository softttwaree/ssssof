package com.example.projectsoftware;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class HelloController3 {

    @FXML
    private ListView<Halls> hallListView;



    @FXML
    private TextField txt1;

    private ObservableList<Halls> allHalls;


    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1482003";

    @FXML
    public void initialize() {
        allHalls = fetchHallsFromDatabase();
        hallListView.setItems(allHalls);
        hallListView.setVisible(false);
        txt1.textProperty().addListener((observable, oldValue, newValue) -> filterHalls(newValue));
        hallListView.setOnMouseClicked(this::showHallInformationDialog);
    }

    private void showHallInformationDialog(MouseEvent event) {
        Halls selectedHall = hallListView.getSelectionModel().getSelectedItem();
        if (selectedHall != null) {
            String hallName = selectedHall.getName();
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if ("Rose".equals(hallName)) {
                    loader.setLocation(getClass().getResource("Rose.fxml"));
                    root = loader.load();
                } else if ("Masaya".equals(hallName)) {
                    loader.setLocation(getClass().getResource("Masaya.fxml"));
                    root = loader.load();
                } else if ("Dreams".equals(hallName)) {
                    loader.setLocation(getClass().getResource("booking.fxml"));
                    root = loader.load();
                } else {
                    // Handle unknown hall name
                    openHallInformationDialog(selectedHall);
                    return;
                }

                HelloController controller = loader.getController();
                controller.performInitialization();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception or provide user feedback
            }
        }
    }

    private ObservableList<Halls> fetchHallsFromDatabase() {
        ObservableList<Halls> halls = FXCollections.observableArrayList();

        String query = "SELECT hallname, priceperhour, capacity FROM software.Halls";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("hallname");
                double price = resultSet.getDouble("priceperhour");
                int capacity = resultSet.getInt("capacity");

                Halls hall = new Halls(name, price, capacity);
                halls.add(hall);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }

        return halls;
    }

    private void filterHalls(String query) {
        ObservableList<Halls> filteredHalls = FXCollections.observableArrayList();

        // Filter based on the query (you can customize the filtering logic)
        for (Halls hall : allHalls) {
            if (hall.getName().toLowerCase().contains(query.toLowerCase()) ||
                    (isNumeric(query) && (String.valueOf(hall.getPrice()).equals(query) || String.valueOf(hall.getCapacity()).equals(query)))) {
                filteredHalls.add(hall);
            }
        }

        // Update the hallListView with filtered data
        hallListView.setItems(filteredHalls);
        hallListView.setVisible(!query.isEmpty());
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    private Button cancclllee;

    @FXML
    private DatePicker newcalender;

    @FXML
    private TextField newcapacity;

    @FXML
    private ChoiceBox<?> newchoice;

    @FXML
    private TextField newhallname;

    @FXML
    private TextField newlocation;

    @FXML
    private TextField newprice;

    @FXML
    void canclenew(ActionEvent event) {

    }

    @FXML
    void choicesnew(MouseEvent event) {

    }

    @FXML
    void newreserve(ActionEvent event) {

    }

    private void openHallInformationDialog(Halls hall) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            String query = "SELECT * FROM software.halls WHERE hallname = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, hall.getName());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String hallName = resultSet.getString("hallname");
                        int capacity = resultSet.getInt("capacity");
                        double pricePerHour = resultSet.getDouble("priceperhour");
                        String location = resultSet.getString("location");
                        // Assuming 'image' column is of type bytea in PostgreSQL
                        byte[] imageData = resultSet.getBytes("image");

                        // Convert byte array to javafx.scene.image.Image
                        javafx.scene.image.Image image = convertToJavaFXImage(imageData);

                        // Display information in a dialog
                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                        informationAlert.setTitle("Hall Information");
                        informationAlert.setHeaderText(null);
                        informationAlert.setContentText("Hall Name: " + hallName + "\n" +
                                "Capacity: " + capacity + "\n" +
                                "Price Per Hour: " + pricePerHour + "\n" +
                                "Location: " + location);
                        informationAlert.setGraphic(new javafx.scene.image.ImageView(image));
                        informationAlert.getButtonTypes().clear();
                        ButtonType reserveButton = new ButtonType("Reserve");
                        ButtonType cancelButton = new ButtonType("Cancel");
                        informationAlert.getButtonTypes().addAll(reserveButton, cancelButton);

                        // Handle button actions
                        informationAlert.showAndWait().ifPresent(buttonType -> {
                            if (buttonType == reserveButton) {
                                // Implement reserve button action
                            } else if (buttonType == cancelButton) {
                                // Implement cancel button action
                            }
                        });
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        }
    }

    // Method to display alerts/messages
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to convert byte array to javafx.scene.image.Image
    private javafx.scene.image.Image convertToJavaFXImage(byte[] imageData) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageData)) {
            return new javafx.scene.image.Image(bis);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error converting image: " + e.getMessage());
            return null;
        }
    }
}
