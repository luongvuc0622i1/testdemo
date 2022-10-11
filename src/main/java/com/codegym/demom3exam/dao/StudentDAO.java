package com.codegym.demom3exam.dao;

import com.codegym.demom3exam.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/examM3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "22072022";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO Student(name, date_of_birth, address, phone, email, class_id) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM Student WHERE id = ?;";
    private static final String SELECT_STUDENT_BY_NAME = "SELECT * FROM Student WHERE name LIKE ?;";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM Student;";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM Student WHERE id = ?;";
    private static final String UPDATE_STUDENT_SQL = "UPDATE Student SET name = ?, date_of_birth = ?, address = ?, phone = ?, email = ?, class_id = ? WHERE id = ?;";

    public StudentDAO() {
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

    @Override
    public void insertStudent(Student student) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getDateOfBirth());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setString(4,student.getPhone());
            preparedStatement.setString(5,student.getEmail());
            preparedStatement.setInt(6,student.getClassroom());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Student selectStudentById(int id) {
        Student student = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("date_of_birth");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int class_id = rs.getInt("class_id");
                student = new Student(id, name, dateOfBirth, address, phone, email, class_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int class_id = resultSet.getInt("class_id");
                students.add(new Student(id, name, dateOfBirth, address, phone, email, class_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    @Override
    public boolean deleteStudent(int id){
        boolean rowDeleted = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean rowUpdated = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getDateOfBirth());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, student.getClassroom());
            preparedStatement.setInt(7, student.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public List<Student> selectStudentByName(String name) {
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_NAME);
            preparedStatement.setString(1, "%"+name+"%");
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String sName = rs.getString("name");
                String dateOfBirth = rs.getString("date_of_birth");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int class_id = rs.getInt("class_id");
                students.add(new Student(id, sName, dateOfBirth, address, phone, email, class_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
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
