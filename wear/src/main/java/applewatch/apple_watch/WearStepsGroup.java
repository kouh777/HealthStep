package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/06.
 */
public class WearStepsGroup extends ObjectGroup {

    private GameSprite m_icoWalk;
    private GameSprite m_StrStep;

    private final int ICO_X = 90;
    private final int ICO_Y = 110;
    private final int STR_X = 145;
    private final int STR_Y = 285;

    public WearStepsGroup( GameView gv ){
        super(gv);
        m_icoWalk = new GameSprite( m_GameView , ICO_X, ICO_Y , R.drawable.souhosuu_icon02 , 1.0, 1.0, 255 );
        m_StrStep = new GameSprite( m_GameView , STR_X, STR_Y , R.drawable.souhosuu_moji02 , 1.0, 1.0, 255 );
        addSprites( m_icoWalk );
        addSprites( m_StrStep );
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
