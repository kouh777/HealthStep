package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Banjyo extends CharacterSprite {

    public char_Banjyo(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char204_banjyo1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_BANJYO_ID;

        if(anim_flg){
            addImg( R.drawable.char204_banjyo4 );   // closing eyes in between animation
            addImg( R.drawable.char204_banjyo2 );   // closed eye
            addImg( R.drawable.char204_banjyo3 );   // speaking
            addImg( R.drawable.char204_banjyo5 );   // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だな！";
        m_StrYell = "今日も元気にいこうか。";

        // set voices
        m_CharVoiceHello = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.banzyo_hello_ize);
        m_CharVoiceWhether = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.banzyo_whether_ize);
        m_CharVoiceYell = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.banzyo_yell_ize);

        //set wait
        m_iHelloWait = 0;
        m_iWhetherWait = 15;
        m_iYellWait =10;
    }
}
