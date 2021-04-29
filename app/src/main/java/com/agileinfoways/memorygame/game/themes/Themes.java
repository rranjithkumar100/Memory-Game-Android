package com.agileinfoways.memorygame.game.themes;

import android.graphics.Bitmap;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.utils.Utils;

import java.util.ArrayList;

public class Themes {

	public static String URI_DRAWABLE = "drawable://";

	public static Theme createAnimalsTheme() {
		Theme theme = new Theme();
		theme.id = 1;
		theme.name = "Animals";
		theme.backgroundImageUrl = URI_DRAWABLE + "animal_levels_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("animal_%d", i));
		}
		return theme;
	}

	public static Theme createVegetablesTheme() {
		Theme theme = new Theme();
		theme.id = 2;
		theme.name = "Vegetables";
		theme.backgroundImageUrl = URI_DRAWABLE + "vegetable_levels_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("veg_%d", i));
		}
		return theme;
	}

	public static Theme createClothesTheme() {
		Theme theme = new Theme();
		theme.id = 3;
		theme.name = "Clothes";
		theme.backgroundImageUrl = URI_DRAWABLE + "clothes_levels_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("clothes_%d", i));
		}
		return theme;
	}

	public static Theme createVehiclesTheme() {
		Theme theme = new Theme();
		theme.id = 4;
		theme.name = "Vehicles";
		theme.backgroundImageUrl = URI_DRAWABLE + "vehicles_levels_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("vehicles_%d", i));
		}
		return theme;
	}

	public static Theme createEmojiTheme() {
		Theme theme = new Theme();
		theme.id = 5;
		theme.name = "Emoji";
		theme.backgroundImageUrl = URI_DRAWABLE + "emojis_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("emojis_%d", i));
		}
		return theme;
	}

	public static Theme createSportsTheme() {
		Theme theme = new Theme();
		theme.id = 6;
		theme.name = "Sports";
		theme.backgroundImageUrl = URI_DRAWABLE + "sports_level_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("sports_%d", i));
		}
		return theme;
	}
	public static Theme createPhotoTheme() {
		Theme theme = new Theme();
		theme.id = 7;
		theme.name = "Photos";
		theme.backgroundImageUrl = URI_DRAWABLE + "sports_level_bg";
		theme.tileImageUrls = new ArrayList<String>();
		// 40 drawables
		for (int i = 1; i <= 28; i++) {
			theme.tileImageUrls.add(URI_DRAWABLE + String.format("sports_%d", i));
		}
		return theme;
	}
	
	public static Bitmap getBackgroundImage(Theme theme) {
		String drawableResourceName = theme.backgroundImageUrl.substring(Themes.URI_DRAWABLE.length());
		int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
		Bitmap bitmap = Utils.scaleDown(drawableResourceId, Utils.screenWidth(), Utils.screenHeight());
		return bitmap;
	}

}
