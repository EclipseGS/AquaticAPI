package org.aquatic.initdev.plugin.actions;

import org.aquatic.initdev.plugin.AquaticAPI;
import org.aquatic.initdev.plugin.utils.color.Text;
import org.aquatic.initdev.plugin.utils.universal.XPotion;
import org.aquatic.initdev.plugin.utils.universal.XSound;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Copyright © 2022.
 *
 * @see ActionModel
 * @since 04-06-2022
 */
public final class ActionHandler implements ActionModel {

	private final AquaticAPI main;

	public ActionHandler(@NotNull AquaticAPI main) { this.main = main; }

	/**
	 * Replay a sound to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void sound(@NotNull String s, @NotNull Player player) {
		// Clearing the Action identifier.
		s = s.substring(9);

		// Splitting the Action string.
		String[] separators = s.split(":", 3);

		// Getting the sound.
		Sound sound = XSound.matchXSound(separators[0])
			.get()
			.parseSound();
		assert sound != null;

		// Sound int parameters.
		int volume;
		int pitch;
		try {
			// Try of convert the contains of string to ints for the parameters.
			volume = Integer.parseInt(separators[1]);
			pitch = Integer.parseInt(separators[2]);
		} catch (NumberFormatException e) {
			// If an error happens...
			System.out.println("[AquaticAPI] Failed to parse the 'Sound Action' int parameters.");

			// Print the error.
			e.printStackTrace();

			// Could not send the title.
			System.out.println("[AquaticAPI] Action 'sound' return exit '1'.");
			return;
		}

		// Finally, replay the sound.
		// And yes can replay the sound.
		player.playSound(player.getLocation(), sound, volume, pitch);
	}

	/**
	 * Give an effect to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void effect(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(10);

		// Splitting the Action string.
		String[] separators = s.split(":", 3);

		// Getting the effect.
		PotionEffectType effect = XPotion.matchXPotion(separators[0])
			.get()
			.getPotionEffectType();
		assert effect != null;

		// Effect int parameters.
		int duration;
		int amplifier;
		try {
			// Try of convert the contains of string to ints for the parameters.
			duration = Integer.parseInt(separators[1]);
			amplifier = Integer.parseInt(separators[2]);
		} catch (NumberFormatException e) {
			// If an error happens...
			System.out.println("[AquaticAPI] Failed to parse the 'Effect Action' int parameters.");

			// Print the error.
			e.printStackTrace();

			// Could not parse the parameters
			System.out.println("[AquaticAPI] Action 'effect' return exit '1'.");
			return;
		}

		// Add the potion effect, can?
		player.addPotionEffect(new PotionEffect(effect, duration, amplifier));
	}

	/**
	 * Send a title to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void title(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(9);

		// Splitting the Action string.
		String[] separators = s.split(":", 5);

		// Getting the title.
		String title = separators[0];
		// Getting the subtitle, first check if this is empty, else not, get and add the colors.
		String subtitle = separators[1].isEmpty() ? null : separators[1];

		// Time Parameters of Title.
		int fadeIn;
		int stay;
		int fadeOut;
		try {
			// Try of convert the contains of string to ints for the parameters.
			fadeIn = Integer.parseInt(separators[2]);
			stay = Integer.parseInt(separators[3]);
			fadeOut = Integer.parseInt(separators[4]);
		} catch (NumberFormatException e) {
			// If an error happens...
			System.out.println("[AquaticAPI] Failed to parse the 'Title Action' int parameters.");

			// Print the error.
			e.printStackTrace();

			// Could not send the title.
			System.out.println("[AquaticAPI] Action 'disconnect' return exit '1'.");
			return;
		}

		// Finally, send the title
		// And yes can send the title.
		main.getHandler()
			.getServer()
			.sendTitle(
				player,
				fadeIn,
				stay,
				fadeOut,
				title,
				subtitle
			);
	}

	/**
	 * Send an actionbar to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void actionbar(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(13);

		// Getting the message and
		// send the actionbar.
		main.getHandler()
			.getServer()
			.actionBar(
				player,
				s
			);
	}

	/**
	 * Disconnect to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void disconnect(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(14);

		// Getting the message and
		// Disconnect to player.
		main.getHandler()
			.getServer()
			.closeConnection(
				player,
				s
			);
	}

	/**
	 * Dispatch a command from console or the player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void command(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(11);

		// Splitting the Action string.
		String[] separators = s.split(":", 2);

		// Getting the command.
		String command = Text.color(separators[0]);

		// Getting the console boolean.
		boolean fromConsole = Boolean.parseBoolean(separators[1]);
		// Check if the boolean is true.
		if (fromConsole) {
			// Dispatch the command from console.
			Bukkit.dispatchCommand(
				Bukkit.getConsoleSender(),
				command
			);
			return;
		}

		// The boolean is false.
		// Dispatch the command from the player.
		player.performCommand(command);
	}

	/**
	 * Send a message to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void message(@Nonnull String s, @Nonnull Player player) {
		// Clearing the Action identifier.
		s = s.substring(11);

		// Getting the message and
		// sending the message.
		player.sendMessage(Text.color(s));
	}

	/**
	 * Send a message to all players connected.
	 *
	 * @param s       Action Container.
	 * @param players Players to send the message.
	 */
	@Override
	public void broadcast(@Nonnull String s, @Nonnull Collection<? extends Player> players) {
		// Clearing the Action identifier.
		s = s.substring(13);

		// Getting the message.
		String message = Text.color(s);

		// Getting all players and sending the message.
		for (Player player : players) player.sendMessage(message);
	}

	/**
	 * Send a message to console.
	 *
	 * @param s Action Container.
	 */
	@Override
	public void console(@Nonnull String s) {
		// Clearing the Action identifier.
		s = s.substring(11);

		// Getting the message and
		// sending the message.
		Bukkit.getConsoleSender().sendMessage(Text.color(s));
	}

	/**
	 * Check can find any action and be executed.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void checkAndExecute(@Nonnull String s, @Nonnull Player player) {
		// The string starts with the '{#Sound}'  identifier?
		if (s.startsWith(ActionFormat.SOUND.getIdentifier())) {
			sound(s, player);
			return;
		}

		// The string starts with the '{#Effect}' identifier?
		if (s.startsWith(ActionFormat.EFFECT.getIdentifier())) {
			effect(s, player);
			return;
		}

		// The string starts with the '{#Title}'  identifier?
		if (s.startsWith(ActionFormat.TITLE.getIdentifier())) {
			title(s, player);
			return;
		}

		// The string starts with the '{#ActionBar}' identifier?
		if (s.startsWith(ActionFormat.ACTION_BAR.getIdentifier())) {
			actionbar(s, player);
			return;
		}

		// The string starts with the '{#Disconnect}'  identifier?
		if (s.startsWith(ActionFormat.DISCONNECT.getIdentifier())) {
			disconnect(s, player);
			return;
		}

		// The string starts with the '{#Command}' identifier?
		if (s.startsWith(ActionFormat.COMMAND.getIdentifier())) {
			command(s, player);
			return;
		}

		// The string starts with the '{#Message}'  identifier?
		if (s.startsWith(ActionFormat.MESSAGE.getIdentifier())) {
			message(s, player);
			return;
		}

		// The string starts with the '{#Broadcast}' identifier?
		if (s.startsWith(ActionFormat.BROADCAST.getIdentifier())) {
			broadcast(s, Bukkit.getOnlinePlayers());
			return;
		}

		// The string starts with the '{#Console}'  identifier?
		if (s.startsWith(ActionFormat.CONSOLE.getIdentifier())) {
			console(s);
			return;
		}

		// The action identifier is misspelled or doesn't exist.
		System.out.println("[AquaticAPI] The action '" + s + "' doesn't exist.");
		System.out.println("[AquaticAPI] checkAndExecute() method returns exit '1'.");
	}
}
