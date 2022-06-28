package org.aquatic.initdev.plugin.nms;

import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public interface NMinecraftServer {
	
	/**
	 * Send a title.
	 *
	 * @param player Player to get her connection.
	 * @param fadeIn In Time.
	 * @param stay Stay Time.
	 * @param fadeOut Out Time.
	 * @param title Title message to send.
	 * @param subtitle Subtitle message to send.
	 */
	void sendTitle(@Nonnull Player player, int fadeIn, int stay, int fadeOut, @Nonnull String title
			,  @Nullable String subtitle);
	
	/**
	 * Send an actionbar.
	 *
	 * @param player Player to get her connection.
	 * @param message Message to send.
	 */
	void actionBar(@Nonnull Player player, @Nonnull String message);
	
	/**
	 * Close the player connection.
	 *
	 * @param player Player to get her connection.
	 * @param message Message for send.
	 */
	void closeConnection(@Nonnull Player player, @Nonnull String message);
}
