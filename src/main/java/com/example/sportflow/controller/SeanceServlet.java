package com.example.sportflow.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SeanceServlet extends HttpServlet {
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
//                    showNewForm(request, response);
                    break;
                case "adduser":
//                    addSeance(request, response);
                    break;

            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
