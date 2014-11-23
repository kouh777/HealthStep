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

    // receiving character sprite
    private scene_Menu m_SceneMenu;

    // game texts
    private GameText m_HelloText;
    private GameText m_WhetherText;
    private GameText m_YellText;

    // text draw flag
    private boolean m_bTextFlg;

    // text date
    String StrHello = "おはよう！";                    // Hello
    String StrWhether = "いい天気だね！";            // Whether
    String StrYell = "今日も元気にいこう。";         // Yell For

    String StrManWhether = "いい天気だな";          // man whether
    String StrManYell = "今日も元気に行こうか";     // man yell

    // define text position from BG
    private final int TEXT_X = 20;
    private final int TEXT_Y = 108;
    private final int TEXT_YELL_Y = 148;

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

    // define wait time
    private final int WHETHER_WAIT = 15;
    private final int YELL_WAIT = 10;

    public MessageCharacter( scene_Menu menu, GameView gv, int posX, int posY){
        m_SceneMenu = menu;
//        m_resChr = menu.getMenuCharacter().getCharacter();
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTextFlg =false;

        // sprite
        m_MsgBg = new GameSprite( gv, posX , posY,  R.drawable.hukidasi, BG_SX, 0, 255 );

        // text
        m_HelloText = new GameText( gv, DISP_BUFFER_SIZE, posX + TEXT_X, posY + TEXT_Y );
        m_WhetherText = new GameText( gv, DISP_BUFFER_SIZE, posX + TEXT_X, posY + TEXT_Y );
        m_YellText = new GameText( gv, DISP_BUFFER_SIZE, posX + TEXT_X, posY + TEXT_YELL_Y );

        // set type
        m_HelloText.setType(true);
        m_WhetherText.setType(true);
        m_YellText.setType(true);

        // set wait
        m_WhetherText.setWait(true);
        m_YellText.setWait(true);

        // set shadow
//        m_HelloText.setShadow( 3.0f, 0.0f, 0.0f, Color.argb( 255 , 197 ,86 ,120 ) );
//        m_HelloText.setColor( 255, 255, 255, 255);

        // if unlock character akemi is selecting
        if( PlayerData.getInstance().getSelectCharacter() == menu_Character.CHAR_AKEMI_ID  ||
            PlayerData.getInstance().getSelectCharacter() == menu_Character.CHAR_BANJYO_ID  ||
            PlayerData.getInstance().getSelectCharacter() == menu_Character.CHAR_YUKITO_ID ) {
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
            // play voices
            if( !m_bHello && m_CharVoiceHello != null ) {
                m_CharVoiceHello.play();
                m_bHello = true;
                m_SceneMenu.getMenuCharacter().getCharacter().setLipAnim(true);
            }
            if( m_bHello && !m_CharVoiceHello.getIsPlaying() ){
                m_CharVoiceWhether.play();
                m_bWhether = true;
            }
            if( m_bWhether && !m_CharVoiceWhether.getIsPlaying() ){
                m_CharVoiceYell.play();
                m_bYell = true;
            }
            if( m_bYell && !m_CharVoiceYell.getIsPlaying() ){
                m_SceneMenu.getMenuCharacter().getCharacter().setLipAnim(false);
            }

            // type text
            if( m_HelloText.getType() ) {
                m_HelloText.type_anim(StrHello);
            }else if( m_bHello && m_WhetherText.getType() ){
                m_WhetherText.setX( m_HelloText.getX() + m_HelloText.getWidth() );
                m_WhetherText.add_wait(WHETHER_WAIT);
                m_WhetherText.type_anim(StrManWhether);
            }else if( m_bWhether ){
                m_YellText.type_anim(StrManYell);
                m_YellText.add_wait(YELL_WAIT);
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
        if( m_HelloText != null ){
            m_HelloText.draw(c);
        }
        if( m_WhetherText != null ){
            m_WhetherText.draw(c);
        }
        if( m_YellText != null ){
            m_YellText.draw(c);
        }
    }
}
