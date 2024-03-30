package com.example.projectsoftware;

import java.time.Duration;
import java.time.Instant;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import java.io.IOException;

import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.util.Callback;

import javafx.util.StringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;




public class HelloController {

    static Logger logger = Logger.getLogger(com.example.projectsoftware.HelloController.class.getName());


    @FXML
    public TextField gmailLogIn;
    public Button service;
    public Button halls;
    public Button booking;
    public Button invoice;
    public Button prof;
    public Button connect;
    public Button bback;
    public Button packg;
    @FXML
    private javafx.scene.control.Button login1;
    @FXML
    private javafx.scene.control.Button signUp;
    @FXML
    public PasswordField passwordLogIn;
    @FXML
    private javafx.scene.control.Button forget;
    @FXML
    private Button sv;
    @FXML
    private static String z;

    public static String getZ() {

        return z;
    }

    public static void setZ(String z) {
        com.example.projectsoftware.HelloController.z = z;
    }


    @FXML
    public void login1Clicked(ActionEvent event) {
        String emailInput = gmailLogIn.getText();
        String passwordInput = passwordLogIn.getText();

        String query = "SELECT * FROM software.users WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, emailInput);
            preparedStatement.setString(2, passwordInput);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                UserCredentials.setEmail(emailInput);
                UserCredentials.setPassword(passwordInput);

                switch (role) {
                    case "customer":
                        loadInterface("custointer.fxml", event);

                        break;
                    case "service-provider":
                        loadInterface("serviceproviderpage.fxml", event);
                        break;
                    case "admin":
                        loadInterface("Adminlogin.fxml", event);
                        break;
                    default:
                        showAlert("Invalid Role");
                        break;
                }
            } else {
                showAlert("Invalid Email or Password");
            }
        } catch (Exception e) {
            showAlert("Error during login: " + e.getMessage());
        }
    }


    private void loadInterface(String fxmlFileName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUp1Clicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, " An error occurred while opening a new window:");
        }
    }

    @FXML
    void HallsClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("Halls.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void serviceClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("service.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void pakClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("packg.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void bookingClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("booking.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void invoiceClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("invoice.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void profClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("prof.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void connectClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("conn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backto1(ActionEvent event) {
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

    @FXML
    void baccc(ActionEvent event) {
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

    @FXML
    private Button backtoallhalls;

    @FXML
    void backktoallhalls(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("Halls.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private TextField capacityy;

    @FXML
    private TextField pricee;

    @FXML
    private TextField locationn;

    @FXML
    private DatePicker dat = new DatePicker();


    @FXML
    private Button makereser;

    @FXML
    private Button bk;

    @FXML
    private Button ros;

    @FXML
    private Spinner<LocalTime> timeSpinner = new Spinner<>();

    @FXML
    private Spinner<LocalTime> timeSpinner1 = new Spinner<>();

    public void performInitialization() {
        initializeTimeSpinners();
    }

    private void initializeTimeSpinners() {
        // Initialize the first spinner
        SpinnerValueFactory<LocalTime> valueFactory1 = createTimeSpinnerValueFactory();
        timeSpinner.setValueFactory(valueFactory1);
        timeSpinner.setEditable(true);

        // Initialize the second spinner
        SpinnerValueFactory<LocalTime> valueFactory2 = createTimeSpinnerValueFactory();
        timeSpinner1.setValueFactory(valueFactory2);
        timeSpinner1.setEditable(true);
    }

    private SpinnerValueFactory<LocalTime> createTimeSpinnerValueFactory() {
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            {
                setConverter(new LocalTimeStringConverter(FormatStyle.MEDIUM));
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null)
                    setValue(LocalTime.now());
                else {
                    LocalTime time = getValue();
                    setValue(time.minusMinutes(steps));
                }
            }

            @Override
            public void increment(int steps) {
                if (getValue() == null)
                    setValue(LocalTime.now());
                else {
                    LocalTime time = getValue();
                    setValue(time.plusMinutes(steps));
                }
            }
        };
        return valueFactory;
    }

    @FXML
    private void select(ActionEvent event) {
        // Your database connection parameters

        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1482003";

        // SQL query to retrieve data for the selected hall
        String query = "SELECT capacity, location, priceperhour FROM software.halls WHERE hallname = 'Rose'";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                int capacity = resultSet.getInt("capacity");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("priceperhour");

                // Set the values in text fields
                capacityy.setText(String.valueOf(capacity));
                locationn.setText(location);
                pricee.setText(String.valueOf(price));
                capacityy.setEditable(false);
                locationn.setEditable(false);
                pricee.setEditable(false);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }
    }

    @FXML
    void bookHall(ActionEvent event) {
        LocalDate selectedDate = dat.getValue();
        String startTimeStr = choicetime.getValue(); // Retrieve start time from the ChoiceBox

        if (selectedDate == null || startTimeStr == null) {
            showAlert("Please select date and start time.");
            return;
        }

        LocalTime startTime = LocalTime.parse(startTimeStr); // Parse the start time string to LocalTime

        // Calculate the end time to be 2 hours after the start time
        LocalTime endTime = startTime.plusHours(2);

        int hallId = 2; // Assuming hallId 2 for demonstration

        // Calculate the duration of the booking in hours
        long durationHours = 2; // Hardcoded duration of 2 hours

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1482003")) {

            // Retrieve the user's ID based on email and password
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), connection);

            if (userId == -1) {
                showAlert("Invalid email or password.");
                return;
            }

            // Check if the hall is already booked for the selected date and time
            if (!isHallAvailable(selectedDate, startTime, endTime, hallId, connection)) {
                showAlert("Wait owner to accept your reservation.");
                return;
            }

            // Retrieve the price per hour for the selected hall
            BigDecimal pricePerHour = getPricePerHour(hallId, connection);

            // Calculate the total price for the reservation
            BigDecimal totalPrice = pricePerHour.multiply(BigDecimal.valueOf(durationHours));

            // Insert the reservation into the database
            insertReservation(userId, hallId, selectedDate, startTime, endTime, totalPrice, connection);

            showAlert("Wait owner to accept your reservation.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to book the hall. Please try again later.");
        }
    }

    private int getUserId(String email, String password, Connection connection) throws SQLException {
        String sql = "SELECT userid FROM software.users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt("userid") : -1;
        }
    }

    private boolean isHallAvailable(LocalDate date, LocalTime startTime, LocalTime endTime, int hallId,
                                    Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM software.new_table_name WHERE hallid = ? AND date = ? AND "
                + "((starttime <= ? AND endtime >= ?) OR (starttime <= ? AND endtime >= ?))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            statement.setDate(2, java.sql.Date.valueOf(date));
            statement.setTime(3, java.sql.Time.valueOf(startTime));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setTime(6, java.sql.Time.valueOf(endTime));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }
    }

    private BigDecimal getPricePerHour(int hallId, Connection connection) throws SQLException {
        String sql = "SELECT priceperhour FROM software.halls WHERE hallid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal("priceperhour");
        }
    }

    private void insertReservation(int userId, int hallId, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   BigDecimal totalPrice, Connection connection) throws SQLException {
        String sql = "INSERT INTO software.new_table_name (userid, hallid, date, starttime, endtime, totalprice, state) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, hallId);
            statement.setDate(3, java.sql.Date.valueOf(date));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setBigDecimal(6, totalPrice);
            statement.setString(7, "wait"); // Set the initial state to 'wait'
            statement.executeUpdate();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private Button addser;

    @FXML
    private CheckBox deccheck;

    @FXML
    private CheckBox djcheck;

    @FXML
    private CheckBox opencheck;

    @FXML
    void addser(ActionEvent event) {
        double additionalPrice = 0;


        if (djcheck.isSelected()) {
            additionalPrice += 500;
        }
        if (opencheck.isSelected()) {
            additionalPrice += 1000;
        }
        if (deccheck.isSelected()) {
            additionalPrice += 1000;
        }

        String email = UserCredentials.getEmail();
        String password = UserCredentials.getPassword();
        updateTotalPrice(email, password, additionalPrice);
    }

    // Method to update the total price in the reservations table

    private void updateTotalPrice(String email, String password, double additionalPrice) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String paassword = "1482003";

        String getUserSql = "SELECT userid FROM software.users WHERE email = ? AND password = ?";
        String getReservationsSql = "SELECT reservationid FROM software.reservations WHERE userid = ?";
        String updateReservationSql = "UPDATE software.reservations SET totalprice = totalprice + ? WHERE reservationid = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, paassword);
             PreparedStatement getUserStatement = connection.prepareStatement(getUserSql);
             PreparedStatement getReservationsStatement = connection.prepareStatement(getReservationsSql);
             PreparedStatement updateReservationStatement = connection.prepareStatement(updateReservationSql)) {

            // Retrieve the user ID based on email and password
            getUserStatement.setString(1, email);
            getUserStatement.setString(2, password);
            ResultSet userResultSet = getUserStatement.executeQuery();

            if (userResultSet.next()) {
                int userId = userResultSet.getInt("userid");

                // Retrieve reservations for the user
                getReservationsStatement.setInt(1, userId);
                ResultSet reservationsResultSet = getReservationsStatement.executeQuery();

                while (reservationsResultSet.next()) {
                    int reservationId = reservationsResultSet.getInt("reservationid");

                    // Update the total price for each reservation
                    updateReservationStatement.setDouble(1, additionalPrice);
                    updateReservationStatement.setInt(2, reservationId);

                    int rowsAffected = updateReservationStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        showAlert("Total price updated successfully.");
                    } else {
                        showAlert("Failed to update total price for reservation ID: " + reservationId);
                    }
                }
            } else {
                showAlert("Invalid Email or Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void forgoooot(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("forgotpass.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, " An error occurred while opening a new window:");
        }
    }

    @FXML
    private Button check;

    @FXML
    private TextField emmmail;

    @FXML
    private TextField newpass;

    @FXML
    private Button reset;

    @FXML
    private TextField vernewpass;

    @FXML
    private TextField yourcode;

    @FXML
    private void checkbutton(ActionEvent event) {
        String email = emmmail.getText();
        String code = yourcode.getText();

        if (email.isEmpty() || code.isEmpty()) {
            showAlert("Please enter both email and code.");
            return;
        }

        // Database connection parameters
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1482003";

        // SQL query to check if the email and code exist in the users table
        String query = "SELECT userid FROM software.users WHERE email = ? AND code = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, code);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Email and code exist, enable password reset fields
                newpass.setDisable(false);
                vernewpass.setDisable(false);
                reset.setDisable(false);
                showAlert("Enter a new password please.");
            } else {
                // Email or code is incorrect
                showAlert("Invalid email or code. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("An error occurred while checking email and code.");
        }
    }

    @FXML
    private void resetbutton(ActionEvent event) {
        String newPassword = newpass.getText();
        String verifyNewPassword = vernewpass.getText();

        if (!newPassword.equals(verifyNewPassword)) {
            showAlert("Passwords do not match. Please try again.");
            return;
        }

        String email = emmmail.getText();

        // Database connection parameters
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1482003";

        // SQL query to update the password for the user
        String updateQuery = "UPDATE software.users SET password = ? WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Password reset successfully.");
                // Clear fields after successful reset
                emmmail.clear();
                yourcode.clear();
                newpass.clear();
                vernewpass.clear();
                // Disable password reset fields
                newpass.setDisable(true);
                vernewpass.setDisable(true);
                reset.setDisable(true);
            } else {
                showAlert("Failed to reset password. Please try again later.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("An error occurred while resetting password.");
        }
    }


    @FXML
    private TextField budgetTextField;

    @FXML
    private Button searchButton;
    @FXML
    private Button budgedconfirm;
    @FXML
    private Button confirmPercentage;


    private double budget;
    private double hallPercentage;
    private double servicePercentage;

    @FXML
    void searchButtonClicked(ActionEvent event) {
        // Display alert to enter budget
        showAlert("Please enter your budget.");

        // Reset percentages
        hallPercentage = 0;
        servicePercentage = 0;

        // Clear text field
        budgetTextField.clear();
    }

    @FXML
    void confirmBudget(ActionEvent event) {
        // Validate budget input
        try {
            budget = Double.parseDouble(budgetTextField.getText());
            if (budget <= 0) {
                showAlert("Please enter a valid budget.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid budget.");
            return;
        }

        // Prompt user to specify percentage allocation
        showAlert("Please specify the percentage allocation for halls and services.");

        // Clear text field
        budgetTextField.clear();
    }

    @FXML
    void confirmPercentage(ActionEvent event) {
        // Validate percentage input
        try {
            String[] percentages = budgetTextField.getText().split(",");
            if (percentages.length != 2) {
                showAlert("Please enter two percentages separated by a comma.");
                return;
            }

            hallPercentage = Double.parseDouble(percentages[0]);
            servicePercentage = Double.parseDouble(percentages[1]);

            if (hallPercentage < 0 || servicePercentage < 0 || hallPercentage + servicePercentage != 100) {
                showAlert("Please enter valid percentage allocations.");
                return;
            }

            // Fetch halls and services based on budget and percentages
            fetchHallsAndServices();

        } catch (NumberFormatException e) {
            showAlert("Please enter valid percentages.");
            return;
        }
    }

    private void fetchHallsAndServices() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1482003")) {

            List<String> availableHalls = fetchHalls(connection);
            List<String> availableServices = fetchServices(connection);
            List<String> availablePackages = fetchPackages(connection);

            // Display available options
            StringBuilder message = new StringBuilder("Available options:\n");
            message.append("Halls:\n");
            for (String hall : availableHalls) {
                message.append(hall).append("\n");
            }
            message.append("\nServices:\n");
            for (String service : availableServices) {
                message.append(service).append("\n");
            }
            message.append("\nPackages:\n");
            for (String pack : availablePackages) {
                message.append(pack).append("\n");
            }
            showAlert(message.toString());

        } catch (SQLException e) {
            showAlert("Error fetching data from the database: " + e.getMessage());
        }
    }

    private List<String> fetchHalls(Connection connection) throws SQLException {
        List<String> halls = new ArrayList<>();
        String sql = "SELECT hallname FROM software.halls WHERE priceperhour <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, budget * hallPercentage / 100);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                halls.add(resultSet.getString("hallname"));
            }
        }
        return halls;
    }

    private List<String> fetchServices(Connection connection) throws SQLException {
        List<String> services = new ArrayList<>();
        String sql = "SELECT servicename FROM software.services WHERE price <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, budget * servicePercentage / 100);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(resultSet.getString("servicename"));
            }
        }
        return services;
    }

    private List<String> fetchPackages(Connection connection) throws SQLException {
        List<String> packages = new ArrayList<>();
        String sql = "WITH RECURSIVE service_combinations AS (" +
                "    SELECT serviceid, servicename, price, CAST(servicename AS TEXT) AS combination " +
                "    FROM software.services " +
                "    UNION ALL " +
                "    SELECT s.serviceid, s.servicename, s.price, CONCAT(sc.combination, ' + ', s.servicename) " +
                "    FROM software.services s " +
                "    JOIN service_combinations sc ON true " +
                "    WHERE s.serviceid > sc.serviceid" +
                ") " +
                "SELECT CONCAT(h.hallname, ' with ', sc.combination) AS package_name " +
                "FROM software.halls h " +
                "CROSS JOIN service_combinations sc " +
                "WHERE h.priceperhour <= ? " +
                "GROUP BY h.hallname, sc.combination " +
                "HAVING SUM(sc.price) <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            double hallBudget = budget * hallPercentage / 100;
            double serviceBudget = budget * servicePercentage / 100;
            statement.setDouble(1, hallBudget);
            statement.setDouble(2, hallBudget + serviceBudget);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                packages.add(resultSet.getString("package_name"));
            }
        }
        return packages;
    }

    @FXML
    private Button event;

    @FXML
    private Button hallss;

    @FXML
    private Button invoices;

    @FXML
    private Button profile;

    @FXML
    private Button servicee;

    @FXML
    private Button users;

    @FXML
    void adminevent(ActionEvent event) {

    }

    @FXML
    void adminhalls(ActionEvent event) {
        System.out.println("0");
        try {
            System.out.println("1");
            Parent root = FXMLLoader.load(getClass().getResource("HallsTabel.fxml"));
            System.out.println("2");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            System.out.println("3");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("11");
            // Log the exception
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }
    }

    @FXML
    void admininvoices(ActionEvent event) {

    }

    @FXML
    void adminprofile(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Log the exception
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }

    }

    @FXML
    void adminservice(ActionEvent event) {

    }

    @FXML
    void adminusers(ActionEvent event) {

    }


    @FXML
    private Button Addd;

    @FXML
    private Button Deleteee;
    @FXML
    private TableView<Hall> hallTableView = new TableView<>();

    @FXML
    private TableColumn<Hall, Integer> hallidd; // Specify the types for the TableColumn

    @FXML
    private TableColumn<Hall, String> hallnamee; // Specify the types for the TableColumn

    @FXML
    private TableColumn<Hall, Integer> capacityyy; // Specify the types for the TableColumn

    @FXML
    private TableColumn<Hall, String> locationnn; // Specify the types for the TableColumn

    @FXML
    private TableColumn<Hall, Double> priceperhourr; // Specify the types for the TableColumn
    @FXML
    private TableColumn<Hall, Integer> USERID;
    // Initialize method or constructor where you set up the TableView


    @FXML
    private Button vieeew;

    @FXML
    void addhalls(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddHall.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Log the exception
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }


    }

    @FXML
    void deletehalls(ActionEvent event) {
        hallidd.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        hallnamee.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        capacityyy.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        priceperhourr.setCellValueFactory(new PropertyValueFactory<>("pricePerHour"));
        locationnn.setCellValueFactory(new PropertyValueFactory<>("location"));
        USERID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        Hall selectedHall = hallTableView.getSelectionModel().getSelectedItem();
        if (selectedHall != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
                String sql = "DELETE FROM software.halls WHERE hallid = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, selectedHall.getHallId());
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Row deleted successfully.");
                    // Remove the selected row from the TableView
                    hallTableView.getItems().remove(selectedHall);
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No row selected.");
        }
    }


    @FXML
    public void viewhalls(ActionEvent event) {
        // Ensure hallTableView is not null before proceeding
        if (hallTableView == null) {
            System.err.println("hallTableView is not initialized!");
            return;
        }

        // Set cell value factories for each column
        hallidd.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        hallnamee.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        capacityyy.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        priceperhourr.setCellValueFactory(new PropertyValueFactory<>("pricePerHour"));
        locationnn.setCellValueFactory(new PropertyValueFactory<>("location"));
        USERID.setCellValueFactory(new PropertyValueFactory<>("userId")); // Assuming you have a USERID column

        // Clear existing items in the table
        hallTableView.getItems().clear();

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            String sql = "SELECT * FROM software.halls";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<Hall> halls = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int hallId = resultSet.getInt("hallid");
                String hallName = resultSet.getString("hallname");
                int capacity = resultSet.getInt("capacity");
                double pricePerHour = resultSet.getDouble("priceperhour");
                String location = resultSet.getString("location");
                int userId = resultSet.getInt("userid");

                Hall hall = new Hall(hallId, hallName, capacity, pricePerHour, location, userId);
                halls.add(hall);
            }

            // Print halls list for debugging
            for (Hall hall : halls) {
                System.out.println("Hall ID: " + hall.getHallId());
                System.out.println("Hall Name: " + hall.getHallName());
                System.out.println("Capacity: " + hall.getCapacity());
                System.out.println("Price Per Hour: " + hall.getPricePerHour());
                System.out.println("Location: " + hall.getLocation());
                System.out.println("User ID: " + hall.getUserId());
                System.out.println("---------------------------------");
            }

            // Add items to the table
            hallTableView.setItems(halls);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button editadmin;

    @FXML
    private TextField emailltxt;

    @FXML
    private TextField fntxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField lntxt;

    @FXML
    private TextField passtxt;

    @FXML
    private Button saveadmiv;

    @FXML
    private Button uploadadminoic;

    @FXML
    private TextField userntxt;

    @FXML
    private Button viewadmin;

    @FXML
    void editadmininfo(ActionEvent event) {

    }

    private File selectedImageFile;

    @FXML
    private TextField codetxt;


    @FXML
    void saveadmininfo(ActionEvent event) {
        String email = UserCredentials.getEmail();
        String password = UserCredentials.getPassword();

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(saveadmiv.getScene().getWindow());

            if (selectedFile != null) {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");

                String sql = "UPDATE software.users SET photo = ? WHERE email = ? AND password = ? AND role = 'admin'";
                PreparedStatement statement = conn.prepareStatement(sql);

                // Set the photo parameter
                statement.setBinaryStream(1, inputStream);
                statement.setString(2, email);
                statement.setString(3, password);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    showAlert("Admin photo updated successfully!");
                } else {
                    showAlert("Failed to update admin photo!");
                }

                inputStream.close();
                statement.close();
                conn.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error occurred while updating admin photo!");
        }

    }


    @FXML
    private ImageView pictureImageView;

    @FXML
    void uplodeadminpicture(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Load the image
            Image image = new Image(selectedFile.toURI().toString());

            // Set the image to the ImageView
            pictureImageView.setImage(image);

            // Resize the image to fit the size of the ImageView
            pictureImageView.setPreserveRatio(true);
            pictureImageView.setFitWidth(169); // Set the width of the ImageView
            pictureImageView.setFitHeight(163); // Set the height of the ImageView
        }
    }


    @FXML
    void viweadmininfo(ActionEvent event) {
        String email = UserCredentials.getEmail();
        String password = UserCredentials.getPassword();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");
            String sql = "SELECT * FROM software.users WHERE email = ? AND password = ? AND role = 'admin'";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int userId = result.getInt("userid");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String username = result.getString("username");
                String userEmail = result.getString("email"); // Retrieve email from the database
                String userPassword = result.getString("password"); // Retrieve password from the database
                String userCode = result.getString("code");
                byte[] imageData = result.getBytes("photo"); // Retrieve image data from the database

                idtxt.setText(String.valueOf(userId));
                fntxt.setText(firstName);
                lntxt.setText(lastName);
                userntxt.setText(username);
                emailltxt.setText(userEmail);
                passtxt.setText(userPassword);
                codetxt.setText(userCode);

                if (imageData != null && imageData.length > 0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    pictureImageView.setImage(image);
                }


            } else {
                showAlert("Admin not found!");
            }

            result.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button addaa;

    @FXML
    private TextField txet1;

    @FXML
    private TextField txet2;

    @FXML
    private TextField txet3;

    @FXML
    private TextField txet4;
    @FXML
    private TextField text5;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1482003";

    @FXML
    void addhallbutton(ActionEvent event) {
        String hallName = txet1.getText();
        int capacity = Integer.parseInt(txet2.getText());
        double pricePerHour = Double.parseDouble(txet3.getText());
        String location = txet4.getText();
        int userId = Integer.parseInt(text5.getText()); // Retrieve userId from text5

        if (hallName.isEmpty() || location.isEmpty()) {
            showAlert("Hall name and location cannot be empty.");
            return;
        }

        if (!isUserIdValid(userId)) {
            showAlert("User ID does not exist.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO software.halls (hallname, capacity, priceperhour, location, userid, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hallName);
            statement.setInt(2, capacity);
            statement.setDouble(3, pricePerHour);
            statement.setString(4, location);
            statement.setInt(5, userId);
            statement.setBytes(6, null);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("A new hall has been added successfully.");
                clearTextFields();
                updateImage(getGeneratedHallId(statement));
            } else {
                showAlert("Failed to add a new hall.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Invalid capacity, price per hour, or user ID format.");
        }
    }

    private int getGeneratedHallId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return -1;
    }

    private boolean isUserIdValid(int userId) {
        String sql = "SELECT userid FROM software.users WHERE userid = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If resultSet.next() returns true, userId exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of SQL exception
        }
    }

    private void updateImage(int hallId) {
        if (uploadedImage != null) {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "UPDATE software.halls SET image = ? WHERE hallid = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setBytes(1, imageToByteArray(uploadedImage));
                statement.setInt(2, hallId);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    showAlert("Image saved successfully.");
                } else {
                    showAlert("Failed to save image.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Database error: " + e.getMessage());
            }
        }
    }

    private byte[] imageToByteArray(Image image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(bufferedImage, "png", outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error converting image to byte array: " + e.getMessage());
            return null;
        }
    }

    private void clearTextFields() {
        txet1.clear();
        txet2.clear();
        txet3.clear();
        txet4.clear();
        text5.clear();
    }

    @FXML
    private Button backwewe;

    @FXML
    void bacckkkkk(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HallsTabel.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);
        }

    }

    @FXML
    private Button uploadhallpppiii;
    @FXML
    private Label hallpiclabel;
    private Image uploadedImage;


    @FXML
    void uploadhallpic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        File selectedFile = fileChooser.showOpenDialog(uploadhallpppiii.getScene().getWindow());

        if (selectedFile != null) {
            try {
                byte[] imageData = readImageFile(selectedFile);
                if (imageData != null) {
                    uploadedImage = new Image(new ByteArrayInputStream(imageData));
                    hallpiclabel.setGraphic(new ImageView(uploadedImage));
                    showAlert("Image uploaded successfully.");
                } else {
                    showAlert("Failed to upload image.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error reading image file: " + e.getMessage());
            }
        }
    }

    private byte[] readImageFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return bos.toByteArray();
        }
    }


    @FXML
    private ChoiceBox<String> choicetime = new ChoiceBox<>();
    @FXML
    private Button choicebutton;
    @FXML
    private Button buttontime;


    private LocalDate selectedDate;


    private Connection connection;
    private PreparedStatement checkReservationStatement;

    @FXML
    public void initialize() {
        choicetime.getItems().addAll("16:00:00", "18:00:00", "20:00:00");


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");
            checkReservationStatement = connection.prepareStatement("SELECT COUNT(*) FROM software.reservations WHERE date = ? AND starttime = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dat.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                } else {
                    setDisable(false);

                    try {
                        checkReservationStatement.setDate(1, Date.valueOf(item));
                        boolean allTimesReserved = true; // Flag to track if all times are reserved
                        boolean anyTimeReserved = false; // Flag to track if any time is reserved
                        for (String time : choicetime.getItems()) {
                            checkReservationStatement.setTime(2, Time.valueOf(time));
                            ResultSet resultSet = checkReservationStatement.executeQuery();
                            resultSet.next();
                            int count = resultSet.getInt(1);
                            if (count == 0) {
                                allTimesReserved = false;
                            } else {
                                anyTimeReserved = true;
                            }
                        }
                        if (allTimesReserved) {
                            setStyle("-fx-background-color: #ff0000;");
                            setOnMouseClicked(event -> {
                                showAlert("All time slots are reserved for this day.");
                            });
                        } else if (anyTimeReserved) {
                            setStyle("-fx-background-color: #ffff00;");

                        } else {
                            setStyle("-fx-background-color: #00ff00;");

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void choisetiameondate(javafx.scene.input.MouseEvent mouseEvent) {
        LocalDate selectedDate = dat.getValue();
        if (selectedDate == null) {
            return;
        }

        choicetime.getItems().clear();

        List<String> availableTimes = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");

            checkReservationStatement = connection.prepareStatement("SELECT DISTINCT starttime FROM software.reservations WHERE date = ? AND hallid = 2");

            checkReservationStatement.setDate(1, Date.valueOf(selectedDate));

            ResultSet resultSet = checkReservationStatement.executeQuery();

            while (resultSet.next()) {
                availableTimes.add(resultSet.getString("starttime"));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> allTimes = List.of("16:00:00", "18:00:00", "20:00:00");

        List<String> availableTimesFiltered = allTimes.stream()
                .filter(time -> !availableTimes.contains(time))
                .collect(Collectors.toList());

        choicetime.getItems().addAll(availableTimesFiltered);
    }

    @FXML
    private Button notifi;

    @FXML
    void notificbutton(ActionEvent event) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("reservationnoti.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while opening a new window:", e);

        }
    }


    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;

    @FXML
    private TableColumn<new_reservation, Integer> cc1 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Integer> cc2 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Integer> cc3 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Date> cc4 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Time> cc5 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Time> cc6 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, Double> cc7 = new TableColumn<>();

    @FXML
    private TableColumn<new_reservation, String> cc8 = new TableColumn<>();

    @FXML
    private TableView<new_reservation> tabelnotification = new TableView<>();




    @FXML
    void logoutserviceprovider(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {
                String sql = "SELECT r.reservationid, r.userid, r.hallid, r.date, r.starttime, r.endtime, r.totalprice, r.state " +
                        "FROM software.new_table_name r " +
                        "INNER JOIN software.halls h ON r.hallid = h.hallid " +
                        "WHERE h.userid = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ArrayList<new_reservation> reservations = new ArrayList<>();
                        while (resultSet.next()) {
                            int reservationId = resultSet.getInt("reservationid");
                            int usserId = resultSet.getInt("userid");
                            int hallId = resultSet.getInt("hallid");
                            Date date = resultSet.getDate("date");
                            Time startTime = resultSet.getTime("starttime");
                            Time endTime = resultSet.getTime("endtime");
                            double totalPrice = resultSet.getDouble("totalprice");
                            String state = resultSet.getString("state");
                            reservations.add(new new_reservation(reservationId, usserId, hallId, date, startTime, endTime, totalPrice, state));
                        }

                        cc1.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
                        cc2.setCellValueFactory(new PropertyValueFactory<>("userId"));
                        cc3.setCellValueFactory(new PropertyValueFactory<>("hallId"));
                        cc4.setCellValueFactory(new PropertyValueFactory<>("date"));
                        cc5.setCellValueFactory(new PropertyValueFactory<>("startTime"));
                        cc6.setCellValueFactory(new PropertyValueFactory<>("endTime"));
                        cc7.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
                        cc8.setCellValueFactory(new PropertyValueFactory<>("state"));


                        tabelnotification.getItems().clear();
                        tabelnotification.getItems().addAll(reservations);
                    }
                }
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    @FXML
    void accept(ActionEvent event) {
        ObservableList<new_reservation> selectedReservations = tabelnotification.getSelectionModel().getSelectedItems();

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            String sql = "UPDATE software.new_table_name SET state = ? WHERE reservationid = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "accepted");
                for (new_reservation reservation : selectedReservations) {
                    statement.setInt(2, reservation.getReservationId());

                    statement.executeUpdate();
                }

                logoutserviceprovider(new ActionEvent());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void deleteres(ActionEvent event) {
        ObservableList<new_reservation> selectedReservations = tabelnotification.getSelectionModel().getSelectedItems();

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            String sql = "UPDATE software.new_table_name SET state = ? WHERE reservationid = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "rejected");
                for (new_reservation reservation : selectedReservations) {
                    statement.setInt(2, reservation.getReservationId());
                    statement.executeUpdate();
                }

                logoutserviceprovider(new ActionEvent());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private Button bbb1;

    @FXML
    private Button bbb2;

    @FXML
    private Button bbb3;

    @FXML
    private TableColumn<ReservationInfo, Integer> col1;

    @FXML
    private TableColumn<ReservationInfo, String> col2;

    @FXML
    private TableColumn<ReservationInfo, String> col3;

    @FXML
    private TableColumn<ReservationInfo, String> col4;

    @FXML
    private TableColumn<ReservationInfo, Date> col5;

    @FXML
    private TableColumn<ReservationInfo, Time> col6;

    @FXML
    private TableColumn<ReservationInfo, Time> col7;

    @FXML
    private TableColumn<ReservationInfo, Double> col8;

    @FXML
    private TableColumn<ReservationInfo, String> col9;

    @FXML
    private TableView<ReservationInfo> confirmtabel;






    @FXML
    void viewstate(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {
                String query = "SELECT r.reservationid, u.username, h.hallname, s.servicename, r.date, r.starttime, r.endtime, r.totalprice, r.state " +
                        "FROM software.new_table_name r " +
                        "INNER JOIN software.users u ON r.userid = u.userid " +
                        "INNER JOIN software.halls h ON r.hallid = h.hallid " +
                        "LEFT JOIN software.services s ON r.serviceid = s.serviceid " +
                        "WHERE r.userid = ?";
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setInt(1, userId); // Using the userId obtained directly
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ArrayList<ReservationInfo> reservations = new ArrayList<>();
                        while (resultSet.next()) {
                            int reservationId = resultSet.getInt("reservationid");
                            String  userName = resultSet.getString("username");
                            String hallName = resultSet.getString("hallname");
                            String serviceName = resultSet.getString("servicename");
                            Date date = resultSet.getDate("date");
                            Time startTime = resultSet.getTime("starttime");
                            Time endTime = resultSet.getTime("endtime");
                            double totalPrice = resultSet.getDouble("totalprice");
                            String state = resultSet.getString("state");
                            reservations.add(new ReservationInfo(reservationId, userName, hallName, serviceName, date, startTime, endTime, totalPrice, state));
                        }
                        col1.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
                        col2.setCellValueFactory(new PropertyValueFactory<>("userName"));
                        col3.setCellValueFactory(new PropertyValueFactory<>("hallName"));
                        col4.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
                        col5.setCellValueFactory(new PropertyValueFactory<>("date"));
                        col6.setCellValueFactory(new PropertyValueFactory<>("startTime"));
                        col7.setCellValueFactory(new PropertyValueFactory<>("endTime"));
                        col8.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
                        col9.setCellValueFactory(new PropertyValueFactory<>("state"));


                        // Clear existing data in the TableView
                        confirmtabel.getItems().clear();

                        // Populate TableView
                        confirmtabel.getItems().addAll(reservations);
                    }
                }
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void confirnation(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            ReservationInfo selectedReservation = confirmtabel.getSelectionModel().getSelectedItem();
            if (selectedReservation != null && selectedReservation.getState().equals("accepted")) {
                int hallId = getHallId(selectedReservation.getHallName(), conn);
                if (hallId != -1) {
                    String query = "INSERT INTO software.reservations (reservationid,userid, hallid, date, starttime, endtime, totalprice, serviceid, state) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = conn.prepareStatement(query)) {
                        statement.setInt(1, selectedReservation.getReservationId());
                        statement.setInt(2, getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn));
                        statement.setInt(3, hallId);
                        statement.setDate(4, (Date) selectedReservation.getDate());
                        statement.setTime(5, selectedReservation.getStartTime());
                        statement.setTime(6, selectedReservation.getEndTime());
                        statement.setDouble(7, selectedReservation.getTotalPrice());
                        statement.setInt(8, getServiceId(selectedReservation.getServiceName(), conn));
                        statement.setString(9, selectedReservation.getState());
                        statement.executeUpdate();
                        showAlert("data inserted successfully");


                    }
                } else {
                    showAlert("Hall not found for reservation: " + selectedReservation.getReservationId());
                }
            } else {
                showAlert("No reservation selected or selected reservation cannot be confirmed.");
            }
        } catch (SQLException e) {
            showAlert("reservation is already exist");
        }
    }





    private int getHallId(String hallName, Connection conn) throws SQLException {
        int hallId = -1;
        String query = "SELECT hallid FROM software.halls WHERE hallname = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, hallName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hallId = resultSet.getInt("hallid");
                }
            }
        }
        return hallId;
    }

    private int getServiceId(String serviceName, Connection conn) throws SQLException {
        int serviceId = -1;
        String query = "SELECT serviceid FROM software.services WHERE servicename = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, serviceName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    serviceId = resultSet.getInt("serviceid");
                }
            }
        }
        return serviceId;
    }
    @FXML
    void deletestate(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003")) {
            ReservationInfo selectedReservation = confirmtabel.getSelectionModel().getSelectedItem();
            if (selectedReservation != null && selectedReservation.getState().equals("wait")) {
                String query = "DELETE FROM software.new_table_name WHERE reservationid = ?";
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setInt(1, selectedReservation.getReservationId());
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        showAlert("Data deleted successfully");
                    } else {
                        showAlert("No reservation found with ID: " + selectedReservation.getReservationId());
                    }
                }
            } else {
                showAlert("No reservation selected or selected reservation cannot be deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("An error occurred while deleting reservation.");
        }
    }



}