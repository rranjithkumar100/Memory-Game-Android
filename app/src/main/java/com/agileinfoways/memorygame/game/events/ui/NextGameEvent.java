package com.agileinfoways.memorygame.game.events.ui;

import com.agileinfoways.memorygame.game.events.AbstractEvent;
import com.agileinfoways.memorygame.game.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class NextGameEvent extends AbstractEvent {

	public static final String TYPE = NextGameEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
