package co.chen.dao.factory;

import java.sql.*;

public class DAOTools {
    /* Fermeture silencieuse du resultset */
    public static void silentClosing(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Close ResultSet Error : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void silentClosing(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Close PreparedStatement Error : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void silentClosing( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Close Connection error : " + e.getMessage() );
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void silentClosing(PreparedStatement preparedStatement, Connection connection) {
        silentClosing(preparedStatement);
        silentClosing(connection);
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void silentClosing(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        silentClosing(resultSet);
        silentClosing(preparedStatement);
        silentClosing(connection);
    }
}
