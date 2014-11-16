package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import java.util.Random;


/**
 * Created by KOUHO on 2014/10/16.
 */
public class scene_Gacha extends Task {
    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    private GachaMessage m_GachaMessage;
    private gacha_Character m_gacha_Character;
    private gacha_Animation m_gacha_Animation;

    private BitmapDrawable m_GachaBack;
    private GameSprite m_H1;

    private boolean m_bGachaAnim;
    private boolean m_bBack;
    private boolean m_bIsGachaCharacter;

    // for aimation for test
    private int m_iTimer;
    private final int GACHA_END = 30;

    private int GachaCharacter;
    private int m_iTouchAciton;

    // define sprite position
    private final int H1_Y = 140;

    // constract
    public scene_Gacha(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_GachaBack = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.black_bg);

        m_GachaMessage = new GachaMessage(gv);

        // common
        m_H1 = new GameSprite( gv, 0, H1_Y, R.drawable.h1_gacha );
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv, 0 ,0);

        reset();
    }

    @Override
    public void reset(){

        m_iTimer = 0;
        m_bMove = false;
        m_bGachaAnim = false;
        m_bIsGachaCharacter = false;
        m_bBack = false;
        m_iTouchAciton = GachaMessage.ACT_NOTHING;

        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();

        setTouchable( true );
        Log.d("TEST", "scene_Gacha::Reset");
    }

    public void doGacha(){
        // create random number(from 0 to character data size)
        Random r = new Random();
        boolean b_Flg = false;
        while(b_Flg == false) {
            int rnd = r.nextInt(20);

            // if gacha complete, escape from loop
            if (PlayerData.getInstance().CharacterComplete() == true){
                b_Flg = true;
            }
            if (PlayerData.getInstance().getUnlockCharacter()[rnd] == false) {
                PlayerData.getInstance().setUnlockCharacter(rnd, true);
                GachaCharacter = rnd;
                m_gacha_Animation = new gacha_Animation(m_GameView,0,0);
                b_Flg = true;
            }
        }
    }

    @Override
    public void update() {

        if( m_GachaMessage != null ) {
            m_GachaMessage.update();
            if ( m_iTouchAciton == GachaMessage.ACT_YES ){
                m_iTouchAciton = GachaMessage.ACT_NOTHING;
                m_MenuGroup.getBtnGacha().setTouch( false );
                m_GachaMessage  = null;
                m_bBack = true;
                doGacha();
            }
            if( m_iTouchAciton == GachaMessage.ACT_NO ){
                m_iTouchAciton = GachaMessage.ACT_NOTHING;
                m_MenuGroup.getBtnGacha().setTouch( false );
                m_GachaMessage  = null;
            }
        }

        if (m_gacha_Animation != null) {
            if (!m_gacha_Animation.move() ) {
                m_gacha_Animation.update();
            }else if (!m_bIsGachaCharacter ) {
                m_gacha_Animation = null;
                m_gacha_Character = new gacha_Character(m_GameView, this, m_GameView.getGameWidth(), 400);
                m_bIsGachaCharacter = true;
            }
        }
        if( m_gacha_Character != null ){
            if( m_bIsGachaCharacter ) {
                m_iTimer++;
            }
            if( m_iTimer > GACHA_END ){
                m_gacha_Character = null;
                if( m_GachaBack != null )
                    m_GachaBack = null;
                m_iTimer = 0;
            }
        }

        if( m_MenuGroup != null ) {
            m_MenuGroup.update();
            m_bMove = m_MenuGroup.getMove();
        }
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if(m_UiGroup != null){
            m_UiGroup.draw(c);
        }
        if(m_H1 != null){
            m_H1.draw(c);
        }
        if (m_MenuGroup != null) {
            m_MenuGroup.draw(c);
        }
        if( m_GachaMessage != null ){
            m_GachaMessage.draw(c);
        }
        if(m_GachaBack != null && m_bBack){
            m_GachaBack.setBounds(0,0,m_GameView.getGameWidth(),m_GameView.getGameHeight());
            m_GachaBack.setAlpha(200);
            m_GachaBack.draw(c);
        }
        // draw gacha animation
        if(m_gacha_Animation != null){
            m_gacha_Animation.draw(c);
        }
        // draw get character
        if(m_gacha_Character != null){
            m_gacha_Character.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
            if( m_GachaMessage != null ){
                m_GachaMessage.touch(event);
                m_iTouchAciton = m_GachaMessage.getAction();
            }
        }
    }

    public int getGachaCharacter(){
        return GachaCharacter;
    }
}