package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/13.
 */

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.util.Log;

public class scene_Title implements GamePart{
    private Paint m_Paint;
    // image resources
    private BitmapDrawable	m_TitleBack;
    private BitmapDrawable m_TitleLogo;
    // image status
    private int	m_iTitleLogoWidth;
    private int 	m_iTitleLogoHeight;

    private boolean m_bMove;

    private int m_iNextScene;
    private GameView m_GameView;

    // constract
    public scene_Title(GameView gv){
        m_Paint = null;
        m_GameView = gv;
        m_iNextScene = gv.m_SceneMenu;

        // background resource
        m_TitleBack = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.bg);
        m_TitleLogo = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.m_title_logo);

        //　タイトルロゴの描画サイズの決定
        m_iTitleLogoWidth  = m_TitleLogo.getIntrinsicWidth();
        m_iTitleLogoHeight = m_TitleLogo.getIntrinsicHeight();

        //　画像の縮小率を適用
        m_iTitleLogoWidth	 *= gv.getGamePerWidth();
        m_iTitleLogoHeight *= gv.getGamePerHeight();
        reset();
    }

    @Override
    public void    reset(){
        m_bMove = false;
        Log.d("TEST", "New Title Class");
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
        if (m_TitleBack != null){
            m_TitleBack.setBounds(0,0,w,h);
            m_TitleBack.draw(c);
        }
        if (m_TitleLogo != null){
            m_TitleLogo.setBounds((w>>1)-(m_iTitleLogoWidth>>1),
                    (h>>1)-(m_iTitleLogoHeight>>1),(w>>1)+(m_iTitleLogoWidth>>1),
                    (h>>1)+(m_iTitleLogoHeight>>1));
            m_TitleLogo.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        // if player don't touch
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            // break
            return;
        }else {
            Log.d("TEST", "get Touch action");
            m_iNextScene = m_GameView.m_SceneMenu;
            m_bMove = true;
        }
    }

    // getter
    public GameView getGameView(){
        return m_GameView;
    }
}