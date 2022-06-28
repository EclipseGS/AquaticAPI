package org.aquatic.initdev.plugin.utils;

import org.aquatic.initdev.plugin.AquaticAPI;
import org.aquatic.initdev.plugin.utils.color.Text;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
		@NotNull String subtitle) {
		// Call to method for send titles.
		main.getHandler()
			.getServer()
			.sendTitle(
				player,
				fadeIn,
				stay,
				fadeOut,
				Text.color(title),
				Text.color(subtitle)
			);
	}
	
	/**
	 * Send the actionbar.
	 *
	 * @param player Player to send the actionbar.
	 * @param message Message to send.
	 */
	public void actionBar(@NotNull Player player, @NotNull String message) {
		// Send the actionbar
		main.getHandler()
			.getServer()
			.actionBar(
				player,
				Text.color(message)
			);
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
