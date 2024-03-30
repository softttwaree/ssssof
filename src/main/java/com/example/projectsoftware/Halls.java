package com.example.projectsoftware;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;

public class Halls {



        private String name;
        private double price;
        private int capacity;

        public Halls(){}
        public Halls(String name, double price, int capacity) {
            this.name = name;
            this.price = price;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getCapacity() {
            return capacity;
        }
        @Override
        public String toString() {
            return name; // Return the name of the hall for display in ListView
        }
        public static boolean isHallExists(String hallName) {
            String query = "SELECT COUNT(*) FROM software.Halls WHERE hallname = ?";
            boolean hallExists = false;

            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1482003");
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, hallName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        hallExists = count > 0;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception based on your application's needs
            }

            return hallExists;
        }



}


