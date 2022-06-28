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
