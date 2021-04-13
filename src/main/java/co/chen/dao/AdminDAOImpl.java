package co.chen.dao;

import static co.chen.dao.factory.DAOTools.*;

import java.sql.*;

import co.chen.bean.Admin;
import co.chen.dao.factory.DAOFactory;
import co.chen.utils.ClientState;

public class AdminDAOImpl implements AdminDAO {
    
    private DAOFactory daoFactory;

    public AdminDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Admin getAdminById(String identifier) {

        Admin admin = new Admin();
        Boolean resultSetEmpty = true;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {

            /* Récupération d'une connexion depuis la Factory */
            connection = daoFactory.getConnection();
            String sql = "SELECT * FROM admin WHERE identifier=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, identifier);
            resultSet = preparedStatement.executeQuery();

            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                admin.setAdminId(resultSet.getInt("adminId"));
                admin.setIdentifier(resultSet.getString("identifier"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admin.setSurname(resultSet.getString("surname"));
                admin.setMail(resultSet.getString("mail"));
                admin.setRemainingLoginAttempts(resultSet.getInt("remainingLoginAttempts"));
                admin.setState(ClientState.fromString(resultSet.getString("state")));
                admin.setCreationDate(resultSet.getTimestamp("creationDate"));
                resultSetEmpty = false;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            silentClosing(resultSet, preparedStatement, connection);
        }

        if (resultSetEmpty == true) return null;
        else return admin;
    }
}
