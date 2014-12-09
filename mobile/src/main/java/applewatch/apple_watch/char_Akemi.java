package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/10/16.
 */
public class char_Akemi extends CharacterSprite {

    public char_Akemi(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char100_akemi1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_GIRL;
        m_iCharacterID = menu_Character.CHAR_AKEMI_ID;

        if(anim_flg){
            addImg( R.drawable.char100_akemi4 );    // closing eyes in between animation
            addImg( R.drawable.char100_akemi2 );    // closed eye
            addImg( R.drawable.char100_akemi3 );    // speaking
            addImg( R.drawable.char100_akemi5 );    // speaking in between animation
            addImg( R.drawable.darkakemi );          // unselected used in player input scene
        }

        // set profile Strings
        m_StrName ="アケミ";
        m_StrBirthday="10月12日";
        m_StrGender="女";
        m_StrComment="リンゴが大好き。朝ご飯はいつもりんごを食べるフルーツ系女子。";

        // set message Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だね！";
        m_StrYell = "今日も元気にいこう。";

        // set voices
        m_CharVoiceHello = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_hello_morinomoko);
        m_CharVoiceWhether = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_whether_morinomoko);
        m_CharVoiceYell = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_yell_morinomoko);

        //set wait
        m_iHelloWait = 0;
        m_iWhetherWait = 15;
        m_iYellWait =10;
    }
}