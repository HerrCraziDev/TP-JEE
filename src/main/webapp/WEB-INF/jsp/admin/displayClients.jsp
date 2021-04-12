<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Patate carrée</title>
    <link rel="stylesheet" href="/static/css/main.css">
    <link rel="stylesheet" href="/static/css/admin.css">
</head>

<body>
    <c:import url="/static/html/top.html" charEncoding="UTF-8" />

    <div class="content">
        <main class="blur page list">
            <h2>Liste clients</h2>

            <table>
                <thead>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>E-mail</th>
                    <th>Ville</th>
                    <th>Code postal</th>
                    <th>Addresse</th>
                    <th>Téléphone</th>
                    <th>Date création</th>
                    <th>Statut</th>
                </thead>
                <tbody>
                    <c:forEach var="client" items="${clients}">
                        <tr>
                            <td>${client.clientId}</td>
                            <td>${client.name}</td>
                            <td>${client.surname}</td>
                            <td>${client.mail}</td>
                            <td>${client.city}</td>
                            <td>${client.postcode}</td>
                            <td>${client.address}</td>
                            <td>${client.phone}</td>
                            <td>${client.creationDate}</td>
                            <td>${client.state}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </div>
</body>

</html>