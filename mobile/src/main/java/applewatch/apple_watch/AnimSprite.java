package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/21.
 */
// Game Sprites Base Class.This class has Image Resources as Vector<BitmapDrawable>.
public class AnimSprite extends GameSprite{

    protected int m_iIndex;
    protected Vector<BitmapDrawable> m_CharResources;

    // easily constructor
    public AnimSprite(GameView gv, int img){
        super( gv, img );
        m_CharResources = new Vector<BitmapDrawable>();
        addImg(img);

    }

    // main constructor
    public AnimSprite(GameView gv, int posX, int posY, int img){
        super( gv, posX, posY , img );
        m_CharResources = new Vector<BitmapDrawable>();
        addImg(img);
    }

    // detail constructor
    public AnimSprite(GameView gv, int posX, int posY, int img, double scaleX, double scaleY, int alpha){
        super( gv, posX, posY , img , scaleX , scaleY , alpha );
        m_CharResources = new Vector<BitmapDrawable>();
        addImg(img);
    }

    // add image resources to Vector
    public void addImg( int img ){
        m_CharResources.addElement( (BitmapDrawable)m_GameView.getResources().getDrawable(img) );
    }

    @Override
    public void draw(Canvas c){
        int w = m_ImageResource.getIntrinsicWidth();
        int h = m_ImageResource.getIntrinsicHeight();
        // scaling and fetch to Surface
        w = (int)(w * m_dScaleX * m_GameView.getGamePerWidth());
        h = (int)(h * m_dScaleY * m_GameView.getGamePerHeight());

        if( m_CharResources.elementAt(m_iIndex) != null  && m_bDisplay ){
            m_CharResources.elementAt(m_iIndex).setBounds(
                    m_iPosX + m_iPaddingLeft,
                    m_iPosY + m_iPaddingTop,
                    m_iPosX + w - m_iPaddingLeft - m_iPaddingRight,
                    m_iPosY + h - m_iPaddingTop - m_iPaddingBottom
            );
            m_CharResources.elementAt(m_iIndex).setAlpha(m_iAlpha);
            m_CharResources.elementAt(m_iIndex).draw(c);
        }
        m_iWidth = w;
        m_iHeight = h;
    }

    @Override
    // receive center ( X,Y ) and draw from center;
    public void draw(Canvas c, int cx, int cy){
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        int ww = m_ImageResource.getIntrinsicWidth();
        int hh = m_ImageResource.getIntrinsicHeight();
        int cpx = (int)( cx * m_GameView.getGamePerWidth() ) ;
        int cpy = (int)( cy * m_GameView.getGamePerHeight() );

        ww = (int)( ww * m_dScaleX * m_GameView.getGamePerWidth() );
        hh = (int)( hh * m_dScaleY * m_GameView.getGamePerHeight() );
        int x = cpx - (ww >> 1);
        int y = cpy - (hh >> 1);

        m_iPosX = x;
        m_iPosY = y;
        m_iWidth = ww;
        m_iHeight = hh;

        if (m_CharResources.elementAt(m_iIndex) != null && m_bDisplay ){
            m_CharResources.elementAt(m_iIndex).setBounds(
                    x + m_iPaddingLeft,
                    y + m_iPaddingTop,
                    x + ww - m_iPaddingLeft - m_iPaddingRight,
                    y + hh - m_iPaddingTop - m_iPaddingBottom
            );
            m_CharResources.elementAt(m_iIndex).draw(c);
        }
    }
}
