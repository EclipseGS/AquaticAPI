package org.aquatic.initdev.nms;

import net.minecraft.server.v1_8_R1.*;
import org.aquatic.initdev.plugin.nms.NMinecraftServer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright © 2022.
 *
 * @see NMinecraftServer
 * @since 02-06-2022
 */
public class v1_8_R1 implements NMinecraftServer {

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
		// Getting the player connection.
		PlayerConnection connectionHandler = ((CraftPlayer) player).getHandle().playerConnection;
		// Create a new instance for the title times.
		PacketPlayOutTitle playOutTitle = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn
				, stay, fadeOut);
		// Send the packet to player.
		connectionHandler.sendPacket(playOutTitle);
		// Serialize the title message.
		IChatBaseComponent baseTitle = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		// Create a new instance only for the title.
		PacketPlayOutTitle outTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, baseTitle);
		// Send the packet to player.
		connectionHandler.sendPacket(outTitle);
		// Check if the subtitle message exists.
		if (subtitle != null) {
			// Serialize the subtitle message.
			IChatBaseComponent baseSubtitle = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			// Create a new instance only for the subtitle.
			PacketPlayOutTitle outSubtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, baseSubtitle);
			// Send the packet to player.
			connectionHandler.sendPacket(outSubtitle);
		}
	}

	/**
	 * Send an actionbar.
	 *
	 * @param player  Player to get her connection.
	 * @param message Message to send.
	 */
	@Override
	public void actionBar(@Nonnull Player player, @Nonnull String message) {
		// Serialize the message.
		IChatBaseComponent chatBaseComponent = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		// Create a new instance for the component.
		PacketPlayOutChat playOutChat = new PacketPlayOutChat(chatBaseComponent, (byte) 2);
		// Send the packet to player.
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(playOutChat);
	}

	/**
	 * Close the player connection.
	 *
	 * @param player  Player to get her connection.
	 * @param message Message for send.
	 */
	@Override
	public void closeConnection(@Nonnull Player player, @Nonnull String message) {
		// Getting the player connection, and disconnects it with the message.
		((CraftPlayer) player).getHandle().playerConnection.disconnect(message);
	}
}
