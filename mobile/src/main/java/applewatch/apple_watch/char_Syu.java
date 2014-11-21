package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Syu extends CharacterSprite {

    public char_Syu(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char202_syu1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_SYU_ID;

        if(anim_flg){
            addImg( R.drawable.char202_syu4 );
            addImg( R.drawable.char202_syu2 );
            addImg( R.drawable.char202_syu3 );
        }
    }
}
