package be.thijswouters.Portals.Listeners;

import be.thijswouters.Portals.Files.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Thijs Wouters - Methum on 4/04/2017.
 */
public class EventPlayerMove implements Listener {

    @EventHandler
    public void onOnPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        for (String s : Files.portalLocations.getConfigurationSection("Locations").getKeys(false)) {
            Location loc = new Location(Bukkit.getWorld(Files.portalLocations.getString("Locations." + s + ".Location.World")),
                    Files.portalLocations.getDouble("Locations." + s + ".Location.X"),
                    Files.portalLocations.getDouble("Locations." + s + ".Location.Y"),
                    Files.portalLocations.getDouble("Locations." + s + ".Location.Z"));

            if (e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) {
                return;
            }

            if(e.getTo().getBlock().equals(loc.getBlock())){
                if(s.contains("BALL_1")){
                    String portal = s.replace("BALL_1", "BALL_2");
                    Location ploc = new Location(Bukkit.getWorld(Files.portalLocations.getString("Locations." + portal + ".Location.World")),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.X"),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.Y"),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.Z"));
                    p.teleport(ploc);
                } else {
                    String portal = s.replace("BALL_2", "BALL_1");
                    Location ploc = new Location(Bukkit.getWorld(Files.portalLocations.getString("Locations." + portal + ".Location.World")),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.X"),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.Y"),
                            Files.portalLocations.getDouble("Locations." + portal + ".Location.Z"));
                    p.teleport(ploc);
                }
            }
        }
    }
}
