package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/05.
 */
public class scene_HeartRate extends Task{

    // this class doesn't have many sprite so don't use Vector<GameSprite>
    private GameSprite m_icoBeat;
    private GameSprite m_chrBeat;

    private GameText m_HeartRate;

    private final int ICO_X = 30;
    private final int CHR_X = 50;
    private final int ICO_BEAT_Y = 10;
    private final int CHR_BEAT_Y = 20;

    private int m_iHeartRate;

    public scene_HeartRate( GameView gv, int prio ) {
        super(prio);
        m_icoBeat = new GameSprite(gv, SpriteId.SP_ICO_BEAT, ICO_X, ICO_BEAT_Y, R.drawable.sinpakusuu_icon);
        m_chrBeat = new GameSprite(gv, SpriteId.SP_ICO_BEAT, 0, CHR_BEAT_Y, R.drawable.sinpakusuu_moji);
        m_iHeartRate = 0;

        m_HeartRate = new GameText(gv, String.valueOf(m_iHeartRate), 100, 100);
        reset();
    }

    @Override
    public void reset(){
        // set sprite
        if( m_chrBeat != null ){
            m_chrBeat.alignCenterHorizontal();
            m_chrBeat.setX( m_chrBeat.getX() + CHR_X );
        }
        Log.d("scene_HeartRate", "reset");
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Canvas c){
        if( m_icoBeat != null ){
            m_icoBeat.draw(c);
        }
        if( m_chrBeat != null ){
            m_chrBeat.draw(c);
        }
        if( m_HeartRate != null ){
            m_HeartRate.draw(c);
        }
    }
    @Override
    public boolean move(){
        return false;
    }

    @Override
    public void touch(MotionEvent event){

    }
}
