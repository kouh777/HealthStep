package applewatch.apple_watch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/27.
 */
// trim images and use part of Image Resource
public class FrameSprite extends GameSprite {

    protected Bitmap m_Bitmap;
    protected int m_iIndex;

    // relation to trimming
    protected int m_iTrimWidth;
    protected int m_iTrimOX;
    protected int m_iTrimOY;

    // display number
    protected int m_iNumber;

    // easily constructor
    public FrameSprite(GameView gv, int img , int trim_width ){
        super(gv, img);
        m_Bitmap = m_ImageResource.getBitmap();
        m_iIndex = 0;
        m_iTrimWidth = trim_width;
        m_iTrimOX = 0;
        m_iTrimOY = 0;
        m_iNumber = 0;
    }

    // main constructor
    public FrameSprite(GameView gv, int posX, int posY, int img , int trim_width){
        super(gv, posX, posY, img);
        m_Bitmap = m_ImageResource.getBitmap();
        m_iIndex = 0;
        m_iTrimWidth = trim_width;
        m_iTrimOX = 0;
        m_iTrimOY = 0;
        m_iNumber = 0;
    }

    /*
    // detail constructor
    public FrameSprite(GameView gv, int posX, int posY, int img, double scaleX, double scaleY, int alpha){
        super(gv, posX, posY, img, scaleX, scaleY, alpha);
        m_Bitmap = m_ImageResource.getBitmap();
        m_iIndex = 0;
        m_iTrimWidth = 0;
        m_iTrimOX = 0;
        m_iTrimOY = 0;
    }
    */

    @Override
    public void draw(Canvas c){
        int w = m_ImageResource.getIntrinsicWidth();
        int h = m_ImageResource.getIntrinsicHeight();
        // scaling and fetch to Surface
        w = (int)(w * m_dScaleX * m_GameView.getGamePerWidth());
        h = (int)(h * m_dScaleY * m_GameView.getGamePerHeight());

        String str = Integer.toString(m_iNumber);
        for(int i = 0; i <  str.length(); ++i ){
            char LookChar =  str.charAt(i);
            int takeNum = Character.getNumericValue(LookChar);
            m_iTrimOX =  takeNum *  m_iTrimWidth;
            // change here
            Rect src = new Rect(m_iTrimOX, m_iTrimOY, m_iTrimOX+m_iTrimWidth,m_iTrimOY+h);
            Rect dst = new Rect(m_iPosX + (i * m_iTrimWidth),m_iPosY, m_iPosX + m_iTrimWidth + (i * m_iTrimWidth),m_iPosY + h);
            // use draw bitmap
            c.drawBitmap(m_Bitmap, src, dst, null);
        }
        m_iWidth = w;
        m_iHeight = h;
    }

    // setter
    public void setNumber( int num ){ m_iNumber = num; }

    // getter
    public int getNumber(){ return m_iNumber; }
}