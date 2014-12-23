package applewatch.apple_watch;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/09.
 */
public class WearSettingGroup extends ObjectGroup {
    private GameSprite m_icoSet;
    private GameSprite m_StrSet;

    private final int ICO_X = 90;
    private final int ICO_Y = 110;
    private final int STR_X = 145;
    private final int STR_Y = 285;

    public WearSettingGroup( GameView gv ){
        super(gv);
        m_icoSet = new GameSprite( m_GameView , ICO_X, ICO_Y , R.drawable.sttei_icon02 , 1.0, 1.0, 255 );
        m_StrSet = new GameSprite( m_GameView , STR_X, STR_Y , R.drawable.settei_moji02 , 1.0, 1.0, 255 );
        addSprites( m_icoSet );
        addSprites( m_StrSet );
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
