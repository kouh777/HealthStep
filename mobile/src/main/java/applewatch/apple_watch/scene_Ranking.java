package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class scene_Ranking extends Task {

    private Object m_Obj;
    private boolean m_bMove;
    private RankingGroup m_RannkingByPrefecture;
    private RankingGroup m_RankingWithInPrefecture;
    private RankingGroup m_RankingWholeCountry;
    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    // define fade in speed
    private final int FADE_IN_SPEED = 40;

    // relation to touch
    private int m_iTouchX;
    private int m_iTouchY;
    private boolean m_bLeftSwipe;
    private boolean m_bRightSwipe;
    // define Check Swipe
    private final int SWIPE_LEN = 4500;

    // define relation to moving RankingGroup
    private final int SLIDE_PER_SPEED = 3;  //
    private int SLIDE_SPEED;// 240;
    private int SLIDE_LEFT_END_X; // 720
    private int SLIDE_LEFT_INIT_X; //  2160
    private int SLIDE_RIGHT_END_X; // 1440
    private int SLIDE_RIGHT_INIT_X; //-2160;

    // constract
    public scene_Ranking(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_RannkingByPrefecture = new RankingGroup( gv , RankingGroup.RankKind.BY_PREFECTURE );
        m_RankingWithInPrefecture = new RankingGroup( gv , RankingGroup.RankKind.WITH_IN_PREFECTURE);
        m_RankingWholeCountry =  new RankingGroup( gv , RankingGroup.RankKind.WHOLE_COUNTRY);
        m_Obj = new Object();

        // common
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv, 0, 0);

        // set slide member
        SLIDE_LEFT_END_X = m_GameView.getGameWidth();
        SLIDE_LEFT_INIT_X = SLIDE_LEFT_END_X * 3;
        SLIDE_RIGHT_END_X = SLIDE_LEFT_END_X * 2;
        SLIDE_RIGHT_INIT_X = -SLIDE_LEFT_INIT_X;
        SLIDE_SPEED = SLIDE_LEFT_END_X / SLIDE_PER_SPEED;

        if( m_RankingWithInPrefecture != null ){
            m_RankingWithInPrefecture.moveX(SLIDE_LEFT_END_X);
        }
        if( m_RankingWholeCountry != null ) {
            m_RankingWholeCountry.moveX(SLIDE_RIGHT_END_X);
        }

        m_iTouchX = 0;
        m_iTouchY = 0;
        m_bLeftSwipe = false;
        m_bRightSwipe = false;

        reset();
    }

    @Override
    public void update(){
        if( m_UiGroup != null ) {
            m_UiGroup.update();
        }
        if( m_MenuGroup != null ) {
            m_MenuGroup.update();
            m_bMove = m_MenuGroup.getMove();
        }

        // swipe end
        if( m_RannkingByPrefecture != null ){
            // swipe left end
            if( m_bLeftSwipe && m_RannkingByPrefecture.getX() <= -SLIDE_LEFT_END_X ){
                m_RannkingByPrefecture.moveX(SLIDE_LEFT_INIT_X);
                m_bLeftSwipe = false;
            }
            // swipe right end
            if( m_bRightSwipe && m_RannkingByPrefecture.getX() >= SLIDE_RIGHT_END_X ){
                m_RannkingByPrefecture.moveX( SLIDE_RIGHT_INIT_X );
                m_bRightSwipe = false;
            }
        }

        if( m_RankingWithInPrefecture != null ){
            // swipe left end
            if(  m_bLeftSwipe && m_RankingWithInPrefecture.getX() <= -SLIDE_LEFT_END_X ){
                m_RankingWithInPrefecture.moveX(SLIDE_LEFT_INIT_X);
                m_bLeftSwipe = false;
            }

            // swipe right end
            if( m_bRightSwipe && m_RankingWithInPrefecture.getX() >= SLIDE_RIGHT_END_X ){
                m_RankingWithInPrefecture.moveX( SLIDE_RIGHT_INIT_X );
                m_bRightSwipe = false;
            }
        }

        if( m_RankingWholeCountry != null ){
            // swipe left end
            if(  m_bLeftSwipe && m_RankingWholeCountry.getX() <= -SLIDE_LEFT_END_X ){
                m_RankingWholeCountry.moveX( SLIDE_LEFT_INIT_X );
                m_bLeftSwipe = false;
            }

            // swipe right end
            if( m_bRightSwipe && m_RankingWholeCountry.getX() >= SLIDE_RIGHT_END_X ){
                m_RankingWholeCountry.moveX( SLIDE_RIGHT_INIT_X );
                m_bRightSwipe = false;
            }
        }

        if( m_RannkingByPrefecture != null ){
            m_RannkingByPrefecture.update();
            if( m_bLeftSwipe  ) { // left swipe
                m_RannkingByPrefecture.moveX(-SLIDE_SPEED);
            }
            if( m_bRightSwipe  ) { // right swipe
                m_RannkingByPrefecture.moveX(SLIDE_SPEED);
            }
        }
        if( m_RankingWithInPrefecture != null ){
            m_RankingWithInPrefecture.update();
            if( m_bLeftSwipe ) { // left swipe
                m_RankingWithInPrefecture.moveX(-SLIDE_SPEED);
            }
            if( m_bRightSwipe  ) { // right swipe
                m_RankingWithInPrefecture.moveX(SLIDE_SPEED);
            }
        }
        if( m_RankingWholeCountry != null ){
            m_RankingWholeCountry.update();
            if( m_bLeftSwipe  ) { // left swipe
                m_RankingWholeCountry.moveX(-SLIDE_SPEED);
            }
            if( m_bRightSwipe  ) { // right swipe
                m_RankingWholeCountry.moveX(SLIDE_SPEED);
            }
        }
    }

    @Override
    public void reset(){
        m_bMove = false;
        setTouchable( true );
        Log.d("TEST", "scene_Ranking::Reset");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_UiGroup != null){
            m_UiGroup.draw(c);
        }
        if( m_RannkingByPrefecture != null ){
            m_RannkingByPrefecture.draw(c);
        }

        if( m_RankingWithInPrefecture != null ){
            m_RankingWithInPrefecture.draw(c);
        }
        if( m_RankingWholeCountry != null ){
            m_RankingWholeCountry.draw(c);
        }
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if (m_MenuGroup != null) {
            m_MenuGroup.touch(event);
        }
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
                    Log.d("scene_Ranking","left swipe");
                    m_bLeftSwipe = true;
                    m_bRightSwipe = false;
                }
                if( distance > SWIPE_LEN && v_x > 0 ){
                    Log.d("scene_Ranking","right swipe");
                    m_bLeftSwipe = false;
                    m_bRightSwipe = true;
                }
                Log.d("scene_Ranking",String.valueOf(distance) );
                break;

            case MotionEvent.ACTION_CANCEL:
                break;

        }
    }
}