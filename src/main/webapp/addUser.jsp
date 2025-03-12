<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Ajouter</title>
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
            height: 657px;
        }

        .form1 {
            color: white;
            font-size: 24px;
            background-color: rgb(177, 177, 177);
            padding: 3%;
            width: 60%;
            border-radius: 10px;
            background: rgba(23, 23, 23, 0.5);
            backdrop-filter: blur(8px);
            box-shadow: 0px 1px 5px rgb(61, 61, 61);
        }

        #btn {
            display: flex;
            justify-content: center;
            width: 100%;
            gap : 20px;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<main>
    <section>
        <div id="form">
            <form id="form1" class="form1" action="user?action=adduser" method="post">
                <h2>Ajouter un utilisateur</h2>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNom">Nom</label>
                        <input type="text" class="form-control" id="inputNom" name="last_name" placeholder="Nom">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPrenom">Prénom</label>
                        <input type="text" class="form-control" id="inputPrenom" name="first_name" placeholder="Prénom">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="Date">Date de naissance</label>
                        <input type="date" class="form-control" id="Date" name="birth_date" placeholder="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email@exemple.com">
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
                </div>
                <!-- Role Selection -->
                <div class="form-group">
                    <label>Rôle</label>
                    <div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="rolemember" value="member" >
                            <label class="form-check-label" for="rolemember">Member</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleentraineur" value="entraineur">
                            <label class="form-check-label" for="roleentraineur">Entraineur</label>
                        </div>
                    </div>
                </div>
                <!-- Sports List (for Member) -->
                <div class="form-group hidden" id="sportsSection">
                    <label for="sports">Sports</label>
                    <select class="form-control" id="sports" name="sport">
                        <option value="No sport">No sport</option>
                        <option value="football">Football</option>
                        <option value="basketball">Basketball</option>
                        <option value="tennis">Tennis</option>
                        <option value="swimming">Swimming</option>
                    </select>
                </div>
                <!-- Specialities List (for Entraineur) -->
                <div class="form-group hidden" id="specialitiesSection">
                    <label for="specialities">Specialities</label>
                    <select class="form-control" id="specialities" name="speciality">
                        <option value="No speciality">No speciality</option>
                        <option value="Strength Training">Strength Training</option>
                        <option value="cardio">Cardio</option>
                        <option value="yoga">Yoga</option>
                        <option value="nutrition">Nutrition</option>
                    </select>
                </div>
                <div id="btn">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const roleMember = document.getElementById('rolemember');
        const roleEntraineur = document.getElementById('roleentraineur');
        const sportsSection = document.getElementById('sportsSection');
        const specialitiesSection = document.getElementById('specialitiesSection');

        roleMember.addEventListener('change', function() {
            if (this.checked) {
                sportsSection.classList.remove('hidden');
                specialitiesSection.classList.add('hidden');
            }
        });

        roleEntraineur.addEventListener('change', function() {
            if (this.checked) {
                specialitiesSection.classList.remove('hidden');
                sportsSection.classList.add('hidden');
            }
        });
    });
</script>
</body>
</html>