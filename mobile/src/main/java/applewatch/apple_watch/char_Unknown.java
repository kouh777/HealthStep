package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class char_Unknown extends CharacterSprite{

    public char_Unknown(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.unknown_waku, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_UNKNOWN;
        m_iCharacterID = menu_Character.CHAR_UNKNOWN_ID;
    }
}
