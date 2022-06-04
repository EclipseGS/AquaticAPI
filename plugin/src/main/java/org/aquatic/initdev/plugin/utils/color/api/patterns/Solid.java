package org.aquatic.initdev.plugin.utils.color.api.patterns;

import org.aquatic.initdev.plugin.utils.color.api.IridiumAPI;
import org.aquatic.initdev.plugin.utils.color.api.Pattern;

import javax.annotation.Nonnull;
import java.util.regex.Matcher;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class Solid implements Pattern {
	
	java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<S:([0-9A-Fa-f]{6})>|#\\{" +
																																		"([0-9A-Fa-f]{6})}");
	@Override
	public String process(@Nonnull String string) {
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String color = matcher.group(1);
			if (color == null) color = matcher.group(2);
			
			string = string.replace(matcher.group(),IridiumAPI.getColor(color) + "");
		}
		return string;
	}
}
