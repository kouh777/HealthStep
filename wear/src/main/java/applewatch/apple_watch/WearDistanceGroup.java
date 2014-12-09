package applewatch.apple_watch;

import android.graphics.Canvas;

/**
 * Created by KOUHO on 2014/12/09.
 */
public class WearDistanceGroup extends ObjectGroup {
    private GameSprite m_icoDis;
    private GameSprite m_StrDis;

    private final int ICO_X = 90;
    private final int ICO_Y = 110;
    private final int STR_X = 145;
    private final int STR_Y = 285;

    public WearDistanceGroup( GameView gv ){
        super(gv);
        m_icoDis = new GameSprite( m_GameView , ICO_X, ICO_Y , R.drawable.distance_icon , 1.0, 1.0, 255 );
        m_StrDis = new GameSprite( m_GameView , STR_X, STR_Y , R.drawable.distance_moji , 1.0, 1.0, 255 );
        addSprites( m_icoDis );
        addSprites( m_StrDis );
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
}
