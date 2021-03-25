<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
            <div class="blur info success">
                <h2>Client créé</h2>
                Client créé avec succès.
                <a href='<c:url value="/createClient"></c:url>'' class="btn">Retour</a>
            </div>
        </div>
    </body>

    </html>