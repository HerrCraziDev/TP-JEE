package co.chen.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import co.chen.bean.Client;
import co.chen.utils.ClientState;
import co.chen.dao.factory.DAOFactory;
import static co.chen.dao.factory.DAOTools.*;

public class ClientDAOImpl implements ClientDAO {
    private DAOFactory daoFactory;

    public ClientDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void createClient(Client client) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
            String query = "INSERT INTO client(name, surname, city, postcode, address, "
                    + "phone, mail, password, state, creationDate) " + "VALUES(?,?,?,?,?,?,?,?,?,?);";
            
            connection = daoFactory.getConnection();
            System.out.println("Open Connection succeeded");

            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getCity());
            statement.setString(4, client.getPostcode());
            statement.setString(5, client.getAddress());
            statement.setString(6, client.getPhone());
            statement.setString(7, client.getMail());
            statement.setString(8, client.getPasswd());
            statement.setString(9, client.getState().toString());
            statement.setObject(10, client.getCreationDate());

            statement.executeUpdate();

            result = statement.getGeneratedKeys();
            int key = 0;
            while (result.next()) {
                key = result.getInt(1);
            }

            client.setClientId(key);
            System.out.println("\33[32mNew client created: " + client + "\33[0m");

        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            silentClosing(result, statement, connection);
        }
    }

    public List<Client> fetchClients() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        List<Client> clients = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM client";

            connection = daoFactory.getConnection();
            System.out.println("Open Connection succeeded");

            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            result = statement.executeQuery();

            while (result.next()) {
                System.out.println(result.toString());

                clients.add(new Client(
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
            silentClosing(result, statement, connection);
        }

        return clients;
    }
}
