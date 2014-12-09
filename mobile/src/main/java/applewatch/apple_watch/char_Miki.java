package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Miki extends CharacterSprite {

    public char_Miki(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char104_miki1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_MIKI_ID;

        if(anim_flg){
            addImg( R.drawable.char104_miki4 );    // closing eyes in between animation
            addImg( R.drawable.char104_miki2 );    // closed eye
            addImg( R.drawable.char104_miki3 );    // speaking
            addImg( R.drawable.char104_miki5 );    // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";

        // set voices
        m_CharVoiceHello = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.miki_hello_kujyou);
        m_CharVoiceWhether = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.miki_whether_kujyou);
        m_CharVoiceYell = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.miki_yell_kujyou);

        //set wait
        m_iHelloWait = 0;
        m_iWhetherWait = 15;
        m_iYellWait =10;
    }
}
