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
package org.aquatic.initdev.plugin.utils;

import org.aquatic.initdev.plugin.VisualAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Utils {
	
	private static final VisualAPI MAIN = JavaPlugin.getPlugin(VisualAPI.class);
	
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
	public static void title(@Nonnull Player player, int fadeIn, int stay, int fadeOut,
													 @Nonnull String title, @Nullable String subtitle) {
		// Check if the version is major to 1.16.5.
		if (MAIN.is1_17orAbove) {
			// If is major...
			player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
		} else {
			// If not is major.
			MAIN.getHandler().getServer().sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
		}
	}
	
	/**
	 * Send the actionbar.
	 *
	 * @param player Player to send the actionbar.
	 * @param message Message to send.
	 *
	 * @throws NoSuchMethodException Exception to throw if the server version is major to 1.16.5.
	 */
	public static void actionBar(@Nonnull Player player, @Nonnull String message) throws NoSuchMethodException {
		// Check if the version is major to 1.16.5.
		if (MAIN.is1_17orAbove) {
			// If is major...
			throw new NoSuchMethodException("The actionbar is not available in 1.17 or above per the " +
																			"moment");
		} else {
			// If not is major.
			MAIN.getHandler().getServer().actionBar(player, message);
		}
	}
	
	/**
	 * Disconnect a player from server.
	 *
	 * @param player Player to disconnect.
	 * @param message Message for disconnect.
	 *
	 * @throws NoSuchMethodException Exception to throws if the server version is major to 1.16.5.
	 */
	public static void disconnect(@Nonnull Player player, @Nonnull String message) throws NoSuchMethodException {
		// Check if the version is major to 1.16.5.
		if (MAIN.is1_17orAbove) {
			// If is major...
			throw new NoSuchMethodException("The disconnect method is not available in 1.17 per the " +
																			"moment");
		} else {
			// If not is major.
			// And if the message to send not is null or empty.
			if (message.isEmpty()) {
				// The message is empty or null.
				throw new NullPointerException("The message for the disconnection is empty.");
			} else MAIN.getHandler().getServer().closeConnection(player, message);
		}
	}
}
