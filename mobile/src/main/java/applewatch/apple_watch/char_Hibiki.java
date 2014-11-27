package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Hibiki extends  CharacterSprite {

    public char_Hibiki(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char205_hibiki1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_HIBIKI_ID;

        if(anim_flg){
            addImg( R.drawable.char205_hibiki4 );
            addImg( R.drawable.char205_hibiki2 );
            addImg( R.drawable.char205_hibiki3 );
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だな！";
        m_StrYell = "今日も元気にいこうか。";
    }
}
