package org.aquatic.initdev.plugin.actions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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
	void effect(@NotNull JavaPlugin plugin, @Nonnull String s, @Nonnull Player player);

	/**
	 * Send a title to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void title(@NotNull String s, @NotNull Player player);

	/**
	 * Send an actionbar to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void actionbar(
		@NotNull JavaPlugin plugin,
		@NotNull String s,
		@NotNull Player player);

	/**
	 * Disconnect to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void disconnect(@NotNull String s, @NotNull Player player);

	/**
	 * Dispatch a command from console or the player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void command(@NotNull JavaPlugin plugin, @NotNull String s, @NotNull Player player);

	/**
	 * Send a message to player.
	 *
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void message(@NotNull String s, @NotNull Player player);

	/**
	 * Send a message to all players connected.
	 *
	 * @param s Action Container.
	 * @param players Players to send the message.
	 */
	void broadcast(@NotNull String s, @NotNull Collection<? extends Player> players);

	/**
	 * Send a message to console.
	 *
	 * @param s Action Container.
	 */
	void console(@NotNull String s);

	/**
	 * Check can find any action and be executed.
	 *
	 * @param plugin Main class of the plugin what uses the API.
	 * @param s Action Container.
	 * @param player Player Parameter.
	 */
	void checkAndExecute(
		@NotNull JavaPlugin plugin,
		@NotNull String s,
		@NotNull Player player);
}
