package com.example.sportflow.authentification;

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

@WebServlet({"/", "/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
        userDAO.createUserTable();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/log_in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.checkLogin(email, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("user", user);


            switch (user.getRole()) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/user?action=listuser");
                    break;
                case "member":
                    response.sendRedirect(request.getContextPath() + "/user?action=listuser");
                    break;
                case "entraineur":
                    response.sendRedirect(request.getContextPath() + "/user?action=listuser");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid role");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/log_in.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/log_in.jsp");
            dispatcher.forward(request, response);
        }
    }
}