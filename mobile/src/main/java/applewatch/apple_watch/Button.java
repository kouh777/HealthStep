package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/15.
 */

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Button {
    public boolean isTouched();     // if image is touched, return true.
    public int nextSceneID();       //
    public void draw(Canvas c);
    public void touch(MotionEvent event);
}
