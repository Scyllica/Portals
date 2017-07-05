package be.thijswouters.Portals.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Thijs Wouters - Methum on 6/04/2017.
 */
public class Chat {

    public static void normal(Player p, String prefix ,String msg){
        p.sendMessage("§a» §7" + prefix + " §8§l: §7" +  msg);
    }

    public static void sec(Player p, String prefix ,String msg){
        p.sendMessage("    §a- §7" + prefix + " §8§l: §7" +  msg);
    }

    public static void broadcast(String prefix ,String msg){
        Bukkit.broadcastMessage("§a» §7" + prefix + " §8§l: §7" + msg);
    }
    
    public static void broadcast(String msg){
        Bukkit.broadcastMessage(msg);
    }
}
