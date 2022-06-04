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
package org.aquatic.initdev.plugin.actions;

import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Copyright © 2022.
 *
 * @since 04-06-2022
 */
public interface ActionModel {

	/**
	 * Replay a sound to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void sound(@Nonnull String s, @Nonnull Player player);

	/**
	 * Give an effect to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void effect(@Nonnull String s, @Nonnull Player player);

	/**
	 * Send a title to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void title(@Nonnull String s, @Nonnull Player player);

	/**
	 * Send an actionbar to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void actionbar(@Nonnull String s, @Nonnull Player player);

	/**
	 * Disconnect to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void disconnect(@Nonnull String s, @Nonnull Player player);

	/**
	 * Dispatch a command from console or the player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void command(@Nonnull String s, @Nonnull Player player);

	/**
	 * Send a message to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void message(@Nonnull String s, @Nonnull Player player);

	/**
	 * Send a message to all players connected.
	 *
	 * @param s Action Container.
	 * @param players Players to send the message.
	 */
	void broadcast(@Nonnull String s, @Nonnull Collection<? extends Player> players);

	/**
	 * Send a message to console.
	 *
	 * @param s Action Container.
	 */
	void console(@Nonnull String s);

	/**
	 * Check can find any action and be executed.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void checkAndExecute(@Nonnull String s, @Nonnull Player player);
}
