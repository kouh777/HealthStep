package applewatch.apple_watch;

import android.graphics.Canvas;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by KOUHO on 2014/11/16.
 */
public class scene_PlayerInput extends Task{

    private boolean m_bMove;
    private GameView m_GameView;

    // input player name
    private EditText p_name;
    private SpannableStringBuilder sb;

    private InputSprite m_InputSprite;

    // constract
    public scene_PlayerInput(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_InputSprite = new InputSprite(gv, R.drawable.gacha_no);

        reset();
    }

    @Override
    public void update(){
        if( m_InputSprite != null ){
//            m_InputSprite.update();
        }
    }

    @Override
    public void    reset(){
        m_bMove = false;

        setTouchable( true );
        Log.d("TEST", "New PlayerInput Class");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_InputSprite != null ){
            m_InputSprite.draw(c);
        }
        if( m_bMove ){
            new scene_Menu( m_GameView, 25);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if( m_InputSprite != null ){
                m_InputSprite.touch(event);
                if( m_InputSprite.getTouch() ){
                    m_bMove = true;
                }
            }
        }
    }
    // getter
}
