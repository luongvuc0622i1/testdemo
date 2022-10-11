package com.codegym.demom3exam.dao;

import com.codegym.demom3exam.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    void insertStudent(Student student);

    Student selectStudentById(int id);

    List<Student> selectAllStudents();

    boolean deleteStudent(int id);

    boolean updateStudent(Student student);

    List<Student> selectStudentByName(String name);
}
