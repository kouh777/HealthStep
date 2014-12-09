package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/04.
 */
public class MailGroup {

    private GameSprite m_MailBack;
    private GameText m_Sender;
    private GameText m_RecivingTime;
    private GameText m_Subject;

    private GameView m_GameView;

    // Strings original for each mail
    private String m_StrSender;
    private String m_StrReceivingTime;
    private String m_StrSubject;
    private String m_StrContents;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bTouch;

    // define sprites positions and scales
    private final int SENDER_X = 120;
    private final int SENDER_Y = 30;
    private final int RECV_TIME_X = 600;
    private final int RECV_TIME_Y = 28;
    private final int SUBJ_X = 150;
    private final int SUBJ_Y = 65;


    public MailGroup(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTouch = false;

        // original text for each mail
        m_StrSender = "運営";
        m_StrSubject = "チケットの配布";
        m_StrReceivingTime = "1分前";
        m_StrContents = "チケット1枚プレゼント";

        m_MailBack = new GameSprite( gv , posX , posY , R.drawable.me_ru_1, 1.0f ,1.0f , 255 );
        m_Sender =new GameText( gv ,m_StrSender.toCharArray() , posX + SENDER_X, posY + SENDER_Y );
        m_RecivingTime =new GameText( gv , m_StrReceivingTime.toCharArray() , posX + RECV_TIME_X , posY + RECV_TIME_Y );
        m_Subject = new GameText( gv , m_StrSubject.toCharArray() , posX + SUBJ_X, posY + SUBJ_Y );

        m_Sender.setMailSubAndSendPreset();
        m_RecivingTime.setMailRecvTimePreset();
        m_Subject.setMailSubAndSendPreset();
        m_RecivingTime.align_right(RECV_TIME_X);
    }

    public void update(){
        m_RecivingTime.align_right(RECV_TIME_X);
    }

    public void draw(Canvas c){
        if( m_MailBack != null ){
            m_MailBack.draw(c);
        }
        if( m_Sender != null ){
            m_Sender.draw(c);
        }
        if( m_RecivingTime != null ){
            m_RecivingTime.draw(c);
        }
        if( m_Subject != null ){
            m_Subject.draw(c);
        }
    }
    public void touch(MotionEvent event){
        if( m_MailBack != null ){
            m_MailBack.touch(event);
            if( m_MailBack.getTouch() ){
                m_bTouch = true;
            }
        }
    }
    // settter
    public void setStrSender( String sender ){
        m_StrSender = sender;
    }
    public void getStrRecvTime( String recv_time ){
        m_StrReceivingTime = recv_time;
    }
    public void getStrSubject( String subject ){
        m_StrSubject = subject;
    }
    public void getStrContents( String contents ){
        m_StrContents = contents;
    }

    // getter
    public boolean getTouch(){
        return m_bTouch;
    }
    public String getStrSender(){
        return m_StrSender;
    }
    public String getStrRecvTime(){
        return m_StrReceivingTime;
    }
    public String getStrSubject(){
        return m_StrSubject;
    }
    public String getStrContents(){
        return m_StrContents;
    }
}
