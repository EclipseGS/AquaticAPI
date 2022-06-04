package org.aquatic.initdev.plugin.utils;

import lombok.Getter;

/**
 * Copyright © 2022.
 *
 * @since 04-06-2022
 */
public enum Permissions {
	
	TITLE_COMMAND ("visualapi.command.title"),
	ACTIONBAR_COMMAND ("visualapi.command.actionbar"),
	DISCONNECT_COMMAND ("visualapi.command.disconnect"),
	
	BYPASS ("visualapi.bypass");
	
	@Getter private final String permission;
	
	Permissions(String permission) { this.permission = permission; }
}
