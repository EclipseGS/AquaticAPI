package org.aquatic.initdev.plugin.utils;

import org.aquatic.initdev.plugin.VisualAPI;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Log {
	
	private static final VisualAPI MAIN = JavaPlugin.getPlugin(VisualAPI.class);
	private static final Logger LOGGER = MAIN.getLogger();
	
	/**
	 * Send a Log info.
	 *
	 * @param s Message to send.
	 */
	public static void asInfo(@Nonnull String s) {
		// Send the info.
		LOGGER.info(s);
	}
	
	/**
	 * Send a Log warn.
	 *
	 * @param s Message to send.
	 */
	public static void asWarn(@Nonnull String s) {
		// Send the warning.
		LOGGER.warning(s);
	}
	
	/**
	 * Send a Log error.
	 *
	 * @param s Message to send.
	 */
	public static void asError(@Nonnull String s) {
		// Send the error.
		LOGGER.severe(s);
	}
}
