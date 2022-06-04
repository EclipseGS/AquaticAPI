/*
 * MIT License
 *
 * Copyright (c) 2022 InitDev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.aquatic.initdev.nms;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.aquatic.initdev.plugin.nms.NMinecraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright © 2022.
 *
 * @see NMinecraftServer
 * @since 02-06-2022
 */
public class v1_8_R3 implements NMinecraftServer {

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
		PacketPlayOutTitle playOutTitle =
				new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay,
															 fadeOut);
		// Send the packet to player.
		connectionHandler.sendPacket(playOutTitle);
		// Serialize the title message.
		IChatBaseComponent baseTitle =
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		// Create a new instance only for the title.
		PacketPlayOutTitle outTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE
				, baseTitle);
		// Send the packet to player.
		connectionHandler.sendPacket(outTitle);
		// Check if the subtitle message exists.
		if (subtitle != null) {
			// Serialize the subtitle message.
			IChatBaseComponent baseSubtitle =
					IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			// Create a new instance only for the subtitle.
			PacketPlayOutTitle outSubtitle =
					new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, baseSubtitle);
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
		IChatBaseComponent chatBaseComponent =
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
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
