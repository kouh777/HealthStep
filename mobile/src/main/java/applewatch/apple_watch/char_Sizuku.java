package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Sizuku extends CharacterSprite {

    public char_Sizuku(GameView gv, int posX, int posY, float scale, boolean anim_flg) {
        super( gv, posX, posY, R.drawable.char102_shizuku1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_SIZUKU_ID;

        if(anim_flg){
            addImg( R.drawable.char102_shizuku4 );
            addImg( R.drawable.char102_shizuku2 );
            addImg( R.drawable.char102_shizuku3 );
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";
    }
}