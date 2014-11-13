package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class scene_Ranking implements GamePart {

    private Paint m_Paint;
    // image resources
    private BitmapDrawable	m_MenuBack;
    private BitmapDrawable m_ButtonBase;

    private BitmapDrawable m_RankingNo1;
    private BitmapDrawable m_RankingNo2;
    private BitmapDrawable m_RankingNo3;
    private BitmapDrawable m_RankingOthers;

    private boolean m_bMove;

    private int m_iNextScene;
    private GameView m_GameView;

    private btn_Ranking m_btn_Ranking;
    private btn_Gallery m_btn_Gallery;
    private btn_Gacha   m_btn_Gacha;
    private btn_MyPage  m_btn_MyPage;

    private UiGroup m_UiGroup;

    // constract
    public scene_Ranking(GameView gv){
        m_Paint = null;
        m_iNextScene = 0;
        m_GameView = gv;

        // background resource
        m_MenuBack = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.bg);
        // button background resource
        m_ButtonBase = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.button_base);
        // ranking resources
        m_RankingNo1 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no1);
        m_RankingNo2 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no2);
        m_RankingNo3 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no3);
        m_RankingOthers = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_others);

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();

        m_btn_MyPage = new btn_MyPage(gv, 0, h-(w>>4)*3);
        m_btn_Gallery = new btn_Gallery(gv, w>>2, h-(w>>4)*3);
        m_btn_Gacha = new btn_Gacha(gv, (w>>2)*2,  h-(w>>4)*3);
        m_btn_Ranking = new btn_Ranking(gv, (w>>2)*3, h-(w>>4)*3);

        // ui
        m_UiGroup = new UiGroup(gv, 0, 0);

        reset();
    }

    @Override
    public void reset(){
        m_bMove = false;
        Log.d("TEST", "scene_Ranking::Reset");
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
        if( m_UiGroup != null){
            m_UiGroup.draw(c);
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
        // for test
        if(m_RankingNo1 != null){
            int ww = m_RankingNo1.getIntrinsicWidth();
            int hh = m_RankingNo1.getIntrinsicHeight();
            int x = (w-ww) >> 1;
            int y = 130;
            double sy = 0.56;
            m_RankingNo1.setBounds(x,y,x+ww,y+(int)(hh * sy));
            m_RankingNo1.draw(c);
        }
        if(m_RankingNo2 != null){
            int ww = m_RankingNo2.getIntrinsicWidth();
            int hh = m_RankingNo2.getIntrinsicHeight();
            int x = (w-ww) >> 1; // for centering
            int y = 250;
            double sy = 0.56;
            m_RankingNo2.setBounds(x,y,x+ww,y+(int)(hh * sy));
            m_RankingNo2.draw(c);
        }
        if(m_RankingNo3 != null){
            int ww = m_RankingNo3.getIntrinsicWidth();
            int hh = m_RankingNo3.getIntrinsicHeight();
            int x = (w-ww) >> 1; // for centering
            int y = 370;
            double sy = 0.56;
            m_RankingNo3.setBounds(x,y,x+ww,y+(int)(hh * sy));
            m_RankingNo3.draw(c);
        }
        if(m_RankingOthers != null){
            int ww = m_RankingOthers.getIntrinsicWidth();
            int hh = m_RankingOthers.getIntrinsicHeight();
            int x = (w-ww) >> 1; // for centering
            int y = 480;
            double sy = 0.52;
            m_RankingOthers.setBounds(x,y,x+ww,y+(int)(hh * sy));
            m_RankingOthers.draw(c);
        }
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
                Log.d("TEST", "scene_Ranking::touch ranking");
                m_iNextScene = m_btn_Ranking.nextSceneID();
                m_bMove = true;
            }
        }
        if(m_btn_Gallery != null){
            m_btn_Gallery.touch(event);
            if(m_btn_Gallery.isTouched()){
                Log.d("TEST", "scene_Ranking::touch gallery");
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
