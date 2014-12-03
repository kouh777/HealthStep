package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Shouta extends CharacterSprite {

    public char_Shouta(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, R.drawable.char203_shouta1, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BOY;
        m_iCharacterID = menu_Character.CHAR_SHOUTA_ID;

        if(anim_flg){
            addImg( R.drawable.char203_shouta4 );   // closing eyes in between animation
            addImg( R.drawable.char203_shouta2 );   // closed eye
            addImg( R.drawable.char203_shouta3);   // speaking
            addImg( R.drawable.char203_shouta5);   // speaking in between animation
        }

        // set Strings
        m_StrHello = "おはよう！";
        m_StrWhether = "いい天気だな！";
        m_StrYell = "今日も元気にいこうか。";
    }
}
