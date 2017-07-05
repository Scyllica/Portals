package be.thijswouters.Portals.Listeners;

import be.thijswouters.Portals.Files.Files;
import be.thijswouters.Portals.Utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.material.MaterialData;

/**
 * Created by Thijs on 28/03/2017.
 */
public class EventProjectileHit implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent e){
        Entity ent = e.getEntity();
        if(ent.getName().contains("BALL_1")){
            Files.portalLocations.set("Locations." + ent.getName() + ".ID", "1");
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.World", ent.getLocation().getWorld().getName());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.X", ent.getLocation().getX());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.Y", ent.getLocation().getY());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.Z", ent.getLocation().getZ());
            Files.portalLocations.save();

            Player p = Bukkit.getPlayer(ent.getName().replace("BALL_1", ""));

            p.sendMessage("Created first portal");

        } else if(ent.getName().contains("BALL_2")){
            Files.portalLocations.set("Locations." + ent.getName() + ".ID", "2");
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.World", ent.getLocation().getWorld().getName());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.X", ent.getLocation().getX());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.Y", ent.getLocation().getY());
            Files.portalLocations.set("Locations." + ent.getName() + ".Location.Z", ent.getLocation().getZ());
            Files.portalLocations.save();

            Player p = Bukkit.getPlayer(ent.getName().replace("BALL_2", ""));

            p.sendMessage("Created second portal");
        }
    }
}
