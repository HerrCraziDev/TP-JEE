package co.chen.dao.factory;

import java.sql.*;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import co.chen.dao.ClientDAO;
import co.chen.dao.ClientDAOImpl;
import co.chen.dao.DAOConfigurationException;

public class DAOFactory {
    
    // private String url;
    // private String user;
    // private String password;
    private BoneCP connectionPool;
    
    DAOFactory(String url, String user, String password) {
        
    }

    DAOFactory(BoneCP connectionPool) {
        this.connectionPool = connectionPool;
    }
    
    public static DAOFactory getInstance() throws DAOConfigurationException {

        String driver="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/jee?serverTimezone=UTC";
        String user = "supercastex";
        String password = "raskarkapak";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Driver not found in classpath.", e);
        }

        // Create instance
        BoneCP boneCP = null;
        try {
            /*
             * Création d'une configuration de pool de connexions via l'objet BoneCPConfig
             */
            BoneCPConfig config = new BoneCPConfig();
            /* Mise en place de l'URL, du nom et du mot de passe */
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);
            /* Paramétrage de la taille du pool */
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);
            /* Création du pool à partir de la configuration, via l'objet BoneCP */
            boneCP = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOConfigurationException("Erreur configuration pool de connexions.", e);
        }
        /* Enregistrement du pool par un appel au constructeur de DAOFactory */
        DAOFactory instance = new DAOFactory(boneCP);
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public ClientDAO getClientDAO() {
        return new ClientDAOImpl(this);
    }
}
