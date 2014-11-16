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
    private final int YES_NO_Y = 150;
    private final int YES_X = 140;
    private final int NO_X = 80;

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
        m_GachaMsgBack.alignCenterHorizontal();
        m_GachaMsgBack.alignCenterVertical();

        m_GachaYes.alignCenterHorizontal();
        m_GachaYes.alignCenterVertical();
        m_GachaYes.setY( m_GachaYes.getY() + YES_NO_Y );
        m_GachaYes.setX( m_GachaYes.getX() - YES_X );

        m_GachaNo.alignCenterHorizontal();
        m_GachaNo.alignCenterVertical();
        m_GachaNo.setY( m_GachaNo.getY() + YES_NO_Y );
        m_GachaNo.setX( m_GachaNo.getX() + NO_X );
    }

    public void update(){
        /*
        if( m_GachaYes != null && m_GachaYes.getTouch() ){
            m_ActionID = ACT_YES;
            Log.d("GachaMsg","act_yes");
        }
        if( m_GachaNo != null && m_GachaNo.getTouch() ){
            m_ActionID = ACT_NO;
            Log.d("GachaMsg","act_no");
        }
        */
    }

    public void draw(Canvas c){
        if( m_GachaMsgBack != null){
            m_GachaMsgBack.draw(c);
        }
        if( m_GachaYes != null){
            m_GachaYes.draw(c);
        }
        if( m_GachaNo != null){
            m_GachaNo.draw(c);
        }
    }

    public void touch( MotionEvent event ){
        if( m_GachaYes != null){
            m_GachaYes.touch(event);
            if( m_GachaYes.getTouch() ) {
                m_ActionID = ACT_YES;
                Log.d("GachaMsg", "Yes-touch");
            }
        }
        if( m_GachaNo != null ){
            m_GachaNo.touch(event);
            if( m_GachaNo.getTouch() ) {
                m_ActionID = ACT_NO;
                Log.d("GachaMsg", "No-touch");
            }
        }
    }

    // getter
    public int getAction(){ return m_ActionID; }
    public boolean getDisplay(){ return m_bDisplay; }

    // setter
    public void setDisplay( boolean disp ){ m_bDisplay = disp; }
}
