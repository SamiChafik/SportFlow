package com.example.sportflow.controller;

import com.example.sportflow.DAO.SeanceDAO;
import com.example.sportflow.model.Seance;
import com.example.sportflow.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/session")
public class SeanceServlet extends HttpServlet {
    private SeanceDAO seanceDAO = new SeanceDAO();

    @Override
    public void init() {
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
                case "add":
                    addSeance(request, response);
                    break;

            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> members = seanceDAO.selectAllMembers();
        List<User> entraineurs = seanceDAO.selectAllEntraineurs();

        request.setAttribute("members", members);
        request.setAttribute("entraineurs", entraineurs);

        RequestDispatcher dispatcher = request.getRequestDispatcher("addSeance.jsp");
        dispatcher.forward(request, response);
    }

    private void addSeance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String seance_name = request.getParameter("seance_name");
        String dateHeure = request.getParameter("date_heure");
        int id_member = Integer.parseInt(request.getParameter("id_member"));
        int id_entraineur = Integer.parseInt(request.getParameter("id_entraineur"));

        Seance seance = new Seance(seance_name, dateHeure, id_member, id_entraineur);

        boolean isAdded = seanceDAO.addSeance(seance);

        if (isAdded) {
            response.sendRedirect("session?action=new");
        } else {
            request.setAttribute("errorMessage", "Failed to add seance");
            RequestDispatcher dispatcher = request.getRequestDispatcher("createSeance.jsp");
            dispatcher.forward(request, response);
        }
    }
}
