package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/15.
 */
public class DummyScene extends Task {
    private boolean m_bMove;
    private GameView m_GameView;

    // constract
    public DummyScene(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        reset();
    }

    @Override
    public void update(){

    }

    @Override
    public void reset(){
        m_bMove = true;
        Log.d("TEST", "DummyScene::Reset");
    }

    @Override
    // go to next scene
    public boolean move(){
        return false;
    }

    @Override
    // draw
    public void draw(Canvas c){
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
    }
}