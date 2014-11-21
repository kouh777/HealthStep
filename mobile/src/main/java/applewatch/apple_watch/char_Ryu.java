package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Ryu extends CharacterSprite {

    public char_Ryu(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char303_ryu1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BEAST;
        m_iCharacterID = menu_Character.CHAR_RYU_ID;

        if(anim_flg){
            addImg( R.drawable.char303_ryu2 );
        }
    }
}
