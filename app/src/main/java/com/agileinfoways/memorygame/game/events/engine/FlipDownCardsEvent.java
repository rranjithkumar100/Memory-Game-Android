package com.agileinfoways.memorygame.game.events.engine;

import com.agileinfoways.memorygame.game.events.AbstractEvent;
import com.agileinfoways.memorygame.game.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class FlipDownCardsEvent extends AbstractEvent {

	public static final String TYPE = FlipDownCardsEvent.class.getName();

	public FlipDownCardsEvent() {
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
