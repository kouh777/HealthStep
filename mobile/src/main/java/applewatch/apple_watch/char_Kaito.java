package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Kaito extends CharacterSprite {

    public char_Kaito(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char201_kaito1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_KAITO_ID;

        if(anim_flg){
            addImg( R.drawable.char201_kaito4 );    // closing eyes in between animation
            addImg( R.drawable.char201_kaito2 );    // closed eye
            addImg( R.drawable.char201_kaito3 );    // speaking
            addImg( R.drawable.char201_kaito5 );    // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だな！";
        m_StrYell = "今日も元気にいこうか。";
    }
}
