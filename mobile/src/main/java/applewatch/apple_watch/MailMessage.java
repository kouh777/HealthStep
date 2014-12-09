package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/06.
 */
public class MailMessage {

    // sprites
    private GameSprite m_MailMsgBack;
    private GameSprite m_MailReturn;
    private GameSprite m_MailDelete;

    // GameText
    private GameText m_Mail;
    private GameText m_MailSender;
    private GameText m_MailContents;

    // string data receive from mail group
    private String m_StrSender;
    private String m_StrContents;

    private GameView m_GameView;

    // define Sprite Position from center
    private final int YES_NO_Y = 755;
    private final int YES_X = 220;
    private final int NO_X = 420;

    // action id
    private int m_ActionID;

    //define ButtonID
    static final int ACT_NOTHING = 0;
    static final int ACT_YES = 1;
    static final int ACT_NO = 2;

    // display flag
    private boolean m_bDisplay;

    public MailMessage( GameView gv , MailGroup mg ){
        m_GameView = gv;

        // new sprites
        m_MailMsgBack = new GameSprite(gv, R.drawable.mail_base);
        m_MailReturn = new GameSprite(gv, R.drawable.mail_modoru);
        m_MailDelete = new GameSprite(gv, R.drawable.mail_sakujo);

        // set string from MailGroup
        m_StrSender = mg.getStrSender();
        m_StrContents = mg.getStrSender();

        m_MailSender = new GameText( gv, m_StrSender.toCharArray(), 0, 0 );
        m_MailContents = new GameText( gv, m_StrContents.toCharArray(), 0, 0 );

        m_ActionID = ACT_NOTHING;
        m_bDisplay = false;

        reset();
    }

    public void reset(){
        // set sprites
        m_MailMsgBack.setScaleX(0);
        m_MailMsgBack.setScaleY(0);

        m_MailReturn.setScaleX(0);
        m_MailReturn.setScaleY(0);

        m_MailDelete.setScaleX(0);
        m_MailDelete.setScaleY(0);

    }

    public void update(){
        if( m_MailMsgBack != null ){
            m_MailMsgBack.zoom(0.4);
        }
        if( m_MailReturn != null ){
            m_MailReturn.zoom(0.4);
        }
        if( m_MailDelete != null ){
            m_MailDelete.zoom(0.4);
        }
    }

    public void draw(Canvas c){
        if( m_MailMsgBack != null){
            m_MailMsgBack.draw( c, 320, 600 );
        }
        if( m_MailReturn != null){
            m_MailReturn.draw( c, YES_X, YES_NO_Y );
        }
        if( m_MailDelete != null){
            m_MailDelete.draw( c, NO_X, YES_NO_Y );
        }
        if( m_MailSender != null ){
            m_MailSender.draw(c);
        }
        if( m_MailContents != null ){
            m_MailContents.draw(c);
        }
    }

    public void touch( MotionEvent event ){
        if( m_MailReturn != null){
            m_MailReturn.touch(event);
            if( m_MailReturn.getTouch() ) {
                m_ActionID = ACT_YES;
                m_GameView.playSE(R.raw.se_yes);
                Log.d("MailMsg", "Return-touch");
            }
        }
        if( m_MailDelete != null ){
            m_MailDelete.touch(event);
            if( m_MailDelete.getTouch() ) {
                m_ActionID = ACT_NO;
                m_GameView.playSE(R.raw.se_no);
                Log.d("MailMsg", "Delete-touch");
            }
        }
    }

    // getter
    public int getAction(){ return m_ActionID; }
    public boolean getDisplay(){ return m_bDisplay; }

    // setter
    public void setDisplay( boolean disp ){ m_bDisplay = disp; }
}
