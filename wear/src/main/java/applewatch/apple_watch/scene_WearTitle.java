package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/16.
 */
public class scene_WearTitle extends Task{
    private boolean m_bMove;
    private GameView m_GameView;

    // sprites
    private GameSprite m_TitleBack;
    private GameSprite m_TitleLogo;
    private GameSprite m_icoBeat;
    private GameSprite m_chrBeat;
    private GameSprite m_icoWalk;
    private GameSprite m_chrWalk;
    private GameSprite m_icoRanking;
    private GameSprite m_chrRanking;
    private GameSprite m_icoSetting;
    private GameSprite m_chrSetting;

    // define sprites position
    private final int BACK_Y = 0;
    private final int LOGO_Y = 5;
    private final int ICO_X = 30;
    private final int CHR_X = 50;
    private final int ICO_BEAT_Y = 70;
    private final int CHR_BEAT_Y = 80;
    private final int ICO_WALK_Y = 150;
    private final int CHR_WALK_Y = 160;
    private final int ICO_RANK_Y = 230;
    private final int CHR_RANK_Y = 240;
    private final int ICO_SETT_Y = 310;
    private final int CHR_SETT_Y = 320;

    // define Timer
    private final int BLINK_SPAN = 10;

    // for animation
    private int m_iTimer;

    // constract
    public scene_WearTitle(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        // sprites
        m_TitleLogo = new GameSprite( gv,R.drawable.game_name );
        m_TitleBack = new GameSprite( gv,R.drawable.name_waku );
        m_icoBeat = new GameSprite( gv, ICO_X , ICO_BEAT_Y , R.drawable.sinpakusuu_icon );
        m_chrBeat = new GameSprite( gv, 0 , CHR_BEAT_Y , R.drawable.sinpakusuu_moji );
        m_icoWalk = new GameSprite( gv, ICO_X , ICO_WALK_Y , R.drawable.souhosu_icon );
        m_chrWalk = new GameSprite( gv, 0 , CHR_WALK_Y , R.drawable.souhosu_moji );
        m_icoRanking = new GameSprite( gv, ICO_X , ICO_RANK_Y , R.drawable.ranking_icon );
        m_chrRanking = new GameSprite( gv, 0 , CHR_RANK_Y , R.drawable.ranking_moji );
        m_icoSetting = new GameSprite( gv, ICO_X , ICO_SETT_Y , R.drawable.settei_icon );
        m_chrSetting = new GameSprite( gv, 0 , CHR_SETT_Y , R.drawable.settei_moji );

        reset();
    }

    @Override
    public void    reset(){
        m_iTimer = 0;
        m_bMove = false;
        // set position
        if( m_TitleLogo != null){
            m_TitleLogo.alignCenterHorizontal();
            m_TitleLogo.setY( m_TitleLogo.getY() + LOGO_Y );
        }
        if( m_chrBeat != null ){
            m_chrBeat.alignCenterHorizontal();
            m_chrBeat.setX( m_chrBeat.getX() + CHR_X );
        }
        if( m_chrWalk != null ){
            m_chrWalk.alignCenterHorizontal();
            m_chrWalk.setX( m_chrWalk.getX() + CHR_X );
        }
        if( m_chrRanking != null ){
            m_chrRanking.alignCenterHorizontal();
            m_chrRanking.setX( m_chrRanking.getX() + CHR_X );
        }
        if( m_chrSetting != null ){
            m_chrSetting.alignCenterHorizontal();
            m_chrSetting.setX( m_chrSetting.getX() + CHR_X );
        }

        setTouchable( true );
        Log.d("TEST", "New Title Class");
    }

    @Override
    public void update(){
        m_iTimer++;
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if(m_icoBeat != null){
            m_icoBeat.draw(c);
        }
        if(m_chrBeat != null){
            m_chrBeat.draw(c);
        }
        if(m_icoWalk != null){
            m_icoWalk.draw(c);
        }
        if(m_chrWalk != null){
            m_chrWalk.draw(c);
        }
        if(m_icoRanking != null){
            m_icoRanking.draw(c);
        }
        if(m_chrRanking != null){
            m_chrRanking.draw(c);
        }
        if(m_icoSetting != null){
            m_icoSetting.draw(c);
        }
        if(m_chrSetting != null){
            m_chrSetting.draw(c);
        }
        if(m_TitleBack != null){
            m_TitleBack.draw(c);
        }
        if(m_TitleLogo != null){
            m_TitleLogo.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            // if player don't touch
            if (event.getAction() != MotionEvent.ACTION_DOWN) {
                // break
                return;
            } else {
                Log.d("TEST", "get Touch action");
//                m_bMove = true;
            }
        }
    }

}
