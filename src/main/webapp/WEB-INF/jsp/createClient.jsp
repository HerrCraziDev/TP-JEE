<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patate carrée</title>
    <link rel="stylesheet" href="/static/css/main.css">
</head>
<body>
    <div class="page-center">
        <main class="blur">        
            <h1>Création de client</h1>
            
            <form action="createClient" method="post">
                <label for="name" id="lbl-name">Nom</label>
                <input type="text" name="name" id="name" required>
        
                <label for="surname">Prénom</label>
                <input type="text" name="surname" id="surname" required>
        
                <label for="city">Ville</label>
                <input type="text" name="city" id="city" required>
        
                <div class="spacer"></div>
        
                <label for="postcode">Code postal</label>
                <input type="text" name="postcode" id="postcode" required>
        
                <label for="address">Adresse</label>
                <input type="text" name="address" id="address" required>
        
                <label for="phone">Téléphone</label>
                <input type="tel" name="phone" id="phone">
        
                <label for="mail">E-mail</label>
                <input type="email" name="mail" id="mail" required>
        
                <div class="spacer"></div>
        
                <label for="passwd">Mot de passe</label>
                <input type="password" name="passwd" id="passwd" required>
        
                <label for="passwd-confirm">Confirmer le mot de passe</label>
                <input type="password" name="passwd-confirm" id="passwd-confirm" required>
        
                <div class="spacer"></div>
        
                <input type="submit" value="Créer" id="btn-submit">
            </form>
        </main>
    </div>
</body>
</html>