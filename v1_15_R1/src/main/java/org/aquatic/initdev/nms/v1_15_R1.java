package org.aquatic.initdev.nms;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import org.aquatic.initdev.plugin.nms.NMinecraftServer;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright © 2022.
 *
 * @see NMinecraftServer
 * @since 08-06-2022
 */
public final class v1_15_R1 implements NMinecraftServer {

	/**
	 * Send a title.
	 *
	 * @param player   Player to get her connection.
	 * @param fadeIn   In Time.
	 * @param stay     Stay Time.
	 * @param fadeOut  Out Time.
	 * @param title    Title message to send.
	 * @param subtitle Subtitle message to send.
	 */
	@Override
	public void sendTitle(@Nonnull Player player, int fadeIn, int stay, int fadeOut,
			@Nonnull String title, @Nullable String subtitle) {
		// Send the title using the method of the API.
		player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
	}

	/**
	 * Send an actionbar.
	 *
	 * @param player  Player to get her connection.
	 * @param message Message to send.
	 */
	@Override
	public void actionBar(@Nonnull Player player, @Nonnull String message) {
		// Send the actionbar by ChatMessageType.
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}

	/**
	 * Close the player connection.
	 *
	 * @param player  Player to get her connection.
	 * @param message Message for send.
	 */
	@Override
	public void closeConnection(@Nonnull Player player, @Nonnull String message) {
		// Getting the player connection.
		PlayerConnection connectionHandler = ((CraftPlayer) player).getHandle().playerConnection;

		// Disconnect to player using the message provided.
		connectionHandler.disconnect(message);
	}
}
