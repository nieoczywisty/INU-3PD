package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {

    Connection connection = null;

    public OraConn() {
        registerDriver();
    }

    public void close() {
        closeConnection();
    }

    private void registerDriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Sterownik Oracle JDBC zostaĹ zarejestrowany.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Brak sterownika JDBC.");
        }
    }

    public void setConnection(String host, String user, String password) {
        try {
            connection = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            System.out.println("BĹÄd polaczenia. SprawdĹş nazwy hosta, uĹźytkownika i hasĹo."
                    + System.lineSeparator()
                    + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}