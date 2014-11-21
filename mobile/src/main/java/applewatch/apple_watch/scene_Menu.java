package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/13.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;


public class scene_Menu extends Task{

    // image resources
    private boolean m_bMove;

    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private menu_Character m_Character;
    private btn_Walk m_btn_Walk;
    private MessageCharacter m_MessageCharacter;
    private UiGroup m_UiGroup;

    // for test
    private numImage m_numImage;

    private GameSprite m_H1;
    private GameSprite m_Mail;
    private GameSprite m_Setting;

    // define sprite position
    private final int H1_Y = 140;

    // define fade in speed
    private final int FADE_IN_SPED = 40;

    // for test
//    private boolean m_bSceneEnd;

    // constract
    public scene_Menu(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();

        /*
        // mail
        m_Mail = new GameSprite(gv, MAIL_X, MAIL_SETTING_Y, R.drawable.mail);

        // setting
        m_Setting = new GameSprite( gv, SETTING_X, MAIL_SETTING_Y, R.drawable.settei );
        */

        // message
        m_MessageCharacter = new MessageCharacter(gv, 50, 775);

        // for presentation
        m_btn_Walk = new btn_Walk(gv, (w>>2)*3, h-(w>>1));
        m_Character = new menu_Character(gv, 100, 350);
        m_numImage = new numImage(gv,100,100,1.0f);

//        m_bSceneEnd = false;

        // common
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_mypage);
        m_UiGroup = new UiGroup(gv, 0, 0);
        m_MenuGroup = new MenuGroup(gv);

        reset();
    }

    @Override
    public void reset(){
        m_bMove = false;
        setTouchable( true );

        m_H1.setAlpha(0);
//        m_H1.setX( -600 );

        Log.d("TEST", "Menu::Reset");
    }

    @Override
    public void update(){
        if( m_UiGroup != null ) {
            m_UiGroup.update();
        }
        if( m_MenuGroup != null ) {
            m_MenuGroup.update();
            m_bMove = m_MenuGroup.getMove();
        }
        if (m_Character != null) {
            m_Character.update();
        }
        if( m_H1 != null ){
            m_H1.fade_in( FADE_IN_SPED );
//            m_H1.slideInX(0);
//            m_H1.setX( m_H1.getX() + 5);
        }
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c) {
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        if (m_UiGroup != null) {
            m_UiGroup.draw(c);
        }
        if (m_Character != null) {
            m_Character.draw(c);
        }
        /*
        if (m_numImage != null) {
            m_numImage.draw(c);
        }

        if (m_btn_Walk != null) {
            m_btn_Walk.draw(c);
        }
        */
        if( m_MessageCharacter != null ){
            if(m_Character != null) {
                switch (m_Character.CharacterID) {
                    case menu_Character.CHAR_AKEMI_ID:
                        m_MessageCharacter.draw(c);
                }
            }
        }
        if( m_H1 != null ){
            m_H1.draw(c);
        }
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
    }

    public void endAnimation(){

    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
            if (m_btn_Walk != null) {
                m_btn_Walk.touch(event);
            }
        }
    }
}