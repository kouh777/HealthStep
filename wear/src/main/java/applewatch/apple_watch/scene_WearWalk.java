package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.util.Log;

/**
 * Created by KOUHO on 2014/11/24.
 */
public class scene_WearWalk extends Task {

    private GameView m_GameView;

    // this class doesn't have many sprite so don't use Vector<GameSprite>
    private GameSprite m_icoWalk;
    private GameSprite m_chrWalk;

    private GameText m_TotalWalkNum;
    private GameText m_Walking;

    private final int ICO_X = 30;
    private final int CHR_X = 50;
    private final int ICO_WALK_Y = 10;
    private final int CHR_WALK_Y = 20;

    // relation to touch
    private int m_iTouchX;
    private int m_iTouchY;              // touch action down y

    //
    private int M_iTotalWalkingCount;

    private boolean m_bMove;

    public scene_WearWalk( GameView gv, int prio ){
        super(prio);
        m_GameView = gv;
        m_icoWalk = new GameSprite(gv, SpriteId.SP_ICO_WALK, ICO_X, ICO_WALK_Y, R.drawable.souhosu_icon);
        m_chrWalk = new GameSprite(gv, SpriteId.SP_CHR_WALK, 0, CHR_WALK_Y, R.drawable.souhosu_moji);
        M_iTotalWalkingCount = 0;
        m_bMove = false;

        m_TotalWalkNum = new GameText( gv, String.valueOf(M_iTotalWalkingCount), 100,100 );

        reset();
    }

    @Override
    public void reset(){
        // set sprite
        if( m_chrWalk != null ){
            m_chrWalk.alignCenterHorizontal();
            m_chrWalk.setX( m_chrWalk.getX() + CHR_X );
        }
        Log.d("scene_WearWalk","reset");
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Canvas c){
        if( m_icoWalk != null ){
            m_icoWalk.draw(c);
        }
        if( m_chrWalk != null ){
            m_chrWalk.draw(c);
        }
        if( m_TotalWalkNum != null ){
            m_TotalWalkNum.draw(c);
        }
    }
    @Override
    public boolean move(){
        return m_bMove;
    }

    @Override
    public void touch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch ( event.getAction() ){

            case MotionEvent.ACTION_DOWN:
                m_iTouchX = x;
                m_iTouchY = y;
                break;

            case MotionEvent.ACTION_MOVE:


                break;

            case MotionEvent.ACTION_UP:
                int v_x = x - m_iTouchX;
                int v_y = y - m_iTouchY;
                int distance = v_x * v_x + v_y * v_y;
                if( distance > 4500 ){
                    m_bMove = true;
                    new scene_WearTitle( m_GameView, 20 );
                    Log.d("wear_walk","swipe");
                }
                Log.d("wear_walk:distance",String.valueOf(distance) );
                break;

            case MotionEvent.ACTION_CANCEL:

                break;

        }

    }
}
