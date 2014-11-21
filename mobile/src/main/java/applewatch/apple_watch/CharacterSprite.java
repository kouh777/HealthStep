package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/21.
 */
public class CharacterSprite extends AnimSprite {
    protected CharacterKind m_CharacterKind;
    protected int m_iCharacterID;
    protected int m_iTimer;
    protected boolean m_bAnimFlg;

    // define animation images size
    protected final int WINK_ANIMATION_SIZE = 4;

    public CharacterSprite( GameView gv, int posX, int posY, int img, float scale, boolean anim_flg ){
        super(gv, posX, posY, img, scale, scale, 255);
        m_bAnimFlg = anim_flg;
    }

    public void update(){
        doAnim();
    }

    // select animation
    public void doAnim(){
        m_iTimer++;
        if(m_CharResources.size() == WINK_ANIMATION_SIZE){
            doWink();
        }else{
            doLaugh();
        }
    }

    // wink animation
    public void doWink(){
        if(m_bAnimFlg){
            // this animation code might be inefficient, but use this way provisionally
            if(m_iTimer >= 30 ){
                m_iIndex = 1;
                if(m_iTimer >= 31 ){
                    m_iIndex = 2;
                }
                if(m_iTimer >= 32){
                    m_iIndex = 1;
                }
                if(m_iTimer >= 33){
                    m_iIndex = 2;
                }
                if(m_iTimer >= 34){
                    m_iIndex = 1;
                }
                if(m_iTimer >= 35){
                    m_iIndex = 0;
                    m_iTimer = 0;
                }
            }
        }
    }

    // laugh animation
    public void doLaugh(){
        if(m_bAnimFlg){
            if(m_iTimer%30 == 0) {
                m_iIndex++;
                if(m_iIndex >= m_CharResources.size())
                    m_iIndex = 0;
            }
        }
    }

    // getter
    public int characterID(){ return m_iCharacterID; }
    public CharacterKind getCharacterKind(){ return m_CharacterKind; }
}
