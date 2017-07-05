package be.thijswouters.Portals;

import be.thijswouters.Portals.Files.Files;
import be.thijswouters.Portals.Listeners.EventPlayerInteract;
import be.thijswouters.Portals.Listeners.EventPlayerJoin;
import be.thijswouters.Portals.Listeners.EventPlayerMove;
import be.thijswouters.Portals.Listeners.EventProjectileHit;
import be.thijswouters.Portals.SQL.MySQL;
import be.thijswouters.Portals.Utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

/**
 * Created by Thijs Wouters - Methum on 27/03/2017.
 */
public class Portals extends JavaPlugin {

    public static Portals instance;
    public MySQL mysql;

    public void onEnable(){
        instance = this;

        File file = new File("plugins/Portals/", "PortalLocations.yml");
        File file2 = new File("plugins/Portals/", "Cooldowns.yml");
        file.delete();
        file2.delete();

        registerEvents();
        registerFiles();
        runParticles();

        connectMySQL();
    }

    public void onDisable(){
        ArrayLists.firstPortalCreated.clear();
        ArrayLists.secondPortalCreated.clear();
        mysql.close();
    }

    public void connectMySQL() {
        String host = Files.mysql.getString("MySQL.Host");
        String port = Files.mysql.getString("MySQL.Port");
        String database = Files.mysql.getString("MySQL.Database");
        String username = Files.mysql.getString("MySQL.Username");
        String password = Files.mysql.getString("MySQL.Password");

        mysql = new MySQL(host + ":" + port, database, username, password);
        mysql.update("CREATE TABLE IF NOT EXISTS PortalsCD (UUID varchar(36),Cooldown int)");
    }

    private void registerFiles(){
        Files.portalLocations.set("Locations.XXXBALL_1.ID", "1");
        Files.portalLocations.set("Locations.XXXBALL_1.Location.World", "world");
        Files.portalLocations.set("Locations.XXXBALL_1.Location.X", 0);
        Files.portalLocations.set("Locations.XXXBALL_1.Location.Y", 0);
        Files.portalLocations.set("Locations.XXXBALL_1.Location.Z", 0);

        Files.portalLocations.set("Locations.XXXBALL_2.ID", "2");
        Files.portalLocations.set("Locations.XXXBALL_2.Location.World", "world");
        Files.portalLocations.set("Locations.XXXBALL_2.Location.X", 0);
        Files.portalLocations.set("Locations.XXXBALL_2.Location.Y", 0);
        Files.portalLocations.set("Locations.XXXBALL_2.Location.Z", 0);
        Files.portalLocations.save();

        if(!Files.mysql.contains("MySQL")){
            Files.mysql.set("MySQL.Host", "localhost");
            Files.mysql.set("MySQL.Port", "3306");
            Files.mysql.set("MySQL.Database", "Example");
            Files.mysql.set("MySQL.Username", "Edgar");
            Files.mysql.set("MySQL.Password", "Dolphins");

            Files.mysql.save();
        }
    }

    private void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new EventPlayerInteract(), this);
        pm.registerEvents(new EventPlayerJoin(), this);
        pm.registerEvents(new EventProjectileHit(), this);
        pm.registerEvents(new EventPlayerMove(), this);
    }

    private void runParticles()
    {
        new BukkitRunnable()
        {
            double phi = 0.0D;

            public void run()
            {
                this.phi += 0.3926990816987241D;
                for (String s : Files.portalLocations.getConfigurationSection("Locations").getKeys(false))
                {
                    Location loc = new Location(Bukkit.getWorld(Files.portalLocations.getString("Locations." + s + ".Location.World")), Files.portalLocations.getDouble("Locations." + s + ".Location.X"), Files.portalLocations.getDouble("Locations." + s + ".Location.Y"), Files.portalLocations.getDouble("Locations." + s + ".Location.Z"));
                    for (double t = 0.0D; t <= 6.283185307179586D; t += 0.1963495408493621D) {
                        for (double i = 0.0D; i <= 1.0D; i += 1.0D)
                        {
                            double x = 0.2D * (6.283185307179586D - t) * 0.5D * Math.cos(t + this.phi + i * 3.141592653589793D);
                            double y = 0.3D * t;
                            double z = 0.2D * (6.283185307179586D - t) * 0.5D * Math.sin(t + this.phi + i * 3.141592653589793D);
                            loc.add(x, y, z);
                            Bukkit.getWorld(Files.portalLocations.getString("Locations." + s + ".Location.World")).spawnParticle(Particle.VILLAGER_HAPPY, loc, 0, 0.0D, 0.0D, 0.0D, 0.5);
                            loc.subtract(x, y, z);
                        }
                    }
                }
                if (this.phi > 31.415926535897931D) {
                    cancel();
                }
            }
        }.runTaskTimer(this, 0L, 30L);
    }

    public static Portals getInstance(){
        return instance;
    }
}
