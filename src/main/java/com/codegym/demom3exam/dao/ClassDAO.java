package com.codegym.demom3exam.dao;

import com.codegym.demom3exam.model.Classroom;
import com.codegym.demom3exam.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/examM3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "22072022";

    private static final String SELECT_ALL_CLASSES = "SELECT * FROM Classroom;";

    public ClassDAO() {
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public List<Classroom> selectAllClasses() {
        List<Classroom> classes = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("class_id");
                String name = resultSet.getString("class_name");
                classes.add(new Classroom(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return classes;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
