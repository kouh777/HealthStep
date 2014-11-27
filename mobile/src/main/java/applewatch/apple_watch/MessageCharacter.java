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

    private String m_StrHello;
    private String m_StrWhether;
    private String m_StrYell;

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

    // voice wait times
    private int m_iHelloWait;
    private int m_iWhetherWait;
    private int m_iYellWait;

    // voice flags
    private boolean m_bHello;
    private boolean m_bWhether;
    private boolean m_bYell;

    // now character
    private CharacterSprite m_NowCharacter;

    public MessageCharacter( scene_Menu menu, GameView gv, int posX, int posY){
        m_SceneMenu = menu;
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

        // initialize strings
        m_StrHello = "";
        m_StrWhether = "";
        m_StrYell = "";
        m_NowCharacter =  m_SceneMenu.getMenuCharacter().getCharacter();
        if( m_SceneMenu != null ) {
            if( m_SceneMenu.getMenuCharacter() != null ) {
                m_StrHello = m_NowCharacter.getStrHello();
                m_StrWhether = m_NowCharacter.getStrWhether();
                m_StrYell = m_NowCharacter.getStrYell();

                m_CharVoiceHello =  m_NowCharacter.getCharVoiceHello();
                m_CharVoiceWhether = m_NowCharacter.getCharVoiceWhether();
                m_CharVoiceYell =  m_NowCharacter.getCharVoiceYell();

                m_iHelloWait =   m_NowCharacter.getWaitHello();
                m_iWhetherWait =   m_NowCharacter.getWaitWhether();
                m_iYellWait =   m_NowCharacter.getWaitYell();
            }
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
                m_NowCharacter.setLipAnim(true);
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
                m_NowCharacter.setLipAnim(false);
            }

            // type text
            if( m_HelloText.getType() ) {
                m_HelloText.type_anim(m_StrHello);
            }else if( m_bHello && m_WhetherText.getType() ){
                m_WhetherText.setX( m_HelloText.getX() + m_HelloText.getWidth() );
                m_WhetherText.type_anim(m_StrWhether);
                m_WhetherText.add_wait(m_iWhetherWait);
            }else if( m_bWhether ){
                m_YellText.type_anim(m_StrYell);
                m_YellText.add_wait(m_iYellWait);
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