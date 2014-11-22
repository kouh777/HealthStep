package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/11/06.
 */
public class MessageCharacter {
    private GameSprite m_MsgBg;
    private GameView m_GameView;
    private int m_iPosX;
    private int m_iPosY;

    // game texts
    private GameText m_WhetherText;
    private GameText m_YellText;

    // text draw flag
    private boolean m_bTextFlg;

    // text date
    String StrWhether = "おはよう！いい天気だね！";    // Hello and Whether
    String StrYell = "今日も元気にいこう。";        // Yell For

    // define char array size
    private final int DISP_BUFFER_SIZE = 32;

    // define Sprite Scale
    private final double BG_SX = 0.9;
    private final double BG_SY = 1.1;

    // define zoom speed
    private final double ZOOM_SPEED =0.2;

    // character voice
    private GameSound m_CharVoiceHello;
    private GameSound m_CharVoiceWhether;
    private GameSound m_CharVoiceYell;

    // voice flags
    private boolean m_bHello;
    private boolean m_bWhether;
    private boolean m_bYell;

    public MessageCharacter(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTextFlg =false;

        // sprite
        m_MsgBg = new GameSprite( gv, posX , posY,  R.drawable.hukidasi, BG_SX, 0, 255 );

        // text
        m_WhetherText = new GameText( gv, DISP_BUFFER_SIZE, posX + 20, posY + 108 );
        m_YellText = new GameText( gv, DISP_BUFFER_SIZE, posX + 20, posY + 148 );

        // set
        m_WhetherText.setType(true);
        m_YellText.setType(true);
        m_YellText.setWait(true);

        // if unlock character is nothing
        if( PlayerData.getInstance().getSelectCharacter() == menu_Character.CHAR_AKEMI_ID ) {
            m_CharVoiceHello = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_hello_morinomoko);
            m_CharVoiceWhether = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_whether_morinomoko);
            m_CharVoiceYell = new GameSound( SoundKind.SOUND_VOICE, m_GameView, R.raw.akemi_yell_morinomoko);
        }

        //
        m_bHello = false;
        m_bWhether = false;
        m_bYell = false;
    }

    public void update(){
        if( m_MsgBg != null ){
            m_MsgBg.zoomIn( AnimKind.ANIM_SY , ZOOM_SPEED, BG_SY);
            if( m_MsgBg.getScaleY() >= BG_SY ){
                m_bTextFlg = true;
            }
        }
        if( m_bTextFlg ){
            // play sound voice
            if( !m_bHello && m_CharVoiceHello != null ) {
                m_CharVoiceHello.play();
                m_bHello = true;
            }
            if( m_bHello && !m_CharVoiceHello.getIsPlaying() ){
                m_CharVoiceWhether.play();
                m_bWhether = true;
            }
            if( m_bWhether && !m_CharVoiceWhether.getIsPlaying() ){
                m_CharVoiceYell.play();
                m_bYell = true;
            }

            if( m_WhetherText.getType() ) {
                m_WhetherText.type_anim(StrWhether);
            }else if( m_bWhether ){
                m_YellText.type_anim(StrYell);
                m_YellText.add_wait(10);
            }
        }
        if( m_WhetherText != null ){
            m_WhetherText.update();
        }
        if( m_YellText != null ){
            m_YellText.update();
        }
    }

    public void draw(Canvas c){
        // draw sprite
        if( m_MsgBg != null ){
            m_MsgBg.draw(c);
        }
        if( m_WhetherText != null ){
            m_WhetherText.draw(c);
        }
        if( m_YellText != null ){
            m_YellText.draw(c);
        }
    }
}
