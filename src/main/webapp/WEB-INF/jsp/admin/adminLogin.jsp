<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Admin - Connection</title>
    <link rel="stylesheet" href="/static/css/main.css">
</head>

<body>
    <c:import url="/static/html/top.html" charEncoding="UTF-8" />

    <div class="content page-center">
        <c:if test="${error}">
            <div class="info error blur">
                Erreur : <c:out value="${errormessage}" default="Problème serveur" />
            </div>
        </c:if>
        <main class="blur form">
            <h1>Connection admin</h1>

            <form action="adminLogin" method="post">
                <label for="identifier" id="lbl-identifier">Identifiant <sup class="required">*</sup><span
                        class="form-error">${form.errors['identifier']}</span></label>
                <input type="text" name="identifier" id="identifier" value='<c:out value="${cookie.identifier.value}"/>'>

                <label for="password">Mot de passe <sup class="required">*</sup><span
                        class="form-error">${form.errors['password']}</span></label>
                <input type="password" name="password" id="password">

                <div class="spacer"></div>

                <input type="submit" value="Connection" id="btn-submit">
            </form>
        </main>
    </div>
</body>

</html>