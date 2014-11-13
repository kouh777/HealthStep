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
    // image resources
    private BitmapDrawable m_BackMain;
    private BitmapDrawable m_CloudLeft;
    private BitmapDrawable m_CloudRight;

    private boolean m_bMove;
    private GameView m_GameView;

    // for animation
    private double m_ScaleLX;   // scale of left cloud width
    private double m_ScaleLY;   // scale of left cloud height
    private double m_ScaleRX;   // scale of right cloud width
    private double m_ScaleRY;   // scale of right cloud height

    private double m_ScaleSpeed;    // speed of scale
    private final double MAX_SCALE = 1.0;    //
    private final double MINIMUM_SCALE = 0.75;   //

    // constract
    public scene_BackMain(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;

        // background resource
        m_BackMain = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.m_game_screen);
        m_CloudLeft = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.kumo1);
        m_CloudRight = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.kumo2);

        // for animation
        m_ScaleLX = 1.0;
        m_ScaleLY = 1.0;
        m_ScaleRX = 1.0;
        m_ScaleRY = 1.0;

        m_ScaleSpeed = 0.005;

        reset();
    }

    @Override
    public void update(){
        m_ScaleLX -= m_ScaleSpeed;
        m_ScaleLY -= m_ScaleSpeed;
        if(m_ScaleLX < MINIMUM_SCALE){
            m_ScaleLX = MINIMUM_SCALE;
            m_ScaleSpeed *= -1;
        }
        if(m_ScaleLY > MAX_SCALE){
            m_ScaleLY = MAX_SCALE;
            m_ScaleSpeed *= -1;
        }
        m_ScaleRX -= m_ScaleSpeed;
        m_ScaleRY -= m_ScaleSpeed;
        if(m_ScaleRX < MINIMUM_SCALE){
            m_ScaleRX = MINIMUM_SCALE;
            m_ScaleSpeed *= -1;
        }
        if(m_ScaleRY > MAX_SCALE){
            m_ScaleRY = MAX_SCALE;
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
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();

        // for centering left cloud
        int clx = (int)( 230 * m_GameView.getGamePerWidth() ) ;
        int cly = (int)( 457 * m_GameView.getGamePerHeight() );
        int lw = m_CloudLeft.getIntrinsicWidth();
        int lh = m_CloudLeft.getIntrinsicHeight();
        lw = (int)( lw * m_ScaleLX);
        lh = (int)( lh * m_ScaleLY);
        int lx = clx - (lw >> 1);
        int ly = cly - (lh >> 1);

        // for centering right cloud
        int crx = (int)( 484 * m_GameView.getGamePerWidth() ) ;
        int cry = (int)( 399 * m_GameView.getGamePerHeight() );
        int rw = m_CloudRight.getIntrinsicWidth();
        int rh = m_CloudRight.getIntrinsicHeight();
        rw = (int)( rw * m_ScaleLX);
        rh = (int)( rh * m_ScaleLY);
        int rx = crx - (rw >> 1);
        int ry = cry - (rh >> 1);

        if (m_BackMain != null){
            m_BackMain.setBounds(0,0,w,h);
            m_BackMain.draw(c);
        }
        if (m_CloudLeft != null){
            m_CloudLeft.setBounds( lx , ly , lx + lw , ly + lh );
            m_CloudLeft.draw(c);
        }
        if (m_CloudRight != null){
            m_CloudRight.setBounds( rx, ry , rx + rw , ry + rh );
            m_CloudRight.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
    }
}
