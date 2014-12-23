package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/05.
 */
public class scene_Setting extends Task {
    // this class doesn't have many sprite so don't use Vector<GameSprite>
    private GameSprite m_icoSett;
    private GameSprite m_chrSett;

    private final int ICO_X = 30;
    private final int CHR_X = 50;
    private final int ICO_SETT_Y = 10;
    private final int CHR_SETT_Y = 20;

    private Object m_Obj;

    public scene_Setting( GameView gv, int prio ) {
        super(prio);
//        m_icoSett = new GameSprite(gv, SpriteId.SP_ICO_BEAT, ICO_X, ICO_SETT_Y, R.drawable.sinpakusuu_icon);
 //       m_chrSett = new GameSprite(gv, SpriteId.SP_ICO_BEAT, 0, CHR_SETT_Y, R.drawable.sinpakusuu_moji);
        m_Obj = new Object();

        reset();
    }

    @Override
    public void reset(){
        // set sprite
        if( m_chrSett != null ){
            m_chrSett.alignCenterHorizontal();
            m_chrSett.setX( m_chrSett.getX() + CHR_X );
        }
        Log.d("scene_HeartRate", "reset");
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Canvas c){
        if( m_icoSett != null ){
            m_icoSett.draw(c);
        }
        if( m_chrSett != null ){
            m_chrSett.draw(c);
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
