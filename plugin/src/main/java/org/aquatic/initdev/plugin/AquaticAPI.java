package org.aquatic.initdev.plugin;

import lombok.Getter;
import org.aquatic.initdev.plugin.actions.ActionHandler;
import org.aquatic.initdev.plugin.nms.Handler;
import org.aquatic.initdev.plugin.utils.Utils;

/**
 * Main Class API Spigot - Copyright © 2022.
 *
 * @since 02-06-2022
 */
public final class AquaticAPI {

	@Getter
	private final String author = "InitDev";
	@Getter
	private final String currentVersion = "1.0.2";

	@Getter
	private Handler handler;
	@Getter
	private ActionHandler actionHandler;
	@Getter
	private Utils utils;

	public AquaticAPI() { enable(); }

	public void enable() {
		// API Startup logic.

		// Initialize the Version Handler.
		System.out.println("[AquaticAPI] Initialized Version Handler.");
		handler = new Handler(this);

		// Initialize the Action Handler.
		System.out.println("[AquaticAPI] Initialized Action Handler.");
		actionHandler = new ActionHandler(this);

		// Initialize the Utils.
		System.out.println("[AquaticAPI] Initialized API Utils.");
		utils = new Utils(this);

		System.out.println("[AquaticAPI] Started!");
		System.out.println("[AquaticAPI] Developed by: " + author + " | v" + currentVersion);
	}

	public void disable() {
		handler = null;
		actionHandler = null;
		utils = null;

		System.out.println("[AquaticAPI] Disabled!");
		System.out.println("[AquaticAPI] Developed by: " + author + " | v" + currentVersion);

	}
}
