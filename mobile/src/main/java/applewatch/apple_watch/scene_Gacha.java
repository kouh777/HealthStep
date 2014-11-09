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
public class scene_Gacha implements GamePart {

    private Paint m_Paint;
    // image resources
    private BitmapDrawable	m_MenuBack;
    private BitmapDrawable m_ButtonBase;

    private boolean m_bMove;

    private int m_iNextScene;
    private GameView m_GameView;

    private btn_Ranking m_btn_Ranking;
    private btn_Gallery m_btn_Gallery;
    private btn_Gacha   m_btn_Gacha;
    private btn_MyPage  m_btn_MyPage;

    // constract
    public scene_Gacha(GameView gv){

        m_Paint = null;
        m_iNextScene = 0;
        m_GameView = gv;

        // background resource
        m_MenuBack = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.bg);
        // button background resource
        m_ButtonBase = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.button_base);

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();

        m_btn_MyPage = new btn_MyPage(gv, 0, h-(w>>4)*3);
        m_btn_Gallery = new btn_Gallery(gv, w>>2, h-(w>>4)*3);
        m_btn_Gacha = new btn_Gacha(gv, (w>>2)*2,  h-(w>>4)*3);
        m_btn_Ranking = new btn_Ranking(gv, (w>>2)*3, h-(w>>4)*3);

        reset();
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
    // value of next scene
    public int nextSceneID(){
        return m_iNextScene;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        if (m_MenuBack != null){
            m_MenuBack.setBounds(0,0,w,h);
            m_MenuBack.draw(c);
        }
        if( m_ButtonBase != null){
            m_ButtonBase.setBounds(0,h-(w>>4)*3,w,h);
            m_ButtonBase.draw(c);
        }
        if(m_btn_MyPage != null){
            m_btn_MyPage.draw(c);
        }
        if(m_btn_Ranking != null){
            m_btn_Ranking.draw(c);
        }
        if(m_btn_Gallery != null){
            m_btn_Gallery.draw(c);
        }
        if(m_btn_Gacha != null){
            m_btn_Gacha.draw(c);
        }
        // draw get character

    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if(m_btn_MyPage != null){
            m_btn_MyPage.touch(event);
            if(m_btn_MyPage.isTouched()){
                m_iNextScene = m_btn_MyPage.nextSceneID();
                m_bMove = true;
            }
        }
        if(m_btn_Ranking != null){
            m_btn_Ranking.touch(event);
            if(m_btn_Ranking.isTouched()){
                m_iNextScene = m_btn_Ranking.nextSceneID();
                m_bMove = true;
            }
        }
        if(m_btn_Gallery != null){
            m_btn_Gallery.touch(event);
            if(m_btn_Gallery.isTouched()){
                m_iNextScene = m_btn_Gallery.nextSceneID();
                m_bMove = true;
            }
        }
        if(m_btn_Gacha != null){
            m_btn_Gacha.touch(event);
            if(m_btn_Gacha.isTouched()){
                m_iNextScene = m_btn_Gacha.nextSceneID();
                m_bMove = true;
            }
        }
    }
}