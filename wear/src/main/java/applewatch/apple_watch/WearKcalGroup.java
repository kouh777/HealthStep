package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/09.
 */
public class WearKcalGroup extends ObjectGroup{
    private GameSprite m_icoCal;
    private GameSprite m_StrCal;

    private final int ICO_X = 90;
    private final int ICO_Y = 110;
    private final int STR_X = 90;
    private final int STR_Y = 285;

    public WearKcalGroup( GameView gv ){
        super(gv);
        m_icoCal = new GameSprite( m_GameView , ICO_X, ICO_Y , R.drawable.kcal_icon , 1.0, 1.0, 255 );
        m_StrCal = new GameSprite( m_GameView , STR_X, STR_Y , R.drawable.kcal_moji , 1.0, 1.0, 255 );
        addSprites( m_icoCal );
        addSprites( m_StrCal );
    }

    @Override
    public void reset(){
        super.reset();
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void draw(Canvas c){
        super.draw(c);
    }

    @Override
    public void touch(MotionEvent event){
        super.touch(event);
    }
}
