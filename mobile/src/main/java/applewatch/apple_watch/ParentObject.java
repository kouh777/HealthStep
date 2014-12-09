package applewatch.apple_watch;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/05.
 */
public class ParentObject {

    protected GameView m_GameView;
    protected int m_iPosX = 0;
    protected int m_iPosY = 0;
    protected int m_iWidth = 0;
    protected int m_iHeight = 0;
    protected int m_iAlpha = 255;
    protected Vector<ChildObject> m_VecObj;

    public ParentObject( GameView gv , int pos_x, int pos_y, int width, int height, int alpha ){
        m_iPosX = (int)( pos_x * gv.getGamePerWidth() );
        m_iPosY = (int)( pos_y * gv.getGameHeight() );
        m_iWidth = (int)( width * gv.getGamePerWidth() );
        m_iHeight = (int)( height * gv.getGamePerHeight() );
        m_iAlpha = alpha;
        m_VecObj = new Vector<ChildObject>();
    }

    public void setPos( int x, int y ){
        m_iPosX = (int)( x * m_GameView.getGamePerWidth() );
        m_iPosY = (int)( y * m_GameView.getGamePerHeight() );
        for( int i = 0; i < m_VecObj.size(); i++ ){
            m_VecObj.get(i).setPos( m_iPosX , m_iPosY );
        }
    }

    public void addObject( ChildObject obj ){
        if( obj != null ) {
            m_VecObj.add(obj);
        }
    }

    public void update(){
        for( int i = 0; i < m_VecObj.size(); i++ ){
            m_VecObj.get(i).update();
        }
    }
}