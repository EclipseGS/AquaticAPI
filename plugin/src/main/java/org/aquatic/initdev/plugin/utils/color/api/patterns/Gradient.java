package org.aquatic.initdev.plugin.utils.color.api.patterns;

import org.aquatic.initdev.plugin.utils.color.api.IridiumAPI;
import org.aquatic.initdev.plugin.utils.color.api.Pattern;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.regex.Matcher;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class Gradient implements Pattern {
	
	java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<G:([0-9A-Fa-f]{6})>(.*?)" +
																																		"</G:([0-9A-Fa-f]{6})>");
	@Override
	public String process(@NotNull String string) {
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String start = matcher.group(1);
			String end = matcher.group(3);
			String content = matcher.group(2);
			string = string.replace(matcher.group(),
															IridiumAPI.color(content, new Color(Integer.parseInt(start, 16)),
																							 new Color(Integer.parseInt(end,16))));
		}
		return string;
	}
}
