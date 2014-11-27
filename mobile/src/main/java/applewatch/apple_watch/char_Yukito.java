package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class char_Yukito extends CharacterSprite{

    public char_Yukito(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char200_yukito1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_YUKITO_ID;

        if(anim_flg){
            addImg( R.drawable.char200_yukito4 );
            addImg( R.drawable.char200_yukito2 );
            addImg( R.drawable.char200_yukito3 );
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だな！";
        m_StrYell = "今日も元気にいこうか。";

        // set voices
        m_CharVoiceHello = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.yukito_hello_hakoi3);
        m_CharVoiceWhether = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.yukito_whether_hakoi3);
        m_CharVoiceYell = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.yukito_yell_hakoi3);

        //set wait
        m_iHelloWait = 0;
        m_iWhetherWait = 5;
        m_iYellWait = 10;
    }
}
