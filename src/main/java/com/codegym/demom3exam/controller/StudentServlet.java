package com.codegym.demom3exam.controller;

import com.codegym.demom3exam.dao.ClassDAO;
import com.codegym.demom3exam.dao.IStudentDAO;
import com.codegym.demom3exam.dao.StudentDAO;
import com.codegym.demom3exam.model.Classroom;
import com.codegym.demom3exam.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    private IStudentDAO studentDAO;
    private ClassDAO classDAO;

    public void init() {
        studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = (List<Student>) studentDAO.selectAllStudents();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = new String(request.getParameter("search").getBytes("iso-8859-1"),"utf-8");
        List<Student> students = (List<Student>) studentDAO.selectStudentByName(name);
        request.setAttribute("students",students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        List<Student> students = (List<Student>) studentDAO.selectAllStudents();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = (Student) studentDAO.selectStudentById(id);
        List<Classroom> classes = (List<Classroom>) classDAO.selectAllClasses();
        RequestDispatcher dispatcher;
        if (student == null){
            dispatcher = request.getRequestDispatcher("student/fail.jsp");
        } else {
            request.setAttribute("student", student);
            request.setAttribute("classes", classes);
            dispatcher = request.getRequestDispatcher("student/edit.jsp");
        }
        dispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classroom> classes = (List<Classroom>) classDAO.selectAllClasses();
        request.setAttribute("classes", classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createStudent(request,response);
                break;
            case "edit":
                editStudent(request,response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
            case "find":
                findByName(request,response);
                break;
            default:
                listStudent(request,response);
                break;
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String dateOfBirth = new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8");
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phone = new String(request.getParameter("phone").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classroom = Integer.parseInt(request.getParameter("classroom"));
        Student student = new Student(id, name, dateOfBirth, address, phone, email, classroom);

        studentDAO.updateStudent(student);

        request.setAttribute("message","Sửa thông tin học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String dateOfBirth = new String(request.getParameter("dateOfBirth").getBytes("iso-8859-1"),"utf-8");
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String phone = new String(request.getParameter("phone").getBytes("iso-8859-1"),"utf-8");
        String email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
        int classroom = Integer.parseInt(request.getParameter("classroom"));
        Student newStudent = new Student(name, dateOfBirth, address, phone, email, classroom);

        studentDAO.insertStudent(newStudent);
        request.setAttribute("message", "Thêm học viên thành công!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
    }
}
