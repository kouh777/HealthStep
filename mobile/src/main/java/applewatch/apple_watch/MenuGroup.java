package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/13.
 */
public class MenuGroup {
    // image resources
    private BitmapDrawable m_ButtonBase;

    private btn_Ranking m_btn_Ranking;
    private btn_Gallery m_btn_Gallery;
    private btn_Gacha   m_btn_Gacha;
    private btn_MyPage  m_btn_MyPage;

    private GameView m_GameView;
    private boolean m_bMove;

    // constract
    public MenuGroup(GameView gv){

        m_GameView = gv;

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

    public void update(){
        if(m_btn_MyPage != null && m_btn_MyPage.isTouched()){
            m_bMove = true;
            new scene_Menu(m_GameView, 21);
        }
        if(m_btn_Ranking != null && m_btn_Ranking.isTouched()){
            m_bMove = true;
            new scene_Ranking(m_GameView, 22);
            Log.d("MenuGroup", "Touch Ranking");
        }
        if(m_btn_Gallery != null && m_btn_Gallery.isTouched()) {
            m_bMove = true;
            new scene_Gallery(m_GameView, 23);
        }
        if(m_btn_Gacha != null && m_btn_Gacha.isTouched()){
            m_bMove = true;
            new scene_Gacha(m_GameView, 20);
            Log.d("MenuGroup", "Touch Gacha");
        }
    }

    public void    reset(){
        Log.d("MenuGroup", "Reset");
    }

    // draw
    public void    draw(Canvas c){
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();

        if (m_ButtonBase != null) {
            m_ButtonBase.setBounds(0, h - (w >> 4) * 3, w, h);
            m_ButtonBase.draw(c);
        }
        if (m_btn_MyPage != null) {
            m_btn_MyPage.draw(c);
        }
        if (m_btn_Ranking != null) {
            m_btn_Ranking.draw(c);
        }
        if (m_btn_Gallery != null) {
            m_btn_Gallery.draw(c);
        }
        if (m_btn_Gacha != null) {
            m_btn_Gacha.draw(c);
        }
    }

    // touch event
    public void    touch(MotionEvent event){
        if( m_btn_MyPage != null ){
            m_btn_MyPage.touch(event);
        }
        if( m_btn_Ranking != null ){
            m_btn_Ranking.touch(event);
        }
        if( m_btn_Gallery != null ){
            m_btn_Gallery.touch(event);
        }
        if( m_btn_Gacha != null ){
            m_btn_Gacha.touch(event);
        }
    }
    // getter
    public boolean getMove(){ return m_bMove; }
}
