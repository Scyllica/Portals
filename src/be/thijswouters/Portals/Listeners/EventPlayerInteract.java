package be.thijswouters.Portals.Listeners;

import be.thijswouters.Portals.API.PortalStaff;
import be.thijswouters.Portals.Utils.ArrayLists;
import be.thijswouters.Portals.Utils.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Thijs on 28/03/2017.
 */
public class EventPlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getItemInHand().equals(PortalStaff.getStaff())) {
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if(ArrayLists.secondPortalCreated.contains(p)) {
                    Chat.normal(p, "Portals", "Your portal gun is on cooldown for #TIME.");
                } else if(ArrayLists.firstPortalCreated.contains(p)){
                   Chat.normal(p, "Portals", "You've already created the first portal.");
                } else {
                    PortalStaff.launchFirstPortal(p);
                    ArrayLists.firstPortalCreated.add(p);
                }

            } else if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if(ArrayLists.secondPortalCreated.contains(p)) {
                    Chat.normal(p, "Portals", "Your portal gun is on cooldown for #TIME.");
                } else if(!ArrayLists.firstPortalCreated.contains(p)){
                    Chat.normal(p, "Portals", "You haven't created the first portal yet.");
                } else {
                    ArrayLists.secondPortalCreated.add(p);
                    PortalStaff.launchSecondPortal(p);
                }
            }
        }
    }
}
