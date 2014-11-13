package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class scene_Ranking extends Task {

    private Paint m_Paint;
    // image resources
    private BitmapDrawable m_RankingNo1;
    private BitmapDrawable m_RankingNo2;
    private BitmapDrawable m_RankingNo3;
    private BitmapDrawable m_RankingOthers;

    private boolean m_bMove;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    // constract
    public scene_Ranking(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;

        // ranking resources
        m_RankingNo1 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no1);
        m_RankingNo2 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no2);
        m_RankingNo3 = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_no3);
        m_RankingOthers = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.ranking_others);

        int w = gv.getGameWidth();
        int h = gv.getGameHeight();

        m_MenuGroup = new MenuGroup(gv);
        // ui
        m_UiGroup = new UiGroup(gv, 0, 0);

        reset();
    }

    @Override
    public void update(){

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
    // draw
    public void    draw(Canvas c){
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
        if( m_UiGroup != null){
            m_UiGroup.draw(c);
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
        if(m_MenuGroup != null){
            m_MenuGroup.touch(event);
            m_bMove = m_MenuGroup.move();
        }
    }
}