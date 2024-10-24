package fr.fms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.Training;

public class TestJdbc {
    public static void main(String[] args) {
        ArrayList<Training> trainingList = new ArrayList<>();
        
        // Database connection details
        String url = "jdbc:mariadb://localhost:3306/training";
        String login = "root";
        String password = "fms2024";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MariaDB driver not found. Ensure it's in your classpath.");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM Training")) {

                while (resultSet.next()) {
                    int idTraining = resultSet.getInt("IdTraining");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    int durationDays = resultSet.getInt("DurationDays");
                    String format = resultSet.getString("Format");
                    double price = resultSet.getDouble("Price");
                    int idCategory = resultSet.getInt("IdCategory");

                    Training training = new Training(idTraining, name, description, durationDays, format, price, idCategory);
                    trainingList.add(training);
                }

                trainingList.forEach(System.out::println);

            } catch (Exception e) {
                System.err.println("Error while executing the SQL query.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Connection to the database failed.");
            e.printStackTrace();
        }
    }
}
