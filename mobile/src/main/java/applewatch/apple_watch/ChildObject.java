package applewatch.apple_watch;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/05.
 */
public class ChildObject {

    protected GameView m_GameView;
    protected int m_iPosX = 0;
    protected int m_iPosY = 0;
    protected int m_iWidth = 0;
    protected int m_iHeight = 0;
    protected int m_iAlpha = 255;

    protected int m_iFromParentX = 0;
    protected int m_iFromParentY = 0;

    public ChildObject( GameView gv , int pos_x, int pos_y, int width, int height, int alpha ){
        m_iPosX = (int)( pos_x * gv.getGamePerWidth() );
        m_iPosY = (int)( pos_y * gv.getGameHeight() );
        m_iWidth = (int)( width * gv.getGamePerWidth() );
        m_iHeight = (int)( height * gv.getGamePerHeight() );
        m_iAlpha = alpha;
    }

    public void setPos( int x, int y ){
        m_iPosX = (int)( x + m_iFromParentX * m_GameView.getGamePerWidth() );
        m_iPosY = (int)( y + m_iFromParentY * m_GameView.getGamePerHeight() );
    }

    public void update(){

    }
}
