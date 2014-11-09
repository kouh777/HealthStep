package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/13.
 */

import android.graphics.Canvas;
import android.view.MotionEvent;

// game scene base interface
public interface GamePart{
    public void     reset();         // reset
    public boolean  move();          // go to next scene
    public int      nextSceneID();    // id of next scene
    public void     draw(Canvas c);  // draw
    public void     touch(MotionEvent event);    // touch event
}