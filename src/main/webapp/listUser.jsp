<%@ page import="com.example.sportflow.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/listUsers.css">
    <title>List of Users</title>
</head>
<body>
<jsp:include page="header.jsp" />
<main>
    <section>
        <h2>Liste d'utilisateurs</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Prenom</th>
                <th>Nom</th>
                <th>Date de naissance</th>
                <th>Email</th>
                <th>Mot de passe </th>
                <th>Sport</th>
                <th>Specialite</th>
                <th>Role</th>
                <th>Option</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if (users != null && !users.isEmpty()) {
                    for (User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getFirst_name() %></td>
                <td><%= user.getLast_name() %></td>
                <td><%= user.getBirth_date() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getSport() == null ? "pas de sport" : user.getSport() %></td>
                <td><%= user.getSpeciality() == null ? "aucune spécialité" : user.getSpeciality() %></td>
                <td><%= user.getRole() %></td>
                <td>
                    <!-- Edit Button -->
                    <a href="/user?action=editform&id=<%= user.getId() %>" class="btn btn-warning">Modifier</a>
                    <!-- Delete Button -->
                    <form action="/user?action=deleteuser" method="post" style="display: inline;" onsubmit="return confirm('Are you sure you want to delete this user?');">
                        <input type="hidden" name="id" value="<%= user.getId() %>">
                        <button type="submit" class="btn btn-danger">Supprimer</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center;">No users found.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>