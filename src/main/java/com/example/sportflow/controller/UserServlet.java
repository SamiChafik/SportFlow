package com.example.sportflow.controller;

import com.example.sportflow.DAO.UserDAO;
import com.example.sportflow.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        userDAO.createUserTable();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "adduser":
                    addUser(request, response);
                    break;
                case "listuser":
                    listUser(request, response);
                    break;
                case "edituser":
                    break;
                case "deleteuser":
                    deleteUser(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        String birth_date = request.getParameter("birth_date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String sport = request.getParameter("sport");
        String speciality = request.getParameter("speciality");

        User newUser = new User();
        newUser.setLast_name(last_name);
        newUser.setFirst_name(first_name);
        newUser.setBirth_date(birth_date);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setSport(sport);
        newUser.setSpeciality(speciality);

        userDAO.addUser(newUser);

        response.sendRedirect("/user?action=listuser");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        String role = (String) session.getAttribute("role");
//
//        if (!"admin".equals(role)) {
//            response.sendRedirect("/log_in.jsp");
//            return;
//        }

        List<User> users = userDAO.selectAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listUser.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("id"));
        boolean deleted = userDAO.deleteUser(userId);

        if (deleted) {
            response.sendRedirect("user?action=listuser");
        } else {
            request.setAttribute("errorMessage", "Failed to delete user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("listUser.jsp");
            dispatcher.forward(request, response);
        }
    }

}
