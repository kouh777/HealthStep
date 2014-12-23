package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/06.
 */
public class scene_WearTotal extends Task {

    private boolean m_bMove;
    private GameView m_GameView;
    private Object m_Obj;   // for synchronizing thread
    private Map< Object , ObjectGroup > m_ObjGroupMap;

   private Vector<GameSprite> m_GameSprites; // manage all sprite. all sprites have their specific id

    // define sprites position
    private final int LOGO_Y = 5;

    // for animation
    private int m_iTimer;   // animation timer

    private boolean m_bSnatch;          // snatch flag
    private int[] m_iSpritePositions;   // for save sprites positions
    private final int SP_MAX_SIZE = 16; // Sprite array size

    private GROUP_ID m_GroupId;
    private GameCamera m_GameCamera;    // samera

    // relation to touch
    private int m_iTouchX;
    private int m_iTouchY;
    private boolean m_bLeftSwipe;
    private boolean m_bRightSwipe;
    // define Check Swipe
    private final int SWIPE_LEN = 1500;

    // define relation to moving RankingGroup
    private final int SLIDE_PER_SPEED = 3;  //
    private int SLIDE_SPEED;                 // 240;
    private int SLIDE_LEFT_END_X;           // 720
    private int SLIDE_LEFT_INIT_X;          // 2160
    private int SLIDE_RIGHT_END_X;          // 1440
    private int SLIDE_RIGHT_INIT_X;         // -2160;

    // constract
    public scene_WearTotal(GameView gv, int prio){
        super(prio);
        m_ObjGroupMap = new HashMap<Object,ObjectGroup>();
        m_ObjGroupMap.put( GROUP_ID.STEP , new WearStepsGroup( gv ) );
        m_ObjGroupMap.put( GROUP_ID.DIST , new WearDistanceGroup( gv ) );
        m_ObjGroupMap.put( GROUP_ID.KCAL , new WearKcalGroup( gv ) );
        m_ObjGroupMap.put( GROUP_ID.SETT , new WearSettingGroup( gv ) );

        // set slide member
        SLIDE_LEFT_END_X = (int)( gv.getGameWidth() * 0.6 );
        SLIDE_LEFT_INIT_X = SLIDE_LEFT_END_X * 3;
        SLIDE_RIGHT_END_X = SLIDE_LEFT_END_X * 2;
        SLIDE_RIGHT_INIT_X = -SLIDE_LEFT_INIT_X;
        SLIDE_SPEED = SLIDE_LEFT_END_X / SLIDE_PER_SPEED;

        // Object Group's Initialized Positions
        m_ObjGroupMap.get(GROUP_ID.STEP).moveX( (int)( -SLIDE_LEFT_END_X ) );  //  gv.getGameWidth() * -0.6
        m_ObjGroupMap.get(GROUP_ID.DIST).moveX( (int)( 0 ) );                    //   gv.getGameWidth() * 0
        m_ObjGroupMap.get(GROUP_ID.KCAL).moveX( (int)( SLIDE_LEFT_END_X ) );     // gv.getGameWidth() * 0.6
        m_ObjGroupMap.get(GROUP_ID.SETT).moveX( (int)( SLIDE_RIGHT_END_X ) );     // gv.getGameWidth() * 1.2

        m_GameView = gv;
        m_GameCamera = new GameCamera();


        m_GameSprites = new Vector<GameSprite>();
        m_GameSprites.add( new GameSprite( gv, 0, 0 , R.drawable.wear_bg , 1.03, 1.03, 255 ) ) ;
        m_GameSprites.add( new GameSprite( gv, 0, 0 , R.drawable.name_waku , 1.03, 1.03, 255 ) );
        m_GameSprites.add( new GameSprite( gv, 0, LOGO_Y, R.drawable.game_name ,1.03,1.03, 255 ) );
        m_GameSprites.lastElement().alignCenterHorizontal();


        reset();
    }

    @Override
    public void    reset(){
        m_iTimer = 0;
        m_bMove = false;
        m_bSnatch = false;

        m_iTouchX = 0;
        m_iTouchY = 0;
        m_bLeftSwipe = false;
        m_bRightSwipe = false;
        m_Obj = new Object();
        m_GroupId = GROUP_ID.NONE;

        m_iSpritePositions = new int[SP_MAX_SIZE];
        setTouchable( true );

        Log.d("TEST", "New Wear Total Class");
    }

    @Override
    public void update(){
        m_iTimer++;
        if( m_GameCamera != null )
            m_GameCamera.update();

        // swipe end
        if( m_ObjGroupMap != null ){
            Iterator it = m_ObjGroupMap.keySet().iterator();
            synchronized (m_Obj) {
                while (it.hasNext()) {
                    Object o = it.next();
                    // swipe left end
                    if (m_bLeftSwipe && m_ObjGroupMap.get(o).getX() <= -SLIDE_LEFT_END_X * 2) {
                        m_ObjGroupMap.get(o).moveX(SLIDE_LEFT_END_X * 4);
                        m_bLeftSwipe = false;
                    }
                    // swipe right end
                    if (m_bRightSwipe && m_ObjGroupMap.get(o).getX() >= SLIDE_RIGHT_END_X) {
                        m_ObjGroupMap.get(o).moveX(SLIDE_LEFT_END_X * -4);
                        m_bRightSwipe = false;
                    }
                }
            }
        }

        // swipe
        if( m_ObjGroupMap != null ){
            Iterator it = m_ObjGroupMap.keySet().iterator();
            synchronized (m_Obj) {
                while (it.hasNext()) {
                    Object o = it.next();
                    m_ObjGroupMap.get(o).update();
                    if (m_bLeftSwipe) { // left swipe
                        m_ObjGroupMap.get(o).moveX(-SLIDE_SPEED);
                    }
                    if (m_bRightSwipe) { // right swipe
                        m_ObjGroupMap.get(o).moveX(SLIDE_SPEED);
                    }
                }
            }
        }
        switch( m_GroupId ){
            case STEP:
                m_bMove  = true;
                new scene_WearWalk(m_GameView,20);
                Log.d("new scene","scene_WearWalk");

              break;
            case DIST:
                new scene_WearWalk(m_GameView,20);
                Log.d("new scene","scene_WearWalk");
                m_bMove  = true;
                break;
            case KCAL:
                new scene_WearWalk(m_GameView,20);
                Log.d("new scene","scene_WearWalk");
                m_bMove  = true;
                break;
            case SETT:
                new scene_WearWalk(m_GameView,20);
                Log.d("new scene","scene_WearWalk");
                m_bMove  = true;
                break;
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
        if( m_ObjGroupMap != null ) {
            Iterator it = m_ObjGroupMap.keySet().iterator();
            while( it.hasNext() ){
                Object o = it.next();
                m_ObjGroupMap.get(o).draw(c);
            }
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch ( event.getAction() ){

            case MotionEvent.ACTION_DOWN:
                m_iTouchX = x;
                m_iTouchY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                int v_x = x - m_iTouchX;
                int v_y = y - m_iTouchY;
                int distance = v_x * v_x + v_y * v_y;
                if( distance > SWIPE_LEN && v_x < 0){
                    Log.d("scene_WearTotal","left swipe");
                    m_bLeftSwipe = true;
                    m_bRightSwipe = false;
                }
                if( distance > SWIPE_LEN && v_x > 0 ){
                    Log.d("scene_WearTotal","right swipe");
                    m_bLeftSwipe = false;
                    m_bRightSwipe = true;
                }
                Log.d("scene_WearTotal",String.valueOf(distance) );
                break;

            case MotionEvent.ACTION_CANCEL:
                break;

        }
        if( m_ObjGroupMap != null ) {
            Iterator it = m_ObjGroupMap.keySet().iterator();
            while( it.hasNext() ){
                Object o = it.next();
                m_ObjGroupMap.get(o).touch(event);
                if( m_ObjGroupMap.get(o).getTouch() ) {
                    if ( o == GROUP_ID.STEP || o == GROUP_ID.DIST
                            || o == GROUP_ID.KCAL || o == GROUP_ID.SETT){
                        m_GroupId = (GROUP_ID) o;
                        return;
                    }
                }
            }
        }
    }

    /*
    // save sprites positions
    private void saveSpritesPos(){
        if( m_GameSprites != null ) {
            for (int i = 0; i < m_GameSprites.size(); i++) {
                m_iSpritePositions[i] = m_GameSprites.elementAt(i).getY();
            }
        }
    }
    */
}
