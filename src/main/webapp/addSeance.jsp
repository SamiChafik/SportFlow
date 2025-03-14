<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.sportflow.model.User" %>
<%
    // Retrieve lists of members and entraineurs from the request
    List<User> members = (List<User>) request.getAttribute("members");
    List<User> entraineurs = (List<User>) request.getAttribute("entraineurs");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Seance</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            background: url(/assets/images/background_3.jpg);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
        }

        #form {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 50%;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<main>
    <section>
        <div id="form">
            <div class="form-container">
                <h2 class="text-center mb-4">Create a New Seance</h2>
                <form action="seance?action=add" method="post">
                    <!-- Seance Name -->
                    <div class="form-group">
                        <label for="seanceName">Seance Name</label>
                        <input type="text" class="form-control" id="seanceName" name="seanceName" placeholder="Enter seance name" required>
                    </div>

                    <!-- Date and Time -->
                    <div class="form-group">
                        <label for="dateHeure">Date and Time</label>
                        <input type="datetime-local" class="form-control" id="dateHeure" name="dateHeure" required>
                    </div>

                    <!-- Member Selector -->
                    <div class="form-group">
                        <label for="member">Member</label>
                        <select class="form-control" id="member" name="member_id" required>
                            <option value="">Select a member</option>
                            <% if (members != null) {
                                for (User member : members) { %>
                            <option value="<%= member.getId() %>"><%= member.getFirst_name() %> <%= member.getLast_name() %></option>
                            <% }
                            } %>
                        </select>
                    </div>

                    <!-- Entraineur Selector -->
                    <div class="form-group">
                        <label for="entraineur">Entraineur</label>
                        <select class="form-control" id="entraineur" name="entraineur_id" required>
                            <option value="">Select an entraineur</option>
                            <% if (entraineurs != null) {
                                for (User entraineur : entraineurs) { %>
                            <option value="<%= entraineur.getId() %>"><%= entraineur.getFirst_name() %> <%= entraineur.getLast_name() %></option>
                            <% }
                            } %>
                        </select>
                    </div>

                    <!-- Submit Button -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Create Seance</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</main>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>