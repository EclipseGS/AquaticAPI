package org.aquatic.initdev.plugin.nms;

import lombok.Getter;
import org.aquatic.initdev.plugin.AquaticAPI;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Handler {

	private final AquaticAPI main;

	@Getter
	private NMinecraftServer server;
	
	public Handler(@NotNull AquaticAPI main) {
		this.main = main;

		check();
	}
	
	// Check Version Method.
	private void check() {
		// Getting the Package name.
		String packageName = Bukkit.getServer()
			.getClass()
			.getPackage()
			.getName();
		// Getting the Version string.
		String version = packageName.substring(packageName.lastIndexOf('.') + 1);

		// Try to find the NMS class.
		try {
			// This depends of the version on use.
			Class<?> clazz = Class.forName("org.aquatic.initdev.nms." + version);
			// If find the class...
			if (NMinecraftServer.class.isAssignableFrom(clazz)) {
				server = (NMinecraftServer) clazz.getConstructor().newInstance();
			}
		} catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
		         IllegalAccessException | NoSuchMethodException e) {
			// If not find the class.
			System.out.println("[AquaticAPI] Failed to check the version of your server. Apparently " +
			                   "this  version is not supported by the API, or has happened an internal " +
			                   "error!");

			// Print the error.
			e.printStackTrace();

			// Disabling the API.
			main.disable();
			return;
		}

		// Yes can find the class.
		System.out.println("[AquaticAPI] Loading class for NMS: " + version);
	}
}
