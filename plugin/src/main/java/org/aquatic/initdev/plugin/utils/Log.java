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
package org.aquatic.initdev.plugin.utils;

import org.aquatic.initdev.plugin.VisualAPI;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

/**
 * Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class Log {
	
	private static final VisualAPI MAIN = JavaPlugin.getPlugin(VisualAPI.class);
	private static final Logger LOGGER = MAIN.getLogger();
	
	/**
	 * Send a Log info.
	 *
	 * @param s Message to send.
	 */
	public static void asInfo(@Nonnull String s) {
		// Send the info.
		LOGGER.info(s);
	}
	
	/**
	 * Send a Log warn.
	 *
	 * @param s Message to send.
	 */
	public static void asWarn(@Nonnull String s) {
		// Send the warning.
		LOGGER.warning(s);
	}
	
	/**
	 * Send a Log error.
	 *
	 * @param s Message to send.
	 */
	public static void asError(@Nonnull String s) {
		// Send the error.
		LOGGER.severe(s);
	}
}
