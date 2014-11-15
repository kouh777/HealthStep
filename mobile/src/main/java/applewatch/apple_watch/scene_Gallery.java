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

    private Paint m_Paint;
    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private btn_Character[] m_btn_Character;
    private UiGroup m_UiGroup;

    // sprites
    private GameSprite m_H1;

    // define position
    private final int H1_Y = 140;

    // constract
    public scene_Gallery(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();
        int posX = 0;
        int posY = 0;

        final int paddingY = 150;
        m_btn_Character = new btn_Character[20];

        for(int i=0; i < m_btn_Character.length; ++i){
            m_btn_Character[i] = new  btn_Character(gv, i , PlayerData.getInstance().getUnlockCharacter()[i] ,posX + (i%4) * 150,  paddingY + posY + (i/4) * 150 );
        }

        // common
        m_H1 = new GameSprite( gv, 0, H1_Y, R.drawable.h1_garaly );
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv,0,0);

        reset();
    }

    @Override
    public void update(){

    }

    @Override
    public void reset(){
        m_bMove = false;
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
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        if (m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
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
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if(m_MenuGroup != null){
            m_MenuGroup.touch(event);
            m_bMove = m_MenuGroup.move();
        }
        if(m_btn_Character != null){
            for(int i=0; i < m_btn_Character.length ; ++i) {
                if(m_btn_Character != null){
                    m_btn_Character[i].touch(event);
                }
            }
        }
    }
}
