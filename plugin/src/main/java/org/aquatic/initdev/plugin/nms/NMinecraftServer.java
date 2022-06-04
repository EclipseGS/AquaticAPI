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
