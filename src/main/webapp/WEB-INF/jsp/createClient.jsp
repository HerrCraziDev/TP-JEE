<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Création compte client</title>
    <link rel="stylesheet" href="/static/css/main.css">
</head>
<body>
    <c:import url="/static/html/top.html" charEncoding="UTF-8"/>
    
    <div class="content page-center">
        <c:if test="${error}">
            <div class="info error blur">
                Erreur : Formulaire incomplet
            </div>
        </c:if>
        <main class="blur">        
            <h1>Création de client</h1>
            
            <form action="createClient" method="post">
                <label for="name" id="lbl-name">Nom <sup class="required">*</sup><span class="form-error">${form.errors['name']}</span></label>
                <input type="text" name="name" id="name" value='<c:out value="${param.name}"/>'>
                

                <label for="surname">Prénom <sup class="required">*</sup><span class="form-error">${form.errors['surname']}</span></label>
                <input type="text" name="surname" id="surname" value='<c:out value="${param.surname}"/>'>
                

                <label for="city">Ville <sup class="required">*</sup><span class="form-error">${form.errors['city']}</span></label>
                <input type="text" name="city" id="city" value='<c:out value="${param.city}"/>'>
                

                <div class="spacer"></div>
        
                <label for="postcode">Code postal <sup class="required">*</sup><span class="form-error">${form.errors['postcode']}</span></label>
                <input type="text" name="postcode" id="postcode" value='<c:out value="${param.postcode}"/>'>
                

                <label for="address">Adresse <sup class="required">*</sup><span class="form-error">${form.errors['address']}</span></label>
                <input type="text" name="address" id="address" value='<c:out value="${param.address}"/>'>
                

                <label for="phone">Téléphone<span class="form-error">${form.errors['phone']}</span></label>
                <input type="tel" name="phone" id="phone" value='<c:out value="${param.phone}"/>'>
                

                <label for="mail">E-mail <sup class="required">*</sup><span class="form-error">${form.errors['mail']}</span></label>
                <input type="email" name="mail" id="mail" value='<c:out value="${param.mail}"/>'>
                

                <div class="spacer"></div>
        
                <label for="passwd">Mot de passe <sup class="required">*</sup><span class="form-error">${form.errors['passwd']}</span></label>
                <input type="password" name="passwd" id="passwd" value='<c:out value="${param.passwd}"/>'>
                

                <label for="passwdconfirm">Confirmer le mot de passe <sup class="required">*</sup><span class="form-error">${form.errors['passwdconfirm']}</span></label>
                <input type="password" name="passwdconfirm" id="passwdconfirm" value='<c:out value="${param.passwdconfirm}"/>'>
        
                <div class="spacer"></div>
        
                <input type="submit" value="Créer" id="btn-submit">
            </form>
        </main>
    </div>
</body>
</html>