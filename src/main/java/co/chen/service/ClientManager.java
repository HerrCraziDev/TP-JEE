package co.chen.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.chen.bean.Client;
import co.chen.utils.ClientState;

public class ClientManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jee?serverTimezone=UTC";
    private static final String DB_USER = "supercastex";
    private static final String DB_PASSWD = "raskarkapak";
    
    public void createClient(Client client) {
        client.setCreationDate(new Date());
        client.setState(ClientState.CREATED);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Loading error : " + e.getMessage());
        }

        String query = "INSERT INTO client(name, surname, city, postcode, address, "
        + "phone, mail, password, state, creationDate) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            // **** Connexion à la base de données ****
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            System.out.println("Open Connection succeeded");

            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            
            statement.setString( 1, client.getName());
            statement.setString( 2, client.getSurname());
            statement.setString( 3, client.getCity());
            statement.setString( 4, client.getPostcode());
            statement.setString( 5, client.getAddress());
            statement.setString( 6, client.getPhone());
            statement.setString( 7, client.getMail());
            statement.setString( 8, client.getPasswd());
            statement.setString( 9, client.getState().toString());
            statement.setObject( 10, client.getCreationDate());

            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            int key = 0;
            while (result.next()) {
                key = result.getInt(1);
            }

            client.setClientId(key);
            System.out.println("\33[32mNew client created: " + client + "\33[0m");

        } catch (SQLException e) {
            System.out.println("Connection Error :" + e.getMessage());
        } finally {
            System.out.println("Close ResultSet");
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignore) {
                }
            }
            System.out.println("Close PreparedStatement");
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            System.out.println("Close Connection");
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    public List<Client> fetchClients() {
        String query = "SELECT * FROM clients";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Client> clients = new ArrayList<>();

        try {
            // **** Connexion à la base de données ****
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            System.out.println("Open Connection succeeded");

            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            result = statement.executeQuery();

            while (result.next()) {
                System.out.println(result.toString());

                clients.add( new Client(
                    result.getInt("clientId"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("city"),
                    result.getString("postcode"),
                    result.getString("address"),
                    result.getString("phone"),
                    result.getString("mail"),
                    result.getString("password"),
                    ClientState.fromString(result.getString("state")),
                    result.getDate("creationDate")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Connection Error :" + e.getMessage());
        } finally {
            System.out.println("Close ResultSet");
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ignore) {
                }
            }
            System.out.println("Close PreparedStatement");
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            System.out.println("Close Connection");
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return clients;
    }
}
