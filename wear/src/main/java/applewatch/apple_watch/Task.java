package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/13.
 */
public abstract class Task {
    private int m_iPriority;
    private boolean m_bTouchable;

    public Task(int prio){
        m_iPriority = prio;
        m_bTouchable = false;
        TaskManager.getInstance().addList(this);
        Log.d("TM::constructor", this.getClass().toString());
    }

    public abstract void reset();       //
    public abstract void update();
    public abstract void draw(Canvas c);        //
    public abstract boolean move();    // scene move
    public abstract void touch(MotionEvent event);

    // change flg
    public boolean Switch( boolean flg ){
        if(flg)
            return false;
        else
            return true;
    }

    // getter
    public int GetPriority(){ return m_iPriority;}
    public boolean getTouchable(){ return m_bTouchable; }

    // setter
    public void setTouchable( boolean touchable ){ m_bTouchable = touchable; }
}
