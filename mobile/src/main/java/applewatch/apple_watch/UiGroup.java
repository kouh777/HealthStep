package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/08.
 */
public class UiGroup {

    private BitmapDrawable m_UiBackGround;
    private GameText m_UiPlayerName;
    private GameText m_UiPrefacture;
    private GameText m_UiWalkingCount;
    private GameText m_UiTicketNum;

    private GameSprite m_UiTicket;
//    private FrameSprite m_UiTicketNum;
//    private GameSprite m_UiTicketUint;

    private GameSprite m_Mail;
    private GameSprite m_Setting;

    private GameView m_GameView;

    // receive from PlayerData
    private String m_StrPlayerName;
    private String m_StrPrefecture;
    private int m_iWalkingCount;
    private int m_iGachaTicketNum;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bMove;

    // define sprites positions and scales
    private final int PN_X = 320;   // player name X
    private final int PN_Y = 45;    // player name Y
    private final int PRE_X = 20;   // Prefecture X
    private final int PRE_Y = 45;   // Prefecture Y
    private final int WC_X = 40;    // Walking Count X
    private final int WC_Y = 110;    // Walking Count Y
    private final int TI_X = 400;   // Ticket Image X
    private final int TI_Y = 75;    // Ticket Image Y
    private final int TN_X = 670;   // Num of ticket X
    private final int TN_Y = 110;    // Num of ticket Y

    private final int MAIL_X = 430;
    private final int SETTING_X = 535;
    private final int MAIL_SETTING_Y = 150;

    private Object m_Obj;

    public UiGroup(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bMove = false;

        m_Obj = new Object();
        synchronized (m_Obj) {
            // ui background resource
            m_UiBackGround = (BitmapDrawable) gv.getResources().getDrawable(R.drawable.name_bar);

            // GameSprite
            m_UiTicket = new GameSprite(gv, m_iPosX + TI_X, m_iPosY + TI_Y, R.drawable.ticket);

            m_Mail = new GameSprite(gv, MAIL_X, MAIL_SETTING_Y, R.drawable.mail);
            m_Setting = new GameSprite(gv, SETTING_X, MAIL_SETTING_Y, R.drawable.settei);

            // receive data from player data
            m_StrPlayerName = PlayerData.getInstance().getPlayerName();
            m_StrPrefecture = PlayerData.getInstance().getPrefecture();
            m_iWalkingCount = PlayerData.getInstance().getWalkingCount();
            m_iGachaTicketNum = PlayerData.getInstance().getGachaTicket();

            // texts
            m_UiWalkingCount = new GameText(gv, ("総歩数　" + String.valueOf(m_iWalkingCount)).toCharArray(), m_iPosX + WC_X, m_iPosY + WC_Y);
            m_UiPlayerName = new GameText(gv, m_StrPlayerName.toCharArray(), m_iPosX + PN_X, m_iPosY + PN_Y);
            m_UiPrefacture = new GameText(gv, m_StrPrefecture.toCharArray(), m_iPosX + PRE_X, m_iPosY + PRE_Y);
            m_UiTicketNum = new GameText(gv, (String.valueOf(m_iGachaTicketNum) + "　枚").toCharArray(), m_iPosX + TN_X, m_iPosY + TN_Y);

            //set text decoration
            m_UiWalkingCount.setUiPreset();
            m_UiPlayerName.setUiPreset();
            m_UiPrefacture.setUiPreset();
            m_UiTicketNum.setUiPreset();
            m_UiTicketNum.align_right(TN_X);
        }
    }

    public void update(){
        m_iWalkingCount = PlayerData.getInstance().getWalkingCount();
        m_iGachaTicketNum = PlayerData.getInstance().getGachaTicket();

        m_UiWalkingCount.setText( "総歩数　"+String.valueOf(m_iWalkingCount) );
        m_UiTicketNum.setText(String.valueOf(m_iGachaTicketNum)+"　枚");

        m_UiTicketNum.align_right(TN_X);

        if( m_Mail != null && m_Mail.getTouch() ){
            m_GameView.playSE( R.raw.se_yes );
            new scene_Mail( m_GameView , 18 );
            m_bMove = true;
        }
    }

    public void draw(Canvas c){
        if(m_UiBackGround != null){
            m_UiBackGround.setBounds(0,0,m_GameView.getGameWidth(),m_UiBackGround.getIntrinsicHeight());
            m_UiBackGround.draw(c);
        }
        // for test ui context
        if(m_UiPlayerName != null){
            m_UiPlayerName.draw_stroke(c,5.0f,Color.argb(255,194,81,114));
            m_UiPlayerName.draw(c);
        }
        if(m_UiPrefacture != null){
            m_UiPrefacture.draw_stroke(c,5.0f,Color.argb(255,194,81,114));
            m_UiPrefacture.draw(c);
        }
        if(m_UiWalkingCount != null){
            m_UiWalkingCount.draw_stroke(c,5.0f,Color.argb(255,194,81,114));
            m_UiWalkingCount.draw(c);
        }
        if( m_UiTicket != null ){
            m_UiTicket.draw(c);
        }
        if( m_UiTicketNum != null ){
            m_UiTicketNum.draw_stroke(c,5.0f,Color.argb(255,194,81,114));
            m_UiTicketNum.draw(c);
        }
        if( m_Mail != null ){
            m_Mail.draw(c);
        }
        if( m_Setting != null ){
            m_Setting.draw(c);
        }
    }
    public void touch(MotionEvent event){
        if( m_Mail != null ){
            m_Mail.touch(event);
        }
        if( m_Setting != null ){
            m_Setting.touch(event);
        }
    }
    // getter
    public boolean getMove(){ return m_bMove; }
}