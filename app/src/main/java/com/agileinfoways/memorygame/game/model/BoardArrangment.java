package com.agileinfoways.memorygame.game.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.themes.Themes;
import com.agileinfoways.memorygame.game.utils.Utils;

import java.util.Map;

/**
 * Before game starts, engine build new board
 * 
 * @author sromku
 */
public class BoardArrangment {
    private static final String TAG = "BoardArrangment";
	// like {0-2, 4-3, 1-5}
	public Map<Integer, Integer> pairs;
	// like {0-mosters_20, 1-mosters_12, 2-mosters_20, ...}
	public Map<Integer, String> tileUrls;

	/**
	 * 
	 * @param id
	 *            The id is the number between 0 and number of possible tiles of
	 *            this theme
	 * @return The Bitmap of the tile
	 */
	public Bitmap getTileBitmap(int id, int size) {

		String string = tileUrls.get(id);
        if(id==0){
            Log.d(TAG, "getTileBitmap: "+string);
        }
		if (string.contains(Themes.URI_DRAWABLE)) {
			String drawableResourceName = string.substring(Themes.URI_DRAWABLE.length());
			int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
			Bitmap bitmap = Utils.scaleDown(drawableResourceId, size, size);
			return bitmap;
			//return Utils.crop(bitmap, size, size);
		}
		return null;
	}

	public boolean isPair(int id1, int id2) {
		Integer integer = pairs.get(id1);
		if (integer == null) {
			// TODO Report this bug!!!
			return false;
		}
		return integer.equals(id2);
	}

}
