package be.thijswouters.Portals.API;

import be.thijswouters.Portals.Utils.ArrayLists;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Thijs on 28/03/2017.
 */
public class PortalStaff {

    public static ItemStack getStaff(){
        ItemStack is = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName("§b§lPortal Staff");
        meta.setUnbreakable(true);
        is.setItemMeta(meta);
        is.setDurability((short) 23);
        return is;
    }

    public static void launchFirstPortal(Player p){
        Projectile pro = p.launchProjectile(Snowball.class);
        pro.setCustomName(p.getName() + "BALL_1");
        ArrayLists.firstPortalCreated.add(p);
    }

    public static void launchSecondPortal(Player p){
        Projectile pro = p.launchProjectile(Snowball.class);
        pro.setCustomName(p.getName() + "BALL_2");
        ArrayLists.firstPortalCreated.add(p);
    }
}
