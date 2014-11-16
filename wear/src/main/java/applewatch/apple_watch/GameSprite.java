package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/14.
 */
// Game Sprite Base Class
public class GameSprite {

    private GameView m_GameView;
    private BitmapDrawable m_ImageResource;

    private int m_iPosX;
    private int m_iPosY;
    private int m_iWidth;
    private int m_iHeight;

    private boolean m_bTouch;

    private double m_dScaleX;
    private double m_dScaleY;
    private int m_iAlpha;   // in API9, I can't use  BitmapDrawable.getAlpha(), so create this

    // centering flag
    private boolean m_bCenterHorizontal;
    private boolean m_bCenterVertical;

    // likely to CSS of padding
    private int m_iPaddingTop;
    private int m_iPaddingRight;
    private int m_iPaddingBottom;
    private int m_iPaddingLeft;

    // Image display flag
    private boolean m_bDisplay;

    // easily constructer
    public GameSprite(GameView gv, int img){
        m_GameView = gv;
        m_bTouch = false;
        m_iPosX = 0;
        m_iPosY = 0;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_dScaleX = 1.0;
        m_dScaleY = 1.0;
        m_iAlpha = 255; // opacity
        m_bCenterHorizontal = false;
        m_bCenterVertical = false;

        m_iPaddingTop = 0;
        m_iPaddingRight = 0;
        m_iPaddingBottom = 0;
        m_iPaddingLeft = 0;
        m_bDisplay = true;

        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);

        if( !(m_ImageResource instanceof BitmapDrawable ) ) {
            m_ImageResource = null;
        }
        if( m_ImageResource != null ) {
            m_iWidth = (int) (m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth());
            m_iHeight = (int) (m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight());
        }
    }

    // main constructor
    public GameSprite(GameView gv, int posX, int posY, int img){
        m_GameView = gv;
        m_bTouch = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_dScaleX = 1.0;
        m_dScaleY = 1.0;
        m_iAlpha = 255; // opacity
        m_bCenterHorizontal = false;
        m_bCenterVertical = false;

        m_iPaddingTop = 0;
        m_iPaddingRight = 0;
        m_iPaddingBottom = 0;
        m_iPaddingLeft = 0;
        m_bDisplay = true;

        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);

        if( !(m_ImageResource instanceof BitmapDrawable ) ) {
            m_ImageResource = null;
        }
        if( m_ImageResource != null ) {
            m_iWidth = (int) (m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth());
            m_iHeight = (int) (m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight());
        }
    }

    // detail constructor
    public GameSprite(GameView gv, int posX, int posY, int img, double scaleX, double scaleY, int alpha){
        m_GameView = gv;
        m_bTouch = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_dScaleX = scaleX;
        m_dScaleY = scaleY;
        m_iAlpha = alpha;

        m_iPaddingTop = 0;
        m_iPaddingRight = 0;
        m_iPaddingBottom = 0;
        m_iPaddingLeft = 0;
        m_bDisplay = true;

        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);

        if( !(m_ImageResource instanceof BitmapDrawable)  ) {
            m_ImageResource = null;
        }
        if( m_ImageResource != null ) {
            m_iWidth = (int)( m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth() * scaleX );
            m_iHeight = (int)( m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight() * scaleY);
        }
    }

    public void draw(Canvas c){
        if( m_ImageResource != null){
            int w = m_ImageResource.getIntrinsicWidth();
            int h = m_ImageResource.getIntrinsicHeight();

            // scaling and fetch to Surface
            w = (int)(w * m_dScaleX * m_GameView.getGamePerWidth());
            h = (int)(h * m_dScaleY * m_GameView.getGamePerHeight());

            m_iWidth = w;
            m_iHeight = h;

            if( m_bDisplay ){
                m_ImageResource.setBounds(
                        m_iPosX + m_iPaddingLeft,
                        m_iPosY + m_iPaddingTop,
                        m_iPosX + w - m_iPaddingLeft - m_iPaddingRight,
                        m_iPosY + h - m_iPaddingTop - m_iPaddingBottom
                );
                m_ImageResource.setAlpha(m_iAlpha);
                m_ImageResource.draw(c);
            }
        }
    }

    // receive center ( X,Y ) and draw from center;
    public void draw(Canvas c, int cx, int cy){
        if( m_ImageResource != null ) {
            int w = m_GameView.getWidth();
            int h = m_GameView.getHeight();
            int ww = m_ImageResource.getIntrinsicWidth();
            int hh = m_ImageResource.getIntrinsicHeight();
            int cpx = (int) (cx * m_GameView.getGamePerWidth());
            int cpy = (int) (cy * m_GameView.getGamePerHeight());

            ww = (int) (ww * m_dScaleX * m_GameView.getGamePerWidth());
            hh = (int) (hh * m_dScaleY * m_GameView.getGamePerHeight());
            int x = cpx - (ww >> 1);
            int y = cpy - (hh >> 1);

            if( m_bDisplay ){
                m_ImageResource.setBounds(
                        x + m_iPaddingLeft,
                        y + m_iPaddingTop,
                        x + ww - m_iPaddingLeft - m_iPaddingRight,
                        y + hh - m_iPaddingTop - m_iPaddingBottom
                );
                m_ImageResource.draw(c);
            }
        }
    }

    // if touch in sprite, return true
    public void touch(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            m_bTouch = false;
            return;
        }else {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if( x > m_iPosX && x < m_iPosX + m_iWidth &&
                    y > m_iPosY && y < m_iPosY + m_iHeight){
                m_bTouch = true;
                Log.d("GameSprite", "m_bTouch = true");
            }else{
                m_bTouch = false;
                Log.d("GameSprite", "m_bTouch = false");
                return;
            }
        }
    }

    // centering image
    public void alignCenterHorizontal(){
        if( m_ImageResource != null ){
            if (!m_bCenterHorizontal) {
                int w = m_GameView.getGameWidth();
                int ww = m_ImageResource.getIntrinsicWidth();
                ww = (int) (ww * m_GameView.getGamePerWidth());
                int cx = (w - ww) >> 1;
                m_iPosX += cx;
                m_bCenterHorizontal = true;
            }
        }
    }

    public void alignCenterVertical(){
        if( m_ImageResource != null ) {
            if (!m_bCenterVertical) {
                int h = m_GameView.getGameHeight();
                int hh = m_ImageResource.getIntrinsicHeight();
                hh = (int) (hh * m_GameView.getGamePerHeight());
                int cy = (h - hh) >> 1;
                m_iPosY += cy;
                m_bCenterVertical = true;
            }
        }
    }

    // fade animation . using in update method
    public void fade( int timer, int fade_start_time, int fade_end_time, boolean fade_in ){
        if( timer > fade_start_time && timer < fade_end_time ){
            int fade_speed = 256 / ( fade_end_time - fade_start_time );
            if( fade_in ) {  // fade in
                m_iAlpha -= fade_speed;
            }else{ // fade out
                m_iAlpha += fade_speed;
            }
        }
        if( fade_in )
            if( timer > fade_end_time || m_iAlpha < 0 ) m_iAlpha = 0;
        else
            if( timer > fade_end_time || m_iAlpha > 255) m_iAlpha = 255;
    }
    // moving animation using in update method
    public void moveToX( int timer, int move_start_time, int move_end_time, int from_x, int to_x ){
        if( timer > move_start_time && timer < move_end_time ){
            int move_speed = ( to_x - from_x ) / ( move_end_time - move_start_time );
            setX( getX() + move_speed );
        }
    }
    public void moveToY( int timer, int move_start_time, int move_end_time, int from_y, int to_y ){
        if( timer > move_start_time && timer < move_end_time ){
            int move_speed = ( to_y - from_y ) / ( move_end_time - move_start_time );
            setY( getY() + move_speed );
        }
    }
    public void moveX( int timer, int move_start_time, int move_end_time, int speed ){
        if( timer > move_start_time && timer < move_end_time ){
            setX( getX() + speed);
        }
    }
    public void moveY( int timer, int move_start_time, int move_end_time, int speed ){
        if( timer > move_start_time && timer < move_end_time ){
            setY( getY() + speed);
        }
    }

    // getter
    public int getX(){ return m_iPosX; }
    public int getY(){ return m_iPosY; }
    public int getWidth() { return m_iWidth; }
    public int getCX() {return ( getX() + ( getWidth() >> 1 ) ); }      // centerX
    public int getCY() {return ( getY() + ( getHeight() >> 1 ) ); }     // centerY
    public int getHeight() { return m_iHeight; }
    public BitmapDrawable getImage(){ return m_ImageResource;}
    public double getScaleX(){ return m_dScaleX; }
    public double getScaleY(){ return m_dScaleY; }
    public int getAlpha(){ return m_iAlpha;}
    public boolean getTouch(){ return m_bTouch; }

    // setter
    public void setX(int mx){
        m_iPosX = (int)( mx * m_GameView.getGamePerWidth() );
    }
    public void setY(int my){
        m_iPosY = (int)( my * m_GameView.getGamePerHeight() );
    }
    public void setScaleX(double sx){ m_dScaleX = sx; }
    public void setScaleY(double sy){ m_dScaleY = sy; }
    public void setAlpha(int alpha){ m_iAlpha = alpha; }
    public void setPadding(int top, int right, int bottom, int left){
        m_iPaddingTop = (int)( top * m_GameView.getGamePerHeight() );
        m_iPaddingRight = (int)( right * m_GameView.getGamePerWidth() );
        m_iPaddingBottom = (int)( bottom * m_GameView.getGamePerHeight() );
        m_iPaddingLeft = (int)( left * m_GameView.getGamePerWidth() );
    }
    public void setPadding(int top_bottom, int right_left){
        m_iPaddingTop = (int)( top_bottom * m_GameView.getGamePerHeight() );
        m_iPaddingRight = (int)( right_left * m_GameView.getGamePerWidth() );
        m_iPaddingBottom = (int)( top_bottom * m_GameView.getGamePerHeight() );
        m_iPaddingLeft = (int)( right_left * m_GameView.getGamePerWidth() );
    }
    public void setDisplay (boolean disp ){ m_bDisplay = disp; }

}
