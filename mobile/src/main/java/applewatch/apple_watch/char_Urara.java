package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Urara extends CharacterSprite {

    public char_Urara(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char101_urara1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_URARA_ID;

        if(anim_flg){
            addImg( R.drawable.char101_urara4 );   // closing eyes in between animation
            addImg( R.drawable.char101_urara2 );   // closed eye
            addImg( R.drawable.char101_urara3 );   // speaking
            addImg( R.drawable.char101_urara5 );   // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";
    }
}
