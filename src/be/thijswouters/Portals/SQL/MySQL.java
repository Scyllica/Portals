package be.thijswouters.Portals.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";

    public static Connection con;

    public MySQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/"
                    + DATABASE + "?autoReconnect=true", USER, PASSWORD);
            System.out.println("[MySQL] The connection to MySQL is made!");
        } catch (SQLException e) {
            System.out
                    .println("[MySQL] The connection to MySQL couldn't be made! reason: "
                            + e.getMessage());
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out
                        .println("[MySQL] The connection to MySQL is ended successfully!");
            }
        } catch (SQLException e) {
            System.out
                    .println("[MySQL] The connection couldn't be closed! reason: "
                            + e.getMessage());
        }
    }

    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public boolean hasConnection() {
        if (con != null) {
            return true;
        }
        return false;
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;

        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}
