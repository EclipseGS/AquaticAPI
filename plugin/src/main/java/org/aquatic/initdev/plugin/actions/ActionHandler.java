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

import org.aquatic.initdev.plugin.VisualAPI;
import org.aquatic.initdev.plugin.utils.Log;
import org.aquatic.initdev.plugin.utils.Utils;
import org.aquatic.initdev.plugin.utils.color.Text;
import org.aquatic.initdev.plugin.utils.universal.XPotion;
import org.aquatic.initdev.plugin.utils.universal.XSound;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Copyright © 2022.
 *
 * @see ActionModel
 * @since 04-06-2022
 */
public final class ActionHandler implements ActionModel {

	private final VisualAPI main;

	public ActionHandler() { this.main = JavaPlugin.getPlugin(VisualAPI.class); }

	/**
	 * Replay a sound to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void sound(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.SOUND.getIdentifier(), "");
		// Splitting the Action string.
		String[] separators = s.split(":", 3);
		// Getting the sound.
		Sound sound = XSound.matchXSound(separators[0]).get().parseSound();
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
			Log.asError("Failed to parse the 'Sound Action' int parameters.");
			// Print the error.
			e.printStackTrace();
			// Could not send the title.
			Log.asInfo("Action 'sound' return '1'.");
			return;
		}
		// Finally, replay the sound.
		player.playSound(player.getLocation(), sound, volume, pitch);
		// Yes can replay the sound.
		Log.asInfo("Action 'sound' return '0'.");
	}

	/**
	 * Give an effect to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void effect(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.EFFECT.getIdentifier(), "");
		// Splitting the Action string.
		String[] separators = s.split(":", 3);
		// Getting the effect.
		PotionEffectType effect = XPotion.matchXPotion(separators[0]).get().getPotionEffectType();
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
			Log.asError("Failed to parse the 'Effect Action' int parameters.");
			// Print the error.
			e.printStackTrace();
			// Could not send the title.
			Log.asInfo("Action 'effect' return '1'.");
			return;
		}
		// Check if is the primary thread.
		if (Bukkit.isPrimaryThread()) {
			// Add the potion effect.
			player.addPotionEffect(new PotionEffect(effect, duration, amplifier));
			return;
		}
		// Is not the primary thread.
		main.getServer().getScheduler().runTaskLater(main, () -> {
			player.addPotionEffect(new PotionEffect(effect, duration, amplifier));
		}, 1L);
		// Yes can give the effect.
		Log.asInfo("Action 'effect' return '0'.");
	}

	/**
	 * Send a title to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void title(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.TITLE.getIdentifier(), "");
		// Splitting the Action string.
		String[] separators = s.split(":", 5);
		// Getting the title.
		String title = Text.color(separators[0]);
		// Getting the subtitle, first check if this is empty, else not, get and add the colors.
		String subtitle = separators[1].isEmpty() ? null : Text.color(separators[1]);
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
			Log.asError("Failed to parse the 'Title Action' int parameters.");
			// Print the error.
			e.printStackTrace();
			// Could not send the title.
			Log.asInfo("Action 'disconnect' return '1'.");
			return;
		}
		// Finally, send the title.
		Utils.title(player, fadeIn, stay, fadeOut, title, subtitle);
		// Yes can send the title.
		Log.asInfo("Action 'disconnect' return '0'.");
	}

	/**
	 * Send an actionbar to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void actionbar(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.ACTION_BAR.getIdentifier(), "");
		// Getting the message.
		String message = Text.color(s);
		// Try of send the actionbar.
		try {
			// Send the actionbar.
			Utils.actionBar(player, message);
		} catch (NoSuchMethodException e) {
			// An error happens...
			Log.asError("Failed to send the actionbar.");
			// Print the error.
			e.printStackTrace();
			// Could not send the actionbar.
			Log.asInfo("Action 'actionbar' return '1'.");
			return;
		}
		// Yes can send the actionbar.
		Log.asInfo("Action 'actionbar' return '0'.");
	}

	/**
	 * Disconnect to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void disconnect(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.DISCONNECT.getIdentifier(), "");
		// Getting the message.
		String message = Text.color(s);
		// Try of disconnect to player.
		try {
			Utils.disconnect(player, message);
		} catch (NoSuchMethodException e) {
			// An error happens...
			Log.asError("Failed to disconnect to player.");
			// Print the error.
			e.printStackTrace();
			// Could not disconnect to player.
			Log.asInfo("Action 'disconnect' return '1'.");
			return;
		}
		// Yes can disconnect to player.
		Log.asInfo("Action 'disconnect' return '0'.");
	}

	/**
	 * Dispatch a command from console or the player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void command(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.COMMAND.getIdentifier(), "");
		// Splitting the Action string.
		String[] separators = s.split(":", 2);
		// Getting the command.
		String command = Text.color(separators[0]);
		// Getting the console boolean.
		boolean fromConsole = Boolean.parseBoolean(separators[1]);
		// Check if the boolean is true.
		if (fromConsole) {
			// Check if is the primary server thread.
			if (main.getServer().isPrimaryThread()) {
				main.getServer().dispatchCommand(main.getServer().getConsoleSender(), command);
			} else {
				// It's not the primary thread.
				main.getServer().getScheduler().runTaskLater(main, () -> {
					main.getServer().dispatchCommand(main.getServer().getConsoleSender(), command);
				}, 1L);
			}
			return;
		}
		// The boolean is false.
		// Check if is the primary server thread.
		if (main.getServer().isPrimaryThread()) player.performCommand(command);
		else {
			// It's not the primary thread.
			main.getServer().getScheduler().runTaskLater(main, () -> player.performCommand(command), 1L);
		}
		// Yes can perform the command.
		Log.asInfo("Action 'command' return '0'.");
	}

	/**
	 * Send a message to player.
	 *
	 * @param s      Action Container.
	 * @param player Player Parameter.
	 */
	@Override
	public void message(@Nonnull String s, @Nonnull Player player) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.COMMAND.getIdentifier(), "");
		// Getting the message.
		String message = Text.color(s);
		// Sending the message.
		player.sendMessage(Text.color(message));
		// Yes can send the message.
		Log.asInfo("Action 'message' return '0'.");
	}

	/**
	 * Send a message to all players connected.
	 *
	 * @param s       Action Container.
	 * @param players Players to send the message.
	 */
	@Override
	public void broadcast(@Nonnull String s, @Nonnull Collection<? extends Player> players) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.BROADCAST.getIdentifier(), "");
		// Getting the message.
		String message = Text.color(s);
		// Sending the message.
		for (Player player : players) player.sendMessage(message);
		// Yes can send the message.
		Log.asInfo("Action 'broadcast' return '0'.");
	}

	/**
	 * Send a message to console.
	 *
	 * @param s Action Container.
	 */
	@Override
	public void console(@Nonnull String s) {
		// Replacing the Action identifier.
		s = s.replace(ActionFormat.CONSOLE.getIdentifier(), "");
		// Getting the message.
		String message = Text.color(s);
		// Sending the message.
		main.getServer().getConsoleSender().sendMessage(message);
		// Yes can send the message.
		Log.asInfo("Action 'console' return '0'.");
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
			broadcast(s, main.getServer().getOnlinePlayers());
			return;
		}
		// The string starts with the '{#Console}'  identifier?
		if (s.startsWith(ActionFormat.CONSOLE.getIdentifier())) {
			console(s);
			return;
		}
		// The action identifier is misspelled or doesn't exist.
		Log.asError("The action '" + s + "' doesn't exist.");
		Log.asError("return '0'.");
	}
}
