package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/11/08.
 */
public class UiGroup {

    private BitmapDrawable m_UiBackGround;

    private GameSprite m_UiPlayerName;
    private GameSprite m_UiPrefacture;
    private GameSprite m_UiWalkingCount;
    private GameSprite m_UiTicket;
    private GameSprite m_UiTicketNum;
    private GameSprite m_UiTicketUint;

    private GameView m_GameView;

    private int m_iPosX;
    private int m_iPosY;

    // define sprites positions and scales
    private final int PN_X = 320;   // player name X
    private final int PN_Y = 20;    // player name Y
    private final int PRE_X = 20;   // Prefecture X
    private final int PRE_Y = 20;   // Prefecture Y
    private final int WC_X = 40;    // Walking Count X
    private final int WC_Y = 80;    // Walking Count Y
    private final int TI_X = 400;   // Ticket Image X
    private final int TI_Y = 75;    // Ticket Image Y
    private final int TN_X = 570;   // Num of ticket X
    private final int TN_Y = 85;    // Num of ticket Y
    private final int TU_X = 600;   // Uint of ticket X
    private final int TU_Y = 85;    // Uint of ticket Y

    private final double UI_SX = 0.82;
    private final double UI_SY = 0.19;

    public UiGroup(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;

        // ui background resource
        m_UiBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.name_bar);

        // sprites
        m_UiPlayerName = new GameSprite( gv, m_iPosX + PN_X, m_iPosY + PN_Y, R.drawable.moji_playername );
        m_UiPrefacture = new GameSprite( gv, m_iPosX + PRE_X, m_iPosY + PRE_Y, R.drawable.moji_todouhuken );
        m_UiWalkingCount = new GameSprite( gv, m_iPosX + WC_X, m_iPosY + WC_Y, R.drawable.moji_totalwalk );
        m_UiTicket = new GameSprite( gv, m_iPosX + TI_X, m_iPosY + TI_Y, R.drawable.ticket );
        m_UiTicketNum = new GameSprite( gv, m_iPosX + TN_X, m_iPosY + TN_Y, R.drawable.ticket_suji1 );
        m_UiTicketUint = new GameSprite( gv, m_iPosX + TU_X, m_iPosY + TU_Y, R.drawable.kadai_mai );

    }

    public void update(){

    }

    public void draw(Canvas c){

        if(m_UiBackGround != null){
            m_UiBackGround.setBounds(0,0,m_GameView.getGameWidth(),m_UiBackGround.getIntrinsicHeight());
            m_UiBackGround.draw(c);
        }

        // for test ui context
        if(m_UiPlayerName != null){
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
    }
}
