package be.thijswouters.Portals.SQL;

import be.thijswouters.Portals.Portals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Thijs Wouters - Methum on 5/04/2017.
 */
public class Cooldown {

    public void startCdTimer(){

    }

    public static synchronized boolean playerExists(String UUID){
        try{
            PreparedStatement sql = MySQL.con.prepareStatement("SELECT * FROM PortalsCD WHERE UUID= '"
                    + UUID + "'");

            ResultSet rs = sql.executeQuery();

            if(rs.next()){
                return rs.getString("UUID") != null;
            }

            rs.close();
            sql.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public static void createPlayer(String UUID){
        if(!(playerExists(UUID))){
            Portals.getInstance().mysql.update("INSERT INTO PortalsCD(UUID, Cooldown) VALUES ('" + UUID + "', '0');");
        }
    }

    public static synchronized String getCooldown(String UUID){
        String s = "";
        if(playerExists(UUID)){
            try{
                ResultSet rs = Portals.getInstance().mysql.query("SELECT * FROM PortalsCD WHERE UUID= '"
                        + UUID + "';");
                if ((!rs.next()));
                s = rs.getString("Cooldown");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return s;
    }

    public static void setCooldown(String UUID, int i){
        if(playerExists(UUID)){
            Portals.getInstance().mysql.update("UPDATE PortalsCD SET Cooldown= '" + i + "' WHERE UUID= '"
                    + UUID + "';");
        }
    }
}
