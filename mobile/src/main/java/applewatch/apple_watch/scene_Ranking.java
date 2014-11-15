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

    private boolean m_bMove;

    private GameSprite m_RankingNo1;
    private GameSprite m_RankingNo2;
    private GameSprite m_RankingNo3;
    private GameSprite m_RankingOthers;

    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    private GameSprite m_H1;

    // define position and scale
    private final int H1_Y = 140;

    private final int GOLD_Y = 230;
    private final int SILVER_Y = 350;
    private final int BROND_Y = 470;
    private final int OTHERS_Y = 580;
    private final double BEST_SY = 0.52;
    private final double OHTERS_SY = 0.52;

    // constract
    public scene_Ranking(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        // sprites
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_ranking);
        m_RankingNo1 = new GameSprite( gv, R.drawable.ranking_no1 );
        m_RankingNo2 = new GameSprite( gv, R.drawable.ranking_no2 );
        m_RankingNo3 = new GameSprite( gv, R.drawable.ranking_no3 );
        m_RankingOthers = new GameSprite( gv, R.drawable.ranking_others );

        // common
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_ranking);
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv, 0, 0);

        reset();
    }

    @Override
    public void update(){
    }

    @Override
    public void reset(){
        // set Sprites
        m_RankingNo1.alignCenterHorizontal();
        m_RankingNo1.setY(GOLD_Y);
        m_RankingNo1.setScaleY(BEST_SY);

        m_RankingNo2.alignCenterHorizontal();
        m_RankingNo2.setY(SILVER_Y);
        m_RankingNo2.setScaleY(BEST_SY);

        m_RankingNo3.alignCenterHorizontal();
        m_RankingNo3.setY(BROND_Y);
        m_RankingNo3.setScaleY(BEST_SY);

        m_RankingOthers.alignCenterHorizontal();
        m_RankingOthers.setY(OTHERS_Y);
        m_RankingOthers.setScaleY(OHTERS_SY);

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
        if( m_RankingNo1 != null ){
            m_RankingNo1.draw(c);
        }
        if(m_RankingNo2 != null){
            m_RankingNo2.draw(c);
        }
        if(m_RankingNo3 != null){
            m_RankingNo3.draw(c);
        }
        if(m_RankingOthers != null){
            m_RankingOthers.draw(c);
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
    }
}