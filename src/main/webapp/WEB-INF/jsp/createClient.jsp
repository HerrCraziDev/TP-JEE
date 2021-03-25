<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patate carrée</title>
    <link rel="stylesheet" href="/static/css/main.css">
</head>
<body>
    <c:import url="/static/html/top.html" charEncoding="UTF-8"/>

    
    <div class="page-center">
        <c:if test="${error}">
            <span class="info error blur"> Erreur : Formulaire incomplet</span>
        </c:if>
        <main class="blur">        
            <h1>Création de client</h1>
            
            <form action="createClient" method="post">
                <label for="name" id="lbl-name">Nom</label>
                <input type="text" name="name" id="name">
        
                <label for="surname">Prénom</label>
                <input type="text" name="surname" id="surname">
        
                <label for="city">Ville</label>
                <input type="text" name="city" id="city">
        
                <div class="spacer"></div>
        
                <label for="postcode">Code postal</label>
                <input type="text" name="postcode" id="postcode">
        
                <label for="address">Adresse</label>
                <input type="text" name="address" id="address">
        
                <label for="phone">Téléphone</label>
                <input type="tel" name="phone" id="phone">
        
                <label for="mail">E-mail</label>
                <input type="email" name="mail" id="mail">
        
                <div class="spacer"></div>
        
                <label for="passwd">Mot de passe</label>
                <input type="password" name="passwd" id="passwd">
        
                <label for="passwd-confirm">Confirmer le mot de passe</label>
                <input type="password" name="passwd-confirm" id="passwd-confirm">
        
                <div class="spacer"></div>
        
                <input type="submit" value="Créer" id="btn-submit">
            </form>
        </main>
    </div>
</body>
</html>