<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Patate carrée</title>
        <link rel="stylesheet" href="/static/css/main.css">
        <link rel="stylesheet" href="/static/css/display.css">
    </head>

    <body>
        <c:import url="/static/html/top.html" charEncoding="UTF-8"/>

        <div class="content page-center">
            <div class="blur info success">
                Client créé avec succès.
            </div>
            
            <main class="blur">
                <a href='<c:url value="/createClient"></c:url>'' class="btn">« Retour</a>
                <h2><c:out value="${param.surname}" /> <c:out value="${param.name}"/></h2>
                <div class="client-info">Ville : <b><c:out value="${param.city}"/></b></div>
                <div class="client-info">Code postal : <b><c:out value="${param.postcode}"/></b></div>
                <div class="client-info">Addresse : <b><c:out value="${param.address}"/></b></div>
                <div class="client-info">E-mail : <b><c:out value="${param.mail}"/></b></div>
                <div class="client-info">Tél. : <b><c:out value="${param.phone}"/></b></div>
            </main>
        </div>
    </body>

    </html>