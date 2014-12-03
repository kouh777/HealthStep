package applewatch.apple_watch;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import android.view.GestureDetector;

/**
 * Created by KOUHO on 2014/12/03.
 */
public class MyGestureListener implements GestureDetector.OnGestureListener {

    public MyGestureListener(){
        super();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("gesture", "onDown");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Log.d("gesture", "onFling");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("gesture", "onLongPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d("gesture", "onScroll");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("gesture", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("gesture", "onSingleTapUp");
        return false;
    }

    public boolean onDoubleTap(MotionEvent e) {
        Log.d("gesture", "onDoubleTap");
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("gesture", "onDoubleTapEvent");
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("gesture", "onSingleTapConfirmed");
        return false;
    }
}
