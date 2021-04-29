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


public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
