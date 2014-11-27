package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Donta extends CharacterSprite {

    public char_Donta(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char301_donta1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BEAST;
        m_iCharacterID = menu_Character.CHAR_DONTA_ID;

        if(anim_flg){
            addImg( R.drawable.char301_donta2 );
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";
    }
}
