package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Nekoko extends CharacterSprite {

    public char_Nekoko(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char103_nekoko1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_NEKOKO_ID;

        if(anim_flg){
            addImg( R.drawable.char103_nekoko4 );
            addImg( R.drawable.char103_nekoko2 );
            addImg( R.drawable.char103_nekoko3 );
        }
    }
}
