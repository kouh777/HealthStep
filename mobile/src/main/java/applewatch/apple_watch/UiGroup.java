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

//    private GameSprite m_UiPlayerName;
//    private GameSprite m_UiPrefacture;
    private GameSprite m_UiWalkingCount;
    private GameSprite m_UiTicket;
//    private GameSprite m_UiTicketNum;
    private FrameSprite m_UiTicketNum;
    private GameSprite m_UiTicketUint;

    private GameSprite m_Mail;
    private GameSprite m_Setting;

    private GameView m_GameView;

    // receive from PlayerData
    private String m_StrPlayerName;
    private String m_StrPrefecture;

    private int m_iPosX;
    private int m_iPosY;

    // define sprites positions and scales
    private final int PN_X = 320;   // player name X
    private final int PN_Y = 45;    // player name Y
    private final int PRE_X = 20;   // Prefecture X
    private final int PRE_Y = 45;   // Prefecture Y
    private final int WC_X = 40;    // Walking Count X
    private final int WC_Y = 80;    // Walking Count Y
    private final int TI_X = 400;   // Ticket Image X
    private final int TI_Y = 75;    // Ticket Image Y
    private final int TN_X = 570;   // Num of ticket X
    private final int TN_Y = 85;    // Num of ticket Y
    private final int TU_X = 600;   // Uint of ticket X
    private final int TU_Y = 85;    // Uint of ticket Y

    private final int MAIL_X = 430;
    private final int SETTING_X = 535;
    private final int MAIL_SETTING_Y = 150;

    private final double UI_SX = 0.82;
    private final double UI_SY = 0.19;

    public UiGroup(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;

        // ui background resource
        m_UiBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.name_bar);

        // sprites
//        m_UiPlayerName = new GameSprite( gv, m_iPosX + PN_X, m_iPosY + PN_Y, R.drawable.moji_playername );
//        m_UiPrefacture = new GameSprite( gv, m_iPosX + PRE_X, m_iPosY + PRE_Y, R.drawable.moji_todouhuken );
        m_UiWalkingCount = new GameSprite( gv, m_iPosX + WC_X, m_iPosY + WC_Y, R.drawable.moji_totalwalk );
        m_UiTicket = new GameSprite( gv, m_iPosX + TI_X, m_iPosY + TI_Y, R.drawable.ticket );
        m_UiTicketNum = new FrameSprite( gv,  m_iPosX + TN_X, m_iPosY + TN_Y,R.drawable.ui_numbers , 64);
 //       m_UiTicketNum = new GameSprite( gv, m_iPosX + TN_X, m_iPosY + TN_Y, R.drawable.ticket_suji1 );
        m_UiTicketUint = new GameSprite( gv, m_iPosX + TU_X, m_iPosY + TU_Y, R.drawable.kadai_mai );

        m_Mail = new GameSprite(gv, MAIL_X, MAIL_SETTING_Y, R.drawable.mail);
        m_Setting = new GameSprite( gv, SETTING_X, MAIL_SETTING_Y, R.drawable.settei );

        m_StrPlayerName = PlayerData.getInstance().getPlayerName();
        m_StrPrefecture = PlayerData.getInstance().getPrefecture();
        // texts
        m_UiPlayerName = new GameText( gv , m_StrPlayerName.toCharArray() , m_iPosX + PN_X, m_iPosY + PN_Y);
        m_UiPrefacture = new GameText( gv , m_StrPrefecture.toCharArray()  ,m_iPosX + PRE_X, m_iPosY + PRE_Y);

        //set text decoration
        m_UiPlayerName.setFamily("HGRPP1.TTC");
        m_UiPrefacture.setFamily("HGRPP1.TTC");

        m_UiPlayerName.setColor(255,255,255,255);
        m_UiPrefacture.setColor(255,255,255,255);

//        m_UiPlayerName.setShadow(10.f,0,0,Color.argb(255,194,81,114));
//        m_UiPrefacture.setShadow(2.f,0,0,Color.argb(255,194,81,114));
    }

    public void update(){
        m_UiTicketNum.setNumber( PlayerData.getInstance().getGachaTicket() );

        /*
        m_StrPlayerName = PlayerData.getInstance().getPlayerName();
        m_StrPrefecture = PlayerData.getInstance().getPrefecture();
        if( m_UiPlayerName != null ) {
            m_UiPlayerName.setText(m_StrPlayerName);
        }
        if( m_UiPrefacture != null ) {
            m_UiPrefacture.setText(m_StrPrefecture);
        }
        */
    }

    public void draw(Canvas c){

        if(m_UiBackGround != null){
            m_UiBackGround.setBounds(0,0,m_GameView.getGameWidth(),m_UiBackGround.getIntrinsicHeight());
            m_UiBackGround.draw(c);
        }

        // for test ui context
        if(m_UiPlayerName != null){
            /*
            //draw stroke
            GameText stroke = m_UiPlayerName;
            stroke.setStrokeStyle();
            stroke.setStrokeWidth(3.0f);
            stroke.setColor(255,194,81,114);
            stroke.setTextSize(34);
            stroke.draw(c);
            */
            m_UiPlayerName.draw(c);
        }
        if(m_UiPrefacture != null){
            m_UiPrefacture.draw(c);
        }
        if(m_UiWalkingCount != null){
            m_UiWalkingCount.draw(c);
        }
        if( m_UiTicket != null ){
            m_UiTicket.draw(c);
        }

        if( m_UiTicketNum != null ){
            m_UiTicketNum.draw(c);
        }
        if( m_UiTicketUint != null ){
            m_UiTicketUint.draw(c);
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
}
