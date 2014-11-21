package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/11/14.
 */
public class gacha_Animation {
    // selecting char ID
    private GameView m_GameView;
    private int m_iPosX;
    private int m_iPosY;

    private boolean m_bGachaEndFlg;

    // sprites
    private GameSprite m_GachaCapselBottom;
    private GameSprite m_GachaCapselMiddle;
    private GameSprite m_GachaCapselTop;
    private GameSprite m_GachaCloudMin;
    private GameSprite m_GachaCloudBig;
    private GameSprite m_GachaHighLight;
    private GameSprite m_GachaSpotLight;
    private GameSprite m_GachaCoreLight;

    // set animation
    private int m_iTimer;

    // define time
    // in this case, count time line with myself so I want to count time automatically
    private final int DELAY_TIME = 10;  // gacha delay time
    private final int CLOUD_MIN_FADEOUT_TIME = 14;
    private final int CLOUD_BIG_FADEOUT_TIME = 22;
    private final int CAPSEL_TOP_OPEN_START_TIME = 26;
    private final int CAPSEL_TOP_OPEN_END_TIME = 28;
    private final int SPOT_LIGHT_START_TIME = 34;
    private final int CAPSEL_TOP_FADEOUT_TIME = 46;
    private final int ALL_ANIMATION_END_TIME = 52;

    // define speed
    private final int CLOUD_MIN_DOWN_SPEED = 20;
    private final int CLOUD_BIG_UP_SPEED = 40;
    private final int CAPSEL_TOP_OPEN_START_SPEED = 1;
    private final int CAPSEL_TOP_OPEN_SPEED = 2;
    private final int CAPSEL_TOP_OPEN_AFTER_SPEED = 10;

    // define alpha value
    private final int ALPHA_TRANSPARENT = 255;
    private final int ALPHA_OPACITY = 0;

    // touch enable flag
    private boolean m_bTouchEnable;

    // construct
    gacha_Animation(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_bGachaEndFlg = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTouchEnable = false;

        // read sprites
        m_GachaCapselBottom = new GameSprite( gv, R.drawable.gacha_capsel_bottom );
        m_GachaCapselMiddle = new GameSprite( gv, R.drawable.gacha_capsel_middle );
        m_GachaCapselTop = new GameSprite( gv, R.drawable.gacha_capsel_top );
        m_GachaCloudMin = new GameSprite( gv, R.drawable.gacha_cloud_min );
        m_GachaCloudBig = new GameSprite( gv, R.drawable.gacha_cloud_big );
        m_GachaHighLight = new GameSprite( gv, R.drawable.gacha_core_light_32 );
        m_GachaSpotLight = new GameSprite( gv, R.drawable.gacha_spot_light );
        m_GachaCoreLight = new GameSprite( gv, R.drawable.gacha_high_light );

        reset();
    }

    public void reset(){
        m_iTimer = 0;

        // reset flag
        m_GachaCapselBottom.setDisplay(false);
        m_GachaCapselMiddle.setDisplay(false);
        m_GachaCapselTop.setDisplay(false);
        m_GachaCloudMin.setDisplay(false);
        m_GachaCloudBig.setDisplay(false);
        m_GachaHighLight.setDisplay(false);
        m_GachaSpotLight.setDisplay(false);
        m_GachaCoreLight.setDisplay(false);
    }

    public void update(){
        m_iTimer++;
        if( m_GachaCloudMin != null ) {
            m_GachaCloudMin.fade( m_iTimer, DELAY_TIME, CLOUD_MIN_FADEOUT_TIME, true );
            m_GachaCloudMin.moveY( m_iTimer, DELAY_TIME, CLOUD_MIN_FADEOUT_TIME, CLOUD_MIN_DOWN_SPEED );
        }
        if( m_GachaCloudBig != null ) {
            m_GachaCloudBig.fade(m_iTimer, CLOUD_MIN_FADEOUT_TIME, CLOUD_BIG_FADEOUT_TIME, true );
            m_GachaCloudBig.moveY( m_iTimer, CLOUD_MIN_FADEOUT_TIME, CLOUD_BIG_FADEOUT_TIME, -CLOUD_BIG_UP_SPEED );
        }
        if( m_GachaCapselTop != null ) {
            m_GachaCapselTop.fade( m_iTimer, SPOT_LIGHT_START_TIME, CAPSEL_TOP_FADEOUT_TIME, true );
            m_GachaCapselTop.moveY( m_iTimer, CAPSEL_TOP_OPEN_START_TIME, CAPSEL_TOP_OPEN_END_TIME, -CAPSEL_TOP_OPEN_START_SPEED );
            m_GachaCapselTop.moveY( m_iTimer, CAPSEL_TOP_OPEN_END_TIME, SPOT_LIGHT_START_TIME, -CAPSEL_TOP_OPEN_SPEED );
            m_GachaCapselTop.moveY( m_iTimer, SPOT_LIGHT_START_TIME, CAPSEL_TOP_FADEOUT_TIME, -CAPSEL_TOP_OPEN_AFTER_SPEED );
        }
        if( m_GachaSpotLight != null ) {
            m_GachaSpotLight.fade( m_iTimer, SPOT_LIGHT_START_TIME, CAPSEL_TOP_FADEOUT_TIME, true );
        }
        if( m_iTimer > DELAY_TIME && m_iTimer < CLOUD_MIN_FADEOUT_TIME){
            m_GachaCloudMin.setDisplay(true);
        }
        if( m_iTimer > CLOUD_MIN_FADEOUT_TIME && m_iTimer < CLOUD_BIG_FADEOUT_TIME){
            // flags
//            m_GachaCloudMin.setDisplay(false);
            m_GachaCloudBig.setDisplay(true);
            m_GachaCapselTop.setDisplay(true);
            m_GachaCapselMiddle.setDisplay(true);
            m_GachaCapselBottom.setDisplay(true);
        }
        if( m_iTimer > CAPSEL_TOP_OPEN_START_TIME && m_iTimer < CAPSEL_TOP_OPEN_END_TIME){
            m_GachaCoreLight.setDisplay(true);
        }
        if( m_iTimer > CAPSEL_TOP_OPEN_END_TIME && m_iTimer < SPOT_LIGHT_START_TIME){
            m_GachaCoreLight.setDisplay(false);
        }
        if(m_iTimer > SPOT_LIGHT_START_TIME && m_iTimer < CAPSEL_TOP_FADEOUT_TIME){
            m_GachaHighLight.setDisplay(true);
            m_GachaSpotLight.setDisplay(true);
        }
        if(m_iTimer > CAPSEL_TOP_FADEOUT_TIME && m_iTimer < ALL_ANIMATION_END_TIME){
            m_GachaSpotLight.setDisplay(false);
        }
        if(m_iTimer > ALL_ANIMATION_END_TIME){
            m_GachaCapselMiddle.setDisplay(false);
            m_GachaCapselBottom.setDisplay(false);
            m_GachaHighLight.setDisplay(false);
            m_bGachaEndFlg = true;
        }
    }

    // draw
    public void draw(Canvas c){
        if( m_GachaCapselMiddle != null){
            m_GachaCapselMiddle.draw(c);
        }
        if(m_GachaCapselBottom != null){
            m_GachaCapselBottom.draw(c);
        }
        if( m_GachaCoreLight != null){
            m_GachaCoreLight.draw(c);
        }
        if( m_GachaHighLight != null){
            m_GachaHighLight.draw(c);
        }
        if(m_GachaSpotLight != null){
            m_GachaSpotLight.draw(c);
        }
        if( m_GachaCapselTop != null){
            m_GachaCapselTop.draw(c);
        }
        if(m_GachaCloudBig != null){
            m_GachaCloudBig.draw(c);
        }
        if(m_GachaCloudMin != null){
            m_GachaCloudMin.draw(c);
        }
    }
    public boolean move(){ return m_bGachaEndFlg;}
}
