package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
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

    // easily constructer
    public GameSprite(GameView gv, int img){
        m_GameView = gv;
        m_bTouch = false;
        m_iPosX = 0;
        m_iPosY = 0;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);
        m_iWidth = (int)( m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth() );
        m_iHeight = (int)( m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight() );
        m_dScaleX = 1.0;
        m_dScaleY = 1.0;
        m_iAlpha = 255; // opacity
        m_bCenterHorizontal = false;
        m_bCenterVertical = false;
    }

    // main constructor
    public GameSprite(GameView gv, int posX, int posY, int img){
        m_GameView = gv;
        m_bTouch = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);
        m_iWidth = (int)( m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth() );
        m_iHeight = (int)( m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight() );
        m_dScaleX = 1.0;
        m_dScaleY = 1.0;
        m_iAlpha = 255; // opacity
        m_bCenterHorizontal = false;
        m_bCenterVertical = false;
    }

    // detail constructor
    public GameSprite(GameView gv, int posX, int posY, int img, double scaleX, double scaleY, int alpha){
        m_GameView = gv;
        m_ImageResource = (BitmapDrawable)gv.getResources().getDrawable(img);
        m_bTouch = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iPosX = (int)( m_iPosX * gv.getGamePerWidth() );
        m_iPosY = (int)( m_iPosY * gv.getGamePerHeight() );
        m_iWidth = (int)( m_ImageResource.getIntrinsicWidth() * gv.getGamePerWidth() * scaleX );
        m_iHeight = (int)( m_ImageResource.getIntrinsicHeight() * gv.getGamePerHeight() * scaleY);
        m_dScaleX = scaleX;
        m_dScaleY = scaleY;
        m_iAlpha = alpha;
    }

    public void draw(Canvas c){
        int w = m_ImageResource.getIntrinsicWidth();
        int h = m_ImageResource.getIntrinsicHeight();

        // scaling and fetch to Surface
        w = (int)(w * m_dScaleX * m_GameView.getGamePerWidth());
        h = (int)(h * m_dScaleY * m_GameView.getGamePerHeight());

        if( m_ImageResource != null){
            m_ImageResource.setBounds( m_iPosX, m_iPosY, m_iPosX + w, m_iPosY + h);
            m_ImageResource.setAlpha(m_iAlpha);
            m_ImageResource.draw(c);
        }

        m_iWidth = w;
        m_iHeight = h;
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
            }else{
                m_bTouch = false;
                return;
            }
        }
    }

    // centering image
    public void alignCenterHorizontal(){
        if( !m_bCenterHorizontal) {
            int w = m_GameView.getGameWidth();
            int ww = m_ImageResource.getIntrinsicWidth();
            ww = (int)( ww * m_GameView.getGamePerWidth() );
            int cx = (w - ww) >> 1;
            m_iPosX += cx;
            m_bCenterHorizontal = true;
        }
    }

    public void alignCenterVertical(){
        if( !m_bCenterVertical ) {
            int h = m_GameView.getGameHeight();
            int hh = m_ImageResource.getIntrinsicHeight();
            hh = (int)( hh * m_GameView.getGamePerHeight() );
            int cy = (h - hh) >> 1;
            m_iPosY += cy;
            m_bCenterVertical = true;
        }
    }

    // getter
    public int getX(){ return m_iPosX; }
    public int getY(){ return m_iPosY; }
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

}
