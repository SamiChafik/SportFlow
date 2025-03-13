<%@ page import="com.example.sportflow.model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
    String firstName = user != null ? user.getFirst_name() : "";
    String lastName = user != null ? user.getLast_name() : "";
%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <!-- Brand/Logo with User's Name -->
        <a class="navbar-brand" href="#">
            Bienvenue, <%= firstName %> <%= lastName %> | <%= role %>
        </a>

        <!-- Toggle Button for Mobile -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <!-- Actions for admin -->
                <% if ("admin".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/user?action=listuser">
                        <button class="btn btn-warning">Liste des utilisateurs</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user?action=new">
                        <button class="btn btn-success">Ajouter un utilisateur</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-info">Ajouter une session</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-secondary">Liste des sessions</button>
                    </a>
                </li>
                <% } %>

                <!-- Actions for member -->
                <% if ("member".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-info">Ajouter une session</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-secondary">Liste des sessions</button>
                    </a>
                </li>
                <% } %>

                <!-- Actions for entraineur -->
                <% if ("entraineur".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-info">Ajouter une session</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/session?action=">
                        <button class="btn btn-secondary">Liste des sessions</button>
                    </a>
                </li>
                <% } %>

                <!-- Logout button for all roles -->
                <li class="nav-item">
                    <a class="nav-link" href="/logout">
                        <button class="btn btn-danger">Déconnexion</button>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>