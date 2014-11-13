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
    private Paint m_Paint;
    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;

    // constract
    public scene_Gacha(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;
        m_MenuGroup = new MenuGroup(gv);
        reset();
    }

    @Override
    public void update(){
    }

    @Override
    public void reset(){
        m_bMove = false;
        boolean b_Flg = false;

        // create random number(from 0 to character data size)
        Random r = new Random();
        while(b_Flg == false) {
            int rnd = r.nextInt(20);

            // if gacha complete, escape from loop
            if (PlayerData.getInstance().CharacterComplete() == true){
                b_Flg = true;
            }

            if (PlayerData.getInstance().getUnlockCharacter()[rnd] == false) {
                PlayerData.getInstance().setUnlockCharacter(rnd, true);
                b_Flg = true;
            }
        }
        Log.d("TEST", "scene_Gacha::Reset");
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
        if (m_MenuGroup != null) {
            m_MenuGroup.draw(c);
        }
        // draw get character
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( m_MenuGroup != null)
            m_MenuGroup.touch(event);
            m_bMove = m_MenuGroup.move();
    }
}