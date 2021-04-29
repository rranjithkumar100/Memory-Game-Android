package com.agileinfoways.memorygame.game.events.engine;

import com.agileinfoways.memorygame.game.events.AbstractEvent;
import com.agileinfoways.memorygame.game.events.EventObserver;
import com.agileinfoways.memorygame.game.model.GameState;

/**
 * When the 'back to menu' was pressed.
 */
public class GameWonEvent extends AbstractEvent {

	public static final String TYPE = GameWonEvent.class.getName();

	public GameState gameState;

	
	public GameWonEvent(GameState gameState) {
		this.gameState = gameState;
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
