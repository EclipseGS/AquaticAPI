package org.aquatic.initdev.plugin.utils.color;

import org.aquatic.initdev.plugin.utils.color.api.IridiumAPI;

import javax.annotation.Nonnull;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class Text {
	
	/**
	 * Translate the color codes from string.
	 *
	 * @param string String to translate.
	 * @return string translated
	 */
	public static String color(@Nonnull String string) { return IridiumAPI.process(string); }
}
