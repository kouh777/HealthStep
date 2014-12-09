package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/15.
 */
public class GachaMessage {

    // sprites
    private GameSprite m_GachaMsgBack;
    private GameSprite m_GachaYes;
    private GameSprite m_GachaNo;

    private GameView m_GameView;

    // define Sprite Position from center
    private final int YES_NO_Y = 700;
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

    public GachaMessage(GameView gv){
        m_GameView = gv;

        // new sprites
        m_GachaMsgBack = new GameSprite(gv, R.drawable.gacha_msgback);
        m_GachaYes = new GameSprite(gv, R.drawable.gacha_yes);
        m_GachaNo = new GameSprite(gv, R.drawable.gacha_no);

        m_ActionID = ACT_NOTHING;
        m_bDisplay = false;

        reset();
    }

    public void reset(){
        // set sprites
        m_GachaMsgBack.setScaleX(0);
        m_GachaMsgBack.setScaleY(0);

        m_GachaYes.setScaleX(0);
        m_GachaYes.setScaleY(0);

        m_GachaNo.setScaleX(0);
        m_GachaNo.setScaleY(0);

    }

    public void update(){
        if( m_GachaMsgBack != null ){
            m_GachaMsgBack.zoom(0.4);
        }
        if( m_GachaYes != null ){
            m_GachaYes.zoom(0.4);
        }
        if( m_GachaNo != null ){
            m_GachaNo.zoom(0.4);
        }
    }

    public void draw(Canvas c){
        if( m_GachaMsgBack != null){
            m_GachaMsgBack.draw( c, 320, 600 );
        }
        if( m_GachaYes != null){
            m_GachaYes.draw( c, YES_X, YES_NO_Y );
        }
        if( m_GachaNo != null){
            m_GachaNo.draw( c, NO_X, YES_NO_Y );
        }
    }

    public void touch( MotionEvent event ){
        if( m_GachaYes != null){
            m_GachaYes.touch(event);
            if( m_GachaYes.getTouch() ) {
                m_ActionID = ACT_YES;
                m_GameView.playSE(R.raw.se_yes);
                Log.d("GachaMsg", "Yes-touch");
            }
        }
        if( m_GachaNo != null ){
            m_GachaNo.touch(event);
            if( m_GachaNo.getTouch() ) {
                m_ActionID = ACT_NO;
                m_GameView.playSE(R.raw.se_no);
                Log.d("GachaMsg", "No-touch");
            }
        }
    }

    // getter
    public int getAction(){ return m_ActionID; }
    public boolean getDisplay(){ return m_bDisplay; }
    public GameSprite getGachaNo(){ return m_GachaNo; }

    // setter
    public void setDisplay( boolean disp ){ m_bDisplay = disp; }
}
