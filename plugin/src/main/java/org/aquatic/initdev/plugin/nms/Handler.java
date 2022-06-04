package org.aquatic.initdev.plugin.nms;

import lombok.Getter;
import org.aquatic.initdev.plugin.VisualAPI;
import org.aquatic.initdev.plugin.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Handler {
	
	private final VisualAPI main;
	
	@Getter private NMinecraftServer server;
	
	public Handler() {
		this.main = JavaPlugin.getPlugin(VisualAPI.class);
		
		check();
	}
	
	// Check Version Method.
	private void check() {
		// Getting the Package name.
		String packageName =
				Bukkit.getServer().getClass().getPackage().getName();
		// Getting the Version string.
		String version =
				packageName.substring(packageName.lastIndexOf('.') + 1);
		// Try to find the NMS class.
		try {
			// This depends of the version on use.
			Class<?> clazz = Class.forName("org.aquatic.initdev.nms." + version);
			// If find the class...
			if (NMinecraftServer.class.isAssignableFrom(clazz)) {
				server = (NMinecraftServer) clazz.getConstructor().newInstance();
			}
			// If the version is 1.17 or 1.18.
			if (version.equals("v1_17_R1") || version.equals("v1_18_R1")) main.is1_17orAbove = true;
		} catch (ClassNotFoundException | InvocationTargetException
												 | InstantiationException | IllegalAccessException
												 | NoSuchMethodException e) {
			// If not find the class.
			Log.asError("Failed to check the version of your server. Apparently this version is not " +
									"supported by the API, or has happened an internal error!");
			// Disabling the API.
			main.getServer().getPluginManager().disablePlugin(main);
			return;
		}
		// Yes can find the class.
		Log.asInfo("Loading class (NMS): " + version);
	}
}
