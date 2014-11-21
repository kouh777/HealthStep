package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/10/16.
 */
public class char_Akemi extends CharacterSprite {

    public char_Akemi(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char100_akemi1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_AKEMI_ID;

        if(anim_flg){
            addImg( R.drawable.char100_akemi4 );
            addImg( R.drawable.char100_akemi2 );
            addImg( R.drawable.char100_akemi3 );
        }
    }
}