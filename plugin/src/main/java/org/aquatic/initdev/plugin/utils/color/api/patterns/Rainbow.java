package org.aquatic.initdev.plugin.utils.color.api.patterns;

import org.aquatic.initdev.plugin.utils.color.api.IridiumAPI;
import org.aquatic.initdev.plugin.utils.color.api.Pattern;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

/**
 * Copyright © 2022.
 *
 * @since 03-06-2022
 */
public final class Rainbow implements Pattern {
	
	java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("<R([0-9]{1,3})>(.*?)</R>");
	
	@Override
	public String process(@NotNull String string) {
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			String saturation = matcher.group(1);
			String content = matcher.group(2);
			string = string.replace(matcher.group(), IridiumAPI.rainbow(content,
																																 Float.parseFloat(saturation)));
		}
		return string;
	}
}
