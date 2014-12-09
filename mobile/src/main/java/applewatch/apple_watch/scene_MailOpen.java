package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/06.
 */
public class scene_MailOpen extends Task {

    private MailMessage m_MailMsg;
//    private MailGroup m_MailGroup;

    // scene common member
    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;
    private GameSprite m_H1;
    // define sprite position
    private final int H1_Y = 140;
    // define fade in speed
    private final int FADE_IN_SPEED = 40;

    // constract
    public scene_MailOpen(GameView gv, int prio, MailGroup mg){
        super(prio);
        m_GameView = gv;
//        m_MailGroup = mg;

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();

        m_MailMsg = new MailMessage(gv , mg);

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
        if( m_MailMsg != null ){
            m_MailMsg.update();
        }
        if( m_H1 != null ){
            m_H1.fade_in( FADE_IN_SPEED );
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
        if( m_MailMsg != null ){
            m_MailMsg.draw(c);
        }
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
            if( m_MailMsg != null ){
                m_MailMsg.touch(event);
            }
        }
    }
}
