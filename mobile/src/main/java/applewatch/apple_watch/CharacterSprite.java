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
    protected boolean m_bLipAnim;

    // define animation images size
    protected final int WINK_ANIMATION_SIZE = 5;

    // define lip animation
    private final int LIP_OPEN_IMG = 3;
    private final int LIP_OPEN_BETWEEN_IMG = 4;
    private final int LIP_CLOSE_IMG = 0;
    private final int LIP_OPEN_SPAN = 3;
    private final int LIP_OPEN_BETWEEN_SPAN = 4;
    private final int LIP_CLOSE_SPAN = 16;

    // character profile
    protected String m_StrName;
    protected String m_StrBirthday;
    protected String m_StrGender;
    protected String m_StrComment;

    // character messages( with voice )
    protected String m_StrHello;
    protected String m_StrWhether;
    protected String m_StrYell;

    // voice data
    protected GameSound m_CharVoiceHello;
    protected GameSound m_CharVoiceWhether;
    protected GameSound m_CharVoiceYell;

    // define wait time
    protected int m_iHelloWait;
    protected int m_iWhetherWait;
    protected int m_iYellWait;

    public CharacterSprite( GameView gv, int posX, int posY, int img, float scale, boolean anim_flg ){
        super(gv, posX, posY, img, scale, scale, 255);
        m_bAnimFlg = anim_flg;
        m_bLipAnim = false;
        // initialize strings
        m_StrName ="";
        m_StrBirthday ="";
        m_StrGender ="";
        m_StrComment ="";
        m_StrHello = "";
        m_StrWhether="";
        m_StrYell="";

        m_iHelloWait = 0;
        m_iWhetherWait = 0;
        m_iYellWait = 0;
    }

    public void update(){
        doAnim();
    }

    // select animation
    public void doAnim(){
        m_iTimer++;
        if(m_CharResources.size() == WINK_ANIMATION_SIZE){
            if( m_bLipAnim ) {
                doLipAnim();
            }else {
                doWink();
            }
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
        if( m_bAnimFlg ){
            if(m_iTimer%10 == 0) {
                m_iIndex++;
                if(m_iIndex >= m_CharResources.size())
                    m_iIndex = 0;
            }
        }
    }

    // lip animation
    public void doLipAnim(){
        if( m_bAnimFlg ){
            m_iIndex = LIP_OPEN_BETWEEN_IMG;
            if( m_iTimer >= 2 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }
            if( m_iTimer >= 4 ){
                m_iIndex = LIP_OPEN_IMG;
            }
            if( m_iTimer >= 5 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }
            if( m_iTimer >= 7 ){
                m_iIndex = LIP_OPEN_IMG;
            }
            if( m_iTimer >= 8 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }
            if( m_iTimer >= 10 ){
                m_iIndex = LIP_OPEN_IMG;
            }
            if( m_iTimer >= 11 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }
            if( m_iTimer >= 13 ){
                m_iIndex = LIP_CLOSE_IMG;
            }
            if( m_iTimer >= 14 ){
                m_iTimer = 0;
            }

            /*
            // first idea
            if( m_iTimer % 7 == 0 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }else if( m_iTimer % 5 == 0 ){
                m_iIndex = LIP_OPEN_IMG;
            }else if( m_iTimer % 3 == 0 ){
                m_iIndex = LIP_OPEN_BETWEEN_IMG;
            }else if( m_iTimer % 2 == 0  ){
                m_iIndex = LIP_CLOSE_IMG;
            }
            */

        }
    }

    //setter
    public void setLipAnim( boolean anim_flg ){ m_bLipAnim = anim_flg; }

    // getter
    public int characterID(){ return m_iCharacterID; }
    public CharacterKind getCharacterKind(){ return m_CharacterKind; }
    public String getStrName(){ return m_StrName; }
    public String getStrBirthday(){ return m_StrBirthday; }
    public String getStrGender(){ return m_StrGender; }
    public String getStrComment(){ return m_StrComment; }
    public String getStrHello(){ return m_StrHello; }
    public String getStrWhether(){ return m_StrWhether; }
    public String getStrYell(){ return m_StrYell; }
    public GameSound getCharVoiceHello(){ return m_CharVoiceHello; }
    public GameSound getCharVoiceWhether(){ return m_CharVoiceWhether; }
    public GameSound getCharVoiceYell(){ return m_CharVoiceYell; }
    public int getWaitHello(){ return m_iHelloWait; }
    public int getWaitWhether(){ return m_iWhetherWait; }
    public int getWaitYell(){ return m_iYellWait; }
}
