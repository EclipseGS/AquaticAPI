package org.aquatic.initdev.plugin.utils.color;

import org.aquatic.initdev.plugin.utils.color.api.IridiumAPI;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
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
	public static String color(@NotNull String string) { return IridiumAPI.process(string); }

	/**
	 * Translate the color codes from a list of strings.
	 *
	 * @param strings List to translate.
	 *
	 * @return list translated
	 */
	public static List<String> color(@NotNull Collection<String> strings) {
		return strings.stream()
			.map(IridiumAPI::process)
			.collect(Collectors.toList());
	}
}
