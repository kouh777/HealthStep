package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Miki extends CharacterSprite {

    public char_Miki(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char104_miki1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_MIKI_ID;

        if(anim_flg){
            addImg( R.drawable.char104_miki4 );
            addImg( R.drawable.char104_miki2 );
            addImg( R.drawable.char104_miki3 );
        }
    }
}
