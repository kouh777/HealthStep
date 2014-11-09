package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/16.
 */
public interface CharacterBase {
    public void doAnim();
    public void draw(Canvas c);
    public int characterID();
}
