package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class scene_Gallery extends Task{

    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private btn_Character[] m_btn_Character;
    private UiGroup m_UiGroup;

    // sprites
    private GameSprite m_H1;

    // define position
    private final int H1_Y = 140;
    private final int GALLERY_Y = 220;
    private final int GALLERY_X = 20;

    // define fade in speed
    private final int FADE_IN_SPED = 40;

    // constructer
    public scene_Gallery(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        m_btn_Character = new btn_Character[20];

        for(int i=0; i < m_btn_Character.length; ++i){
            m_btn_Character[i] = new  btn_Character(
                    gv,
                    this,
                    i ,
                    PlayerData.getInstance().getUnlockCharacter()[i] ,
                    GALLERY_X +  (i%4) * 150,
                    GALLERY_Y +  (i/4) * 150
            );
        }

        // common
        m_H1 = new GameSprite( gv, 0, H1_Y, R.drawable.h1_garaly );
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv,0,0);

        reset();
    }

    @Override
    public void update(){
        if( m_MenuGroup != null ) {
            m_MenuGroup.update();
            m_bMove = m_MenuGroup.getMove();
        }
        if( m_H1 != null ){
            m_H1.fade_in( FADE_IN_SPED );
        }
        if( m_UiGroup != null ){
            m_UiGroup.update();
        }
        if( m_btn_Character != null ){
            for(int i=0; i < m_btn_Character.length ; ++i) {
                if(m_btn_Character[i] != null) {
                    m_btn_Character[i].update();
                }
                if( m_btn_Character[i].isTouched() ){
                    m_bMove = true;
                    new scene_Detail( m_GameView, 20, m_btn_Character[i].getCharacter() );
                    break;
                }
            }
        }
    }

    @Override
    public void reset(){
        m_bMove = false;
        setTouchable( true );

        // set Alpha
        m_H1.setAlpha(0);

        Log.d("TEST", "scene_Gallery::Reset");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if (m_UiGroup != null){
            m_UiGroup.draw(c);
        }
        if(m_btn_Character != null){
            for(int i=0; i < m_btn_Character.length ; ++i) {
                if(m_btn_Character[i] != null)
                    m_btn_Character[i].draw(c);
            }
        }
        if(m_H1 != null){
            m_H1.draw(c);
        }
        if (m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
            if (m_btn_Character != null) {
                for (int i = 0; i < m_btn_Character.length; ++i) {
                    if (m_btn_Character != null) {
                        m_btn_Character[i].touch(event);
                    }
                }
            }
        }
    }
}
