package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/13.
 */
public abstract class Task {
    private int m_iPriority;

    public Task( int prio ){
        m_iPriority = prio;
        TaskManager.getInstance().addList(this);
    }

    public abstract void reset();       //
    public abstract void update();
    public abstract void draw(Canvas c);        //
    public abstract boolean move();    // scene move
    public abstract void touch(MotionEvent event);

    // getter
    public int GetPriority(){ return m_iPriority;}
}
