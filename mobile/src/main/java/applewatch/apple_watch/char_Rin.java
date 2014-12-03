package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Rin extends CharacterSprite{

    public char_Rin(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char107_rin1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_RIN_ID;

        if(anim_flg){
            addImg( R.drawable.char107_rin4 );   // closing eyes in between animation
            addImg( R.drawable.char107_rin2 );   // closed eye
            addImg( R.drawable.char107_rin3 );   // speaking
            addImg( R.drawable.char107_rin5 );   // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";
    }
}
