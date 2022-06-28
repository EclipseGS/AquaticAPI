package org.aquatic.initdev.nms;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.network.PlayerConnection;
import org.aquatic.initdev.plugin.nms.NMinecraftServer;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Copyright © 2022.
 *
 * @see NMinecraftServer
 * @since 27-06-2022
 */
public class v1_18_R1 implements NMinecraftServer {

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
	@Override
	public void sendTitle(
		@NotNull Player player,
		int fadeIn,
		int stay,
		int fadeOut,
		@NotNull String title,
		@Nullable String subtitle) {
		// Send the title using the method of the API.
		player.sendTitle(
			title,
			subtitle,
			fadeIn,
			stay,
			fadeOut
		);
	}

	/**
	 * Send an actionbar.
	 *
	 * @param player Player to get her connection.
	 * @param message Message to send.
	 */
	@Override
	public void actionBar(@NotNull Player player, @NotNull String message) {
		// Send the actionbar using the method provided by the Spigot API.
		player.spigot().sendMessage(
			ChatMessageType.ACTION_BAR,
			TextComponent.fromLegacyText(message)
		);
	}

	/**
	 * Close the player connection.
	 *
	 * @param player Player to get her connection.
	 * @param message Message for send.
	 */
	@Override
	public void closeConnection(@NotNull Player player, @NotNull String message) {
		// Getting the player connection.
		PlayerConnection connectionHandler = ((CraftPlayer) player).getHandle().b;

		// Disconnect to player using the message provided.
		connectionHandler.disconnect(message);
	}
}
