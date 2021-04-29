package com.agileinfoways.memorygame.game.events;

import com.agileinfoways.memorygame.game.events.engine.FlipDownCardsEvent;
import com.agileinfoways.memorygame.game.events.engine.GameWonEvent;
import com.agileinfoways.memorygame.game.events.engine.HidePairCardsEvent;
import com.agileinfoways.memorygame.game.events.ui.BackGameEvent;
import com.agileinfoways.memorygame.game.events.ui.DifficultySelectedEvent;
import com.agileinfoways.memorygame.game.events.ui.FlipCardEvent;
import com.agileinfoways.memorygame.game.events.ui.NextGameEvent;
import com.agileinfoways.memorygame.game.events.ui.ResetBackgroundEvent;
import com.agileinfoways.memorygame.game.events.ui.StartEvent;
import com.agileinfoways.memorygame.game.events.ui.ThemeSelectedEvent;

public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();		
	}

}
