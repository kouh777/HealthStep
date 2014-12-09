package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.List;
import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/16.
 */
public class scene_WearTitle extends Task{
    private boolean m_bMove;
    private GameView m_GameView;
    private Object m_Obj;   // for synchronizing thread

    private Vector<GameSprite> m_GameSprites; // manage all sprite. all sprites have their specific id

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

    // for animation
    private int m_iTimer;   // animation timer

    private boolean m_bSnatch;          // snatch flag
    private int[] m_iSpritePositions;   // for save sprites positions
    private final int SP_MAX_SIZE = 16; // Sprite array size

    private int m_iTouchY;              // touch action down y
    private boolean m_bIcoWalk;        // ico walk touch flag
    private boolean m_bIcoBeat;
    private boolean m_bIcoSett;

    private GameCamera m_GameCamera;    // samera

    // constract
    public scene_WearTitle(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_GameCamera = new GameCamera();

        m_GameSprites = new Vector<GameSprite>();
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_BACK, R.drawable.name_waku ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_LOGO, R.drawable.game_name ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_ICO_BEAT, ICO_X , ICO_BEAT_Y , R.drawable.sinpakusuu_icon ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_CHR_BEAT, 0 , CHR_BEAT_Y , R.drawable.sinpakusuu_moji ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_ICO_WALK, ICO_X , ICO_WALK_Y , R.drawable.souhosu_icon ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_CHR_WALK, 0 , CHR_WALK_Y , R.drawable.souhosu_moji ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_ICO_RANK, ICO_X , ICO_RANK_Y , R.drawable.ranking_icon ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_CHR_RANK, 0 , CHR_RANK_Y , R.drawable.ranking_moji ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_ICO_SETT, ICO_X , ICO_SETT_Y , R.drawable.settei_icon ) );
        m_GameSprites.add( new GameSprite( gv, SpriteId.SP_CHR_SETT, 0 , CHR_SETT_Y , R.drawable.settei_moji ) );

        reset();
    }

    @Override
    public void    reset(){
        m_iTimer = 0;
        m_bMove = false;
        m_bSnatch = false;
        m_bIcoWalk = false;
        m_bIcoBeat = false;
        m_bIcoSett = false;

        m_iTouchY = 0;
        m_Obj = new Object();

        m_iSpritePositions = new int[SP_MAX_SIZE];

        // set sprites
        if( m_GameSprites != null ) {
            for (int i = 0; i < m_GameSprites.size(); i++) {
                GameSprite gs = m_GameSprites.get(i);
                switch (m_GameSprites.get(i).getId()) {
                    case SP_LOGO:
                        gs.alignCenterHorizontal();
                        gs.setY( gs.getY() + LOGO_Y );
                        break;

                    case SP_CHR_BEAT:
                        gs.alignCenterHorizontal();
                        gs.setX( gs.getX() + CHR_X );
                        break;

                    case SP_CHR_WALK:
                        gs.alignCenterHorizontal();
                        gs.setX( gs.getX() + CHR_X );
                        break;

                    case SP_CHR_RANK:
                        gs.alignCenterHorizontal();
                        gs.setX( gs.getX() + CHR_X );
                        break;

                    case SP_CHR_SETT:
                        gs.alignCenterHorizontal();
                        gs.setX( gs.getX() + CHR_X );
                        break;
                }

            }
        }
        setTouchable( true );
        Log.d("TEST", "New Title Class");
    }

    @Override
    public void update(){
        m_iTimer++;
        if( m_GameCamera != null )
            m_GameCamera.update();

        // scene end animation and new next scene
        if( m_bIcoBeat ){
            if(!m_GameCamera.moveToCamera(-30, 15)) {
                m_GameCamera.setCamera(m_GameSprites, m_iSpritePositions);
            } else {
                m_bMove = true;
                new scene_HeartRate( m_GameView , 20 );
            }
        }

        // scene end animation and new next scene
        if( m_bIcoWalk ){
            if(!m_GameCamera.moveToCamera(-110, 15)) {
                m_GameCamera.setCamera(m_GameSprites, m_iSpritePositions);
            } else {
                m_bMove = true;
                new scene_WearWalk( m_GameView , 20 );
            }
        }

        // scene end animation and new next scene
        if( m_bIcoSett ){
            if(!m_GameCamera.moveToCamera(-270, 15)) {
                m_GameCamera.setCamera(m_GameSprites, m_iSpritePositions);
            } else {
                m_bMove = true;
                new scene_Setting( m_GameView , 20 );
            }
        }
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
    // touch
    public void    touch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch( event.getAction() ){
            case MotionEvent.ACTION_DOWN :
                m_bSnatch = true;
                saveSpritesPos();
                m_iTouchY = y;                      // action down y
                Log.d("getAction()", "ACTION_DOWN");
                break;

            case  MotionEvent.ACTION_MOVE :
                if( m_bSnatch ) {
                    int distance = y - m_iTouchY;               // swipe distance
                    m_GameCamera.setY(distance);      // set camera position
                    m_GameCamera.setCamera( m_GameSprites , m_iSpritePositions );
                }
                Log.d("getAction()", "ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP :
                Log.d("getAction()", "ACTION_UP");
                if( m_bSnatch ) {
                    m_bSnatch = false;
                }
                break;

            case MotionEvent.ACTION_CANCEL :
                Log.d("getAction()", "ACTION_CANCEL");
                break;
        }

        // sprites touch event
        if( m_GameSprites != null ) {
            for (int i = 0; i < m_GameSprites.size(); i++) {
                if (m_GameSprites.get(i) != null) {
                    m_GameSprites.get(i).touch(event);

                    // if touch walk
                    if( m_GameSprites.get(i).getId() == SpriteId.SP_ICO_WALK ){
                        if( m_GameSprites.get(i).getTouch() ){
                            m_bIcoWalk = true;
                            Log.d("ico_walk", "get touch action");
                            break;
                        }
                    }

                    // if touch walk
                    if( m_GameSprites.get(i).getId() == SpriteId.SP_ICO_BEAT ){
                        if( m_GameSprites.get(i).getTouch() ){
                            m_bIcoBeat = true;
                            Log.d("ico_beat", "get touch action");
                            break;
                        }
                    }

                    // if touch walk
                    if( m_GameSprites.get(i).getId() == SpriteId.SP_ICO_SETT ){
                        if( m_GameSprites.get(i).getTouch() ){
                            m_bIcoSett = true;
                            Log.d("ico_sett", "get touch action");
                            break;
                        }
                    }

                }
            }
        }
    }

    // save sprites positions
    private void saveSpritesPos(){
//        synchronized (m_Obj) {
        if( m_GameSprites != null ) {
            for (int i = 0; i < m_GameSprites.size(); i++) {
                m_iSpritePositions[i] = m_GameSprites.elementAt(i).getY();
            }
        }
//        }
    }
}
