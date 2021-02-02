package br.com.grupoVoid.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maryucha
 */
public class ConnectionFactory {

     private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_biblioteca?serverTimezone=UTC";
    
    private static final String USER = "root";
    private static final String PASS = "";
    

    public static Connection getConnection() throws SQLException {

        try {

            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Erro na conexão.", ex);
        }

    }

    public static void fecharConexao(Connection con) throws SQLException {
        try {
            if (con != null) {
                con.close();

            }
        } catch (Exception e) {
            System.err.println("Conexão aberta");
        }

    }

    public static void fecharConexao(Connection con, PreparedStatement stm) throws SQLException {
        try {
            if (stm != null) {
                stm.close();
            }
            fecharConexao(con);
        } catch (SQLException e) {
            System.err.println("Conexão aberta");
        }

    }

    public static void fecharConexao(Connection con, PreparedStatement stm, ResultSet rs) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
            fecharConexao(con, stm);
        } catch (SQLException e) {
            System.err.println("Conexão aberta");
        }

    }
}
