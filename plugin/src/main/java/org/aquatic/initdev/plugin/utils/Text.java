package org.aquatic.initdev.plugin.utils;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class Text {

	/**
	 * Translate the color codes from a string.
	 *
	 * @param string String to translate.
	 *
	 * @return string translated
	 */
	public static String color(@NotNull String string) {
		if (getVersion() >= 16) {
			Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
			Matcher match = pattern.matcher(string);

			while(match.find()) {
				String color = string.substring(match.start(),match.end());
				string = string.replace(color, ChatColor.of(color) + "");

				match = pattern.matcher(string);
			}
		}

		string = ChatColor.translateAlternateColorCodes('&', string);
		return string;
	}

	/**
	 * Translate the color codes from a list of strings.
	 *
	 * @param strings List to translate.
	 *
	 * @return list translated
	 */
	public static List<String> color(@NotNull Collection<String> strings) {
		return strings.stream()
			.map(Text::color)
			.collect(Collectors.toList());
	}

	private static int getVersion() {
		String version = Bukkit.getVersion();
		Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");

		// getVersion()
		int index = version.lastIndexOf("MC:");
		if (index != -1 ) version = version.substring(index + 4, version.length() - 1);
		else if (version.endsWith("SNAPSHOT")) {
			// getBukkitVersion()
			index = version.indexOf('-');
			version = version.substring(0, index);
		}

		// 1.13.2, 1.14.4, etc...
		int lastDot = version.lastIndexOf('.');
		if (version.indexOf('.') != lastDot) version = version.substring(0, lastDot);

		return Integer.parseInt(version.substring(2));
	}
}
