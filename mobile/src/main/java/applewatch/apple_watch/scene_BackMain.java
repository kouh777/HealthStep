package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/13.
 */
public class scene_BackMain extends Task{
    private Paint m_Paint;

    private GameSprite m_BackGroundMain;
    private GameSprite m_CloudLeft;
    private GameSprite m_CloudRight;

    private boolean m_bMove;
    private GameView m_GameView;

    // for animation
    private double m_ScaleSpeed;                // speed of scale
    private final double MAX_SCALE = 0.9;       //
    private final double MINIMUM_SCALE = 0.8;  //

    // define sprites' center position
    private final int CL_L_X = 239;
    private final int CL_L_Y = 457;
    private final int CL_R_X = 484;
    private final int CL_R_Y = 399;

    // constract
    public scene_BackMain(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;

        // new sprite classes
        m_BackGroundMain = new GameSprite(gv, R.drawable.m_game_screen);
        m_CloudLeft = new GameSprite(gv, R.drawable.kumo1);
        m_CloudRight = new GameSprite(gv, R.drawable.kumo2);

        m_ScaleSpeed = 0.0012;

        reset();
    }

    @Override
    public void update(){

        // left cloud
        m_CloudLeft.setScaleX( m_CloudLeft.getScaleX() - m_ScaleSpeed );
        m_CloudLeft.setScaleY( m_CloudLeft.getScaleY() - m_ScaleSpeed );
        if( m_CloudLeft.getScaleX() < MINIMUM_SCALE ){
            m_CloudLeft.setScaleX( MINIMUM_SCALE );
            m_ScaleSpeed *= -1;
        }
        if(m_CloudLeft.getScaleY() > MAX_SCALE){
            m_CloudLeft.setScaleY( MAX_SCALE );
            m_ScaleSpeed *= -1;
        }


        // right cloud
        m_CloudRight.setScaleX( m_CloudRight.getScaleX() - m_ScaleSpeed );
        m_CloudRight.setScaleY( m_CloudRight.getScaleY() - m_ScaleSpeed );
        if( m_CloudRight.getScaleX() < MINIMUM_SCALE ){
            m_CloudRight.setScaleX( MINIMUM_SCALE );
            m_ScaleSpeed *= -1;
        }
        if(m_CloudRight.getScaleY() > MAX_SCALE){
            m_CloudRight.setScaleY( MAX_SCALE );
            m_ScaleSpeed *= -1;
        }

    }

    @Override
    public void    reset(){
        m_bMove = false;
        Log.d("TEST", "New BackMain Class");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if (m_BackGroundMain != null){
            m_BackGroundMain.draw(c);
        }
        if (m_CloudLeft != null){
            m_CloudLeft.draw( c, CL_L_X, CL_L_Y );
        }
        if (m_CloudRight != null){
            m_CloudRight.draw( c, CL_R_X, CL_R_Y );
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
    }
}
