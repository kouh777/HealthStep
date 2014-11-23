package applewatch.apple_watch;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/23.
 */
public class GameTouch  {

    // touch action
    private float m_fDownX;     // ACTION_DOWN getX()
    private float m_fDownY;     // ACTION_DOWN getY()
    private float m_fMoveX;     // ACTION_MOVE getX()
    private float m_fMoveY;     // ACTION_MOVE getY()
    private float m_fSwipeX;    // swipe X
    private float m_fSwipeY;    // swipe Y

    // singleton
    private static GameTouch instance = new GameTouch();
    public static GameTouch getInstance(){ return instance; }
    private GameTouch(){
    }

    // touch
    public void    touch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch( event.getAction() ){
            case MotionEvent.ACTION_DOWN :
                Log.d("getAction()", "ACTION_DOWN");
                break;

            case  MotionEvent.ACTION_MOVE :
                Log.d("getAction()", "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP :
                Log.d("getAction()", "ACTION_UP");
                break;

            case MotionEvent.ACTION_CANCEL :
                Log.d("getAction()", "ACTION_CANCEL");
                break;

            case MotionEvent.ACTION_SCROLL :
                Log.d("getAction()", "ACTION_SCROLL");
                break;
        }
    }
/*
    private final GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        　　　　public boolean onDoubleTap(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onDoubleTap");
            　　　　　　return super.onDoubleTap(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onDoubleTapEvent(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onDoubleTapEvent");
            　　　　　　return super.onDoubleTapEvent(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onDown(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onDown");
            　　　　　　return super.onDown(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,　float velocityY) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onFling");
            　　　　　　return super.onFling(e1, e2, velocityX, velocityY);
            　　　　}

        　　　　@Override
        　　　　public void onLongPress(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onLongPress");
            　　　　　　super.onLongPress(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onScroll");
            　　　　　　return super.onScroll(e1, e2, distanceX, distanceY);
            　　　　}

        　　　　@Override
        　　　　public void onShowPress(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onShowPress");
            　　　　　　super.onShowPress(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onSingleTapConfirmed(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onSingleTapConfirmed");
            　　　　　　return super.onSingleTapConfirmed(e);
            　　　　}

        　　　　@Override
        　　　　public boolean onSingleTapUp(MotionEvent e) {
            　　　　　　// TODO Auto-generated method stub
            　　　　　　Log.v("Gesture", "onSingleTapUp");
            　　　　　　return super.onSingleTapUp(e);
            　　　　}

    }
*/
}
