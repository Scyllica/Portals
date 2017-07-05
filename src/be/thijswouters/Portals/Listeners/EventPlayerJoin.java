package be.thijswouters.Portals.Listeners;

import be.thijswouters.Portals.API.PortalStaff;
import be.thijswouters.Portals.SQL.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Thijs on 28/03/2017.
 * This event is just for testing purposes.
 */
public class EventPlayerJoin implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String UUID = p.getUniqueId().toString();
        p.getInventory().setItem(0, PortalStaff.getStaff());
        if(!Cooldown.playerExists(UUID)){
            Cooldown.createPlayer(UUID);
        }
    }
}
