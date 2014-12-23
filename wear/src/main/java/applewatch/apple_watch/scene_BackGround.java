package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/10.
 */
public class scene_BackGround extends Task {

    private boolean m_bMove;
    private GameView m_GameView;
    private Vector<GameSprite> m_GameSprites; // manage all sprite. all sprites have their specific id

    // define sprites position
    private final int LOGO_Y = 5;
    public scene_BackGround(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        m_GameSprites = new Vector<GameSprite>();
        m_GameSprites.add( new GameSprite( gv, 0, 0 , R.drawable.wear_bg , 1.03, 1.03, 255 ) ) ;
        m_GameSprites.add( new GameSprite( gv, 0, 0 , R.drawable.name_waku , 1.03, 1.03, 255 ) );
        m_GameSprites.add( new GameSprite( gv, 0, LOGO_Y, R.drawable.game_name ,1.03,1.03, 255 ) );
        m_GameSprites.lastElement().alignCenterHorizontal();
    }

    @Override
    public void    reset(){
        m_bMove = false;
        setTouchable( true );

        Log.d("TEST", "New Back Ground Class");
    }

    @Override
    public void update(){
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_GameSprites != null ) {
            for (int i = 0; i < m_GameSprites.size(); i++) {
                if (m_GameSprites.get(i) != null) {
                    m_GameSprites.get(i).draw(c);
                }
            }
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){

    }
}
