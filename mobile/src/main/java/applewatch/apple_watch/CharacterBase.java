package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/10/16.
 */
public class CharacterBase {
    protected Vector<BitmapDrawable> m_CharResource;
    protected BitmapDrawable m_CharNowImage;

    protected int m_iCharWidth;
    protected int m_iCharHeight;
    protected int m_iPosX;
    protected int m_iPosY;

    // for animation
    protected int      m_iTimer;
    protected int      m_iIndex;
    protected boolean m_bAnimFlg;

    protected CharacterKind m_CharacterKind;
    protected int      m_CharacterID;

    private final int WINK_ANIMATION_SIZE = 4;

    protected CharacterBase(){}
    public CharacterBase(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        m_iIndex = 0;
        m_iTimer = 0;

        m_iPosX = posX;
        m_iPosY = posY;
        m_bAnimFlg = anim_flg;
        m_CharResource = new Vector<BitmapDrawable>();
    }
    public void draw(Canvas c){
        if (m_CharNowImage != null){
            doAnim();
            m_CharNowImage.setBounds(m_iPosX, m_iPosY, m_iPosX+m_iCharWidth, m_iPosY+m_iCharHeight);
            m_CharNowImage.draw(c);
        }
    }

    public void doAnim(){
        if(m_CharResource.size() == WINK_ANIMATION_SIZE){
            doWink();
        }else{
            doLaugh();
        }
    }

    public void doWink(){
        if(m_bAnimFlg){
            m_iTimer++;
            // this animation code might be inefficient, but use this way provisionally
            if(m_iTimer >= 30 ){
                m_iIndex = 1;
                m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                if(m_iTimer >= 31 ){
                    m_iIndex = 2;
                    m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                }
                if(m_iTimer >= 32){
                    m_iIndex = 1;
                    m_CharNowImage =m_CharResource.elementAt(m_iIndex);
                }
                if(m_iTimer >= 33){
                    m_iIndex = 2;
                    m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                }
                if(m_iTimer >= 34){
                    m_iIndex = 1;
                    m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                }
                if(m_iTimer >= 35){
                    m_iIndex = 0;
                    m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                    m_iTimer = 0;
                }
            }
        }
    }

    public void doLaugh(){
        if(m_bAnimFlg){
            m_iTimer++;
            if(m_iTimer%30 == 0) {
                m_CharNowImage = m_CharResource.elementAt(m_iIndex);
                m_iIndex++;
                if(m_iIndex >= m_CharResource.size())
                    m_iIndex = 0;
            }
        }
    }
    // getter
    public int characterID(){ return m_CharacterID; }
    public CharacterKind getCharacterKind(){ return m_CharacterKind; }
    public int getPosX(){ return m_iPosX;}
    public int getPosY(){ return m_iPosY;}
    public int getWidth(){ return m_iCharWidth;}

    // setter
    public void setPosX(int mx){ m_iPosX = mx;}
    public void setPosY(int my){ m_iPosY = my;}
}
