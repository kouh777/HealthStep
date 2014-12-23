package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/15.
 */
public class ObjectGroup  {
    protected GameView m_GameView;
    protected Vector<GameText> m_VecText;
    protected Vector<GameSprite> m_VecSprite;
    protected int m_iPosX;
    protected boolean m_bTouch;

    public ObjectGroup( GameView gv ){
        m_GameView = gv;
        reset();
    }

    public void reset(){
        m_VecText = new Vector<GameText>();
        m_VecSprite = new Vector<GameSprite>();
        m_iPosX = 0;
        m_bTouch = false;
    }

    public void update(){
        if( m_VecText != null ) {
            for (int i = 0; i < m_VecText.size(); i++) {
                m_VecText.get(i).update();
            }
        }
    }
    public void draw( Canvas c){
        if( m_VecText != null ) {
            for (int i = 0; i < m_VecText.size(); i++) {
                m_VecText.get(i).draw(c);
            }
        }
        if( m_VecSprite != null ) {
            for (int i = 0; i < m_VecSprite.size(); i++) {
                m_VecSprite.get(i).draw(c);
            }
        }
    }
    public void moveX( int mx ){
        if( m_VecText != null ) {
            for (int i = 0; i < m_VecText.size(); i++) {
                m_VecText.get(i).setX(m_VecText.get(i).getX() + mx);
            }
        }
        if( m_VecSprite != null ) {
            for (int i = 0; i < m_VecSprite.size(); i++) {
                m_VecSprite.get(i).setX(m_VecSprite.get(i).getX() + mx);
            }
        }
        m_iPosX += mx;
    }

    public void addText( GameText text ){
        if( text != null ) m_VecText.addElement(text);
    }

    public void addSprites( GameSprite sprite ){
        if( sprite != null ) m_VecSprite.addElement(sprite);
    }

    // getter
    public int getX(){
        return m_iPosX;
    }
    public boolean getTouch(){
        return m_bTouch;
    }
}
