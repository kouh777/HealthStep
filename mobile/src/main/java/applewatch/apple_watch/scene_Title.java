package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/13.
 */
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.util.Log;

public class scene_Title extends Task{
    private boolean m_bMove;
    private GameView m_GameView;
    private GameSprite m_TouchStart;
    private GameSprite m_TitleLogo;

    // define image position, from center of screen
    private final int LOGO_Y = -100;
    private final int TOUCH_START_Y_FROM_CENTER = 300;

    // define Timer
    private final int BLINK_SPAN = 10;

    // for animation
    private int m_iTimer;

    // constract
    public scene_Title(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        m_TitleLogo = new GameSprite(gv,R.drawable.title_logo_new);
        m_TouchStart = new GameSprite(gv,R.drawable.touchtostart);

        reset();
    }

    @Override
    public void    reset(){
        m_iTimer = 0;
        m_bMove = false;
        // set position
        if( m_TitleLogo != null){
            m_TitleLogo.alignCenterHorizontal();
            m_TitleLogo.alignCenterVertical();
            m_TitleLogo.setY( m_TitleLogo.getY() + LOGO_Y );
        }
        if( m_TouchStart != null){
            m_TouchStart.alignCenterHorizontal();
            m_TouchStart.alignCenterVertical();
            m_TouchStart.setY( m_TouchStart.getY() + TOUCH_START_Y_FROM_CENTER );
        }
        setTouchable( true );
        Log.d("TEST", "New Title Class");
    }

    @Override
    public void update(){
        m_iTimer++;
        m_TouchStart.blink( m_iTimer, BLINK_SPAN );
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if(m_TouchStart != null){
            m_TouchStart.draw(c);
        }
        if(m_TitleLogo != null){
            m_TitleLogo.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            // if player don't touch
            if (event.getAction() != MotionEvent.ACTION_DOWN) {
                // break
                return;
            } else {
                Log.d("Title", "get Touch action");
                new scene_Menu(m_GameView, 22);
                m_bMove = true;
            }
        }
    }
}