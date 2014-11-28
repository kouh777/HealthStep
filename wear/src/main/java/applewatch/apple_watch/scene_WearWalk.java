package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.util.Log;

/**
 * Created by KOUHO on 2014/11/24.
 */
public class scene_WearWalk extends Task {

    // this class doesn't have many sprite so don't use Vector<GameSprite>
    private GameSprite m_icoWalk;
    private GameSprite m_chrWalk;

    private GameText m_TotalWalkNum;

    private final int ICO_X = 30;
    private final int CHR_X = 50;
    private final int ICO_WALK_Y = 10;
    private final int CHR_WALK_Y = 20;

    //
    private int M_iTotalWalkingCount;

    public scene_WearWalk( GameView gv, int prio ){
        super(prio);
        m_icoWalk = new GameSprite(gv, SpriteId.SP_ICO_WALK, ICO_X, ICO_WALK_Y, R.drawable.souhosu_icon);
        m_chrWalk = new GameSprite(gv, SpriteId.SP_CHR_WALK, 0, CHR_WALK_Y, R.drawable.souhosu_moji);
        M_iTotalWalkingCount = 0;

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
        return false;
    }

    @Override
    public void touch(MotionEvent event){

    }
}
