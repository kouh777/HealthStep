package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/04.
 */
public class scene_Mail extends Task {

    private boolean m_bTouch;
    private boolean m_bMove;

    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    private MailGroup m_Mail_01;
    private MailGroup m_Mail_02;
    private MailGroup m_Mail_03;
    private MailGroup m_Mail_04;
    private MailGroup m_Mail_05;

    private GameSprite m_H1;

    // define sprite position
    private final int H1_Y = 140;

    // define fade in speed
    private final int FADE_IN_SPEED = 40;

    // constract
    public scene_Mail(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_bTouch = false;

        // common
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_mypage);
        m_UiGroup = new UiGroup(gv, 0, 0);

        m_Mail_01 = new MailGroup( gv,50,250);
        m_Mail_02 = new MailGroup( gv,50,350);
        m_Mail_03 = new MailGroup( gv,50,450);
        m_Mail_04 = new MailGroup( gv,50,550);
        m_Mail_05 = new MailGroup( gv,50,650);

        m_MenuGroup = new MenuGroup(gv);

        reset();
    }

    @Override
    public void reset(){
        m_bMove = false;
        setTouchable( true );

        m_H1.setAlpha(0);

        Log.d("TEST", "scene_Mail::Reset");
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
        if( m_H1 != null ){
            m_H1.fade_in( FADE_IN_SPEED );
        }
        if( m_Mail_01 != null ){
            m_Mail_01.update();
        }
        if( m_Mail_02 != null ){
            m_Mail_02.update();
        }
        if( m_Mail_03 != null ){
            m_Mail_03.update();
        }
        if( m_Mail_04 != null ){
            m_Mail_04.update();
        }
        if( m_Mail_05 != null ){
            m_Mail_05.update();
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
        if( m_H1 != null ){
            m_H1.draw(c);
        }
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
        if( m_Mail_01 != null ){
            m_Mail_01.draw(c);
            if( m_Mail_01.getTouch() ){
                m_bMove = true;
                new scene_MailOpen( m_GameView , 20 , m_Mail_01 );
            }
        }
        if( m_Mail_02 != null ){
            m_Mail_02.draw(c);
        }
        if( m_Mail_03 != null ){
            m_Mail_03.draw(c);
        }
        if( m_Mail_04 != null ){
            m_Mail_04.draw(c);
        }
        if( m_Mail_05 != null ){
            m_Mail_05.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
            if( m_Mail_01 != null ){
                m_Mail_01.touch(event);
            }
            if( m_Mail_02 != null ){
                m_Mail_02.touch(event);
            }
            if( m_Mail_03 != null ){
                m_Mail_03.touch(event);
            }
            if( m_Mail_04 != null ){
                m_Mail_04.touch(event);
            }
            if( m_Mail_05 != null ){
                m_Mail_05.touch(event);
            }
        }
    }
}
