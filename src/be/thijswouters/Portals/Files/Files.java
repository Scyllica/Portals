package be.thijswouters.Portals.Files;

import be.thijswouters.Portals.Portals;

public class Files {
	
	public static PluginFile config = new PluginFile(Portals.getInstance(), "Config.yml");
	public static PluginFile mysql = new PluginFile(Portals.getInstance(), "MySQL.yml");
	public static PluginFile portalLocations = new PluginFile(Portals.getInstance(), "PortalLocations.yml");
	public static PluginFile cooldowns = new PluginFile(Portals.getInstance(), "Cooldowns.yml");
}
