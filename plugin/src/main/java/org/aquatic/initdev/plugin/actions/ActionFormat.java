package org.aquatic.initdev.plugin.actions;

import lombok.Getter;

/**
 * Copyright © 2022.
 *
 * @since 04-06-2022
 */
public enum ActionFormat {

	SOUND ("{#Sound} "),
	EFFECT ("{#Effect} "),
	TITLE ("{#Title} "),
	ACTION_BAR ("{#ActionBar} "),
	DISCONNECT ("{#Disconnect} "),
	COMMAND ("{#Command} "),
	MESSAGE ("{#Message} "),
	BROADCAST ("{#Broadcast} "),
	CONSOLE ("{#Console} ");

	@Getter private final String identifier;

	ActionFormat(String identifier) { this.identifier = identifier; }
}
