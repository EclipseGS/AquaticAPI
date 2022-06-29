package org.aquatic.initdev.plugin.utils;

import org.aquatic.initdev.plugin.AquaticAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Utils {
	
	private final AquaticAPI main;
	
	public Utils(@NotNull AquaticAPI main) { this.main = main; }
	
	/**
	 * Send the title.
	 *
	 * @param player Player to send the title.
	 * @param fadeIn In Time.
	 * @param stay Stay Time.
	 * @param fadeOut Out Time.
	 * @param title Title message for send.
	 * @param subtitle Subtitle message to send.
	 */
	public void title(
		@NotNull Player player,
		int fadeIn,
		int stay,
		int fadeOut,
		@NotNull String title,
		@Nullable String subtitle) {
		// Call to method for send titles.
		main.getHandler()
			.getServer()
			.sendTitle(
				player,
				fadeIn,
				stay,
				fadeOut,
				Text.color(title),
				subtitle == null ? null : Text.color(subtitle)
			);
	}
	
	/**
	 * Send the actionbar.
	 *
	 * @param player Player to send the actionbar.
	 * @param message Message to send.
	 */
	public void actionBar(
		@NotNull JavaPlugin plugin,
		@NotNull Player player,
		@NotNull String message,
		long duration) {
		// The duration must be higher to 1.
		if (duration < 1) return;

		// Create an instance of object BukkitRunnable for run the task for the actionbar.
		new BukkitRunnable() {
			long repeater = duration;

			@Override
			public void run() {
				// Send the actionbar.
				main.getHandler()
					.getServer()
					.actionBar(
						player,
						Text.color(message)
					);
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
	
	/**
	 * Disconnect a player from server.
	 *
	 * @param player Player to disconnect.
	 * @param message Message for disconnect.
	 */
	public void disconnect(@NotNull Player player, @NotNull String message) {
		// If the message to send not is null or empty.
		if (message.isEmpty()) {
			// The message is empty or null.
			throw new NullPointerException("The message for the disconnection is empty.");
		} else {
			// The message is not empty or yes exists.
			main.getHandler()
				.getServer()
				.closeConnection(
					player,
					Text.color(message)
				);
		}
	}
}
