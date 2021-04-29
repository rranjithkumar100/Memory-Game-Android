package com.agileinfoways.memorygame.game.events.ui;

import com.agileinfoways.memorygame.game.events.AbstractEvent;
import com.agileinfoways.memorygame.game.events.EventObserver;
import com.agileinfoways.memorygame.game.themes.Theme;

public class ThemeSelectedEvent extends AbstractEvent {

	public static final String TYPE = ThemeSelectedEvent.class.getName();
	public final Theme theme;

	public ThemeSelectedEvent(Theme theme) {
		this.theme = theme;
	}

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
