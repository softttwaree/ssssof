package com.example.projectsoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class signup  implements Initializable {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1482003";

    static Logger logger = Logger.getLogger(signup.class.getName());
    public TextField id;
    public TextField fname;
    public TextField lname;
    public TextField username;
    public TextField email;
    public TextField passs;
    public Button backsign;
    public Button sv;


    @FXML
    private ChoiceBox<String> checkbox = new ChoiceBox<>();
    private String[] pai = {"customer"};


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        checkbox.getItems().addAll(pai);
    }

    public void backsignn(ActionEvent event) {

        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(ActionEvent event) {
        int idValue = Integer.parseInt(id.getText());
        String fnameValue = fname.getText();
        String lnameValue = lname.getText();
        String usernameValue = username.getText();
        String emailValue = email.getText();
        String passwordValue = passs.getText();
        String roleValue = checkbox.getValue();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO software.users (userid, firstname, lastname, username, password, email, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idValue);
                preparedStatement.setString(2, fnameValue);
                preparedStatement.setString(3, lnameValue);
                preparedStatement.setString(4, usernameValue);
                preparedStatement.setString(5, passwordValue);
                preparedStatement.setString(6, emailValue);
                preparedStatement.setString(7, roleValue);

                preparedStatement.executeUpdate();
                showAlert("User successfully inserted into the database.");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sing up");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }


    public static boolean gmailTest(String gmail) {
        if (Character.isDigit(gmail.charAt(0)) || gmail.length() < 11) return false;
        else {
            boolean flag = false;
            for (int i = 1; i < gmail.length(); i++) {
                if (gmail.charAt(i) == '@') flag = true;
            }
            return flag;
        }
    }

    // Method to validate the name input
    public static boolean nameTest(String name) {
        // For demonstration, let's assume the name must be at least 2 characters long
        return name != null && name.trim().length() >= 2;
    }


    public static boolean passwordTest(String password) {
        boolean flags = false;
        boolean flagc = false;
        boolean flagn = false;
        if (password.length() < 4) return false;
        else {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLowerCase(password.charAt(i))) flags = true;
                else if (Character.isUpperCase(password.charAt(i))) flagc = true;
                else if (Character.isDigit(password.charAt(i))) flagn = true;
            }
            return flags && flagc && flagn;
        }
    }



    public static boolean idTest(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare the SQL query
            String sql = "SELECT COUNT(*) FROM software.users WHERE CAST(userid AS TEXT) = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            // Get the result
            resultSet.next();
            int count = resultSet.getInt(1);

            // If count > 0, ID already exists
            if (count > 0) {
                return false;
            }

            // If count == 0, check the length of the ID
            return id.length() > 2;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static boolean selectRole(String roleName) {
        return roleName.equalsIgnoreCase("Customer");
    }



    private static boolean isEmailAlreadyRegistered(String email) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Prepare the SQL query
            String sql = "SELECT COUNT(*) FROM software.users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);


            ResultSet resultSet = statement.executeQuery();

            // Get the result
            resultSet.next();
            int count = resultSet.getInt(1);

            // If count > 0, email already registered
            return count > 0;
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerWithExistingEmail(String email) {

        if (isEmailAlreadyRegistered(email)) {

            return true;
        } else {

            return false;
        }
    }
}
