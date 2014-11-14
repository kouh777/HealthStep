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

    // gacha resource
    private BitmapDrawable m_GachaCapselBottom;
    private BitmapDrawable m_GachaCapselMiddle;
    private BitmapDrawable m_GachaCapselTop;
    private BitmapDrawable m_GachaCloudMin;
    private BitmapDrawable m_GachaCloudBig;
    private BitmapDrawable m_GachaHighLight;
    private BitmapDrawable m_GachaSpotLight;
    private BitmapDrawable m_GachaCoreLight;

    // display flag
    private boolean m_bGachaCapselBottom;
    private boolean m_bGachaCapselMiddle;
    private boolean m_bGachaCapselTop;
    private boolean m_bGachaCloudMin;
    private boolean m_bGachaCloudBig;
    private boolean m_bGachaHighLight;
    private boolean m_bGachaSpotLight;
    private boolean m_bGachaCoreLight;

    // image pos
    // it may create too many member, but use this way;
    private int m_iGachaCapselBottomX;
    private int m_iGachaCapselBottomY;
    private int m_iGachaCapselMiddleX;
    private int m_iGachaCapselMiddleY;
    private int m_iGachaCapselTopX;
    private int m_iGachaCapselTopY;
    private int m_iGachaCloudMinX;
    private int m_iGachaCloudMinY;
    private int m_iGachaCloudBigX;
    private int m_iGachaCloudBigY;
    private int m_iGachaHighLightX;
    private int m_iGachaHighLightY;
    private int m_iGachaSpotLightX;
    private int m_iGachaSpotLightY;
    private int m_iGachaCoreLightX;
    private int m_iGachaCoreLightY;

    // set animation
    private int m_iTimer;

    private int m_iMoveX;
    private int M_iMoveY;

    // define time
    // in this case, count time line with myself so I want to count time automatically
    private final int DELAY_TIME = 10;  // gacha delay time
    private final int CLOUD_MIN_FADEOUT_TIME = 14;
    private final int CLOUD_BIG_FADEOUT_TIME = 24;
    private final int CAPSEL_TOP_OPEN_START_TIME = 28;
    private final int CAPSEL_TOP_OPEN_END_TIME = 30;
    private final int SPOT_LIGHT_START_TIME = 36;
    private final int CAPSEL_TOP_FADEOUT_TIME = 60;
    private final int ALL_ANIMATION_END_TIME = 64;

    // define speed
    private final int CLOUD_MIN_DOWN_SPEED = 20;
    private final int CLOUD_BIG_UP_SPEED = 40;
    private final int CAPSEL_TOP_OPEN_START_SPEED = 1;
    private final int CAPSEL_TOP_OPEN_SPEED = 2;
    private final int CAPSEL_TOP_OPEN_AFTER_SPEED = 6;

    // define alpha value
    private final int ALPHA_TRANSPARENT = 255;
    private final int ALPHA_OPACITY = 0;

    // fade speed
    private int m_iGachaCloudMinAlpha;
    private int m_iGachaCloudBigAlpha;
    private int m_iGachaCapselTopAlpha;
    private int m_iGachaSpotLightAlpha;

    // touch enable flag
    private boolean m_bTouchEnable;

    // construct
    gacha_Animation(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_bGachaEndFlg = false;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTouchEnable = false;

        // read resource
        m_GachaCapselBottom =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_bottom);
        m_GachaCapselMiddle =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_middle);
        m_GachaCapselTop =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_top);
        m_GachaCloudMin =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_cloud_min);
        m_GachaCloudBig =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_cloud_big);
        m_GachaHighLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_core_light_32);
        m_GachaSpotLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_spot_light);
        m_GachaCoreLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_high_light);

        reset();
    }

    public void reset(){
        m_iTimer = 0;

        // reset flag
        m_bGachaCapselBottom = false;
        m_bGachaCapselMiddle = false;
        m_bGachaCapselTop = false;
        m_bGachaCloudMin = false;
        m_bGachaCloudBig = false;
        m_bGachaHighLight = false;
        m_bGachaSpotLight = false;
        m_bGachaCoreLight = false;

        // reset alpha
        m_iGachaCloudMinAlpha = ALPHA_TRANSPARENT;
        m_iGachaCloudBigAlpha = ALPHA_TRANSPARENT;
        m_iGachaCapselTopAlpha = ALPHA_TRANSPARENT;
        m_iGachaSpotLightAlpha = ALPHA_TRANSPARENT;
    }

    public void update(){
        m_iTimer++;
        if( m_iTimer > DELAY_TIME && m_iTimer < CLOUD_MIN_FADEOUT_TIME){
            m_bGachaCloudMin = true;
            // moving object
            m_iGachaCloudMinY += CLOUD_MIN_DOWN_SPEED;

            // set alpha and fade out object
            int fade_out_speed = 255/(CLOUD_MIN_FADEOUT_TIME - DELAY_TIME);
            m_iGachaCloudMinAlpha -= fade_out_speed;
            if( m_iGachaCloudMinAlpha < ALPHA_OPACITY) m_iGachaCloudMinAlpha = ALPHA_OPACITY;
            m_GachaCloudMin.setAlpha(m_iGachaCloudMinAlpha);
        }
        if( m_iTimer > CLOUD_MIN_FADEOUT_TIME && m_iTimer < CLOUD_BIG_FADEOUT_TIME){
            // flags
            m_bGachaCloudMin = false;
            m_bGachaCloudBig = true;
            m_bGachaCapselTop = true;
            m_bGachaCapselMiddle = true;
            m_bGachaCapselBottom = true;

            // move object
            m_iGachaCloudBigY -= CLOUD_BIG_UP_SPEED;

            // set alpha and fade out object
            int fade_out_peed = 255/(CLOUD_BIG_FADEOUT_TIME - CLOUD_MIN_FADEOUT_TIME);
            m_iGachaCloudBigAlpha -= fade_out_peed;
            if( m_iGachaCloudBigAlpha < ALPHA_OPACITY) m_iGachaCloudBigAlpha = ALPHA_OPACITY;
            m_GachaCloudBig.setAlpha(m_iGachaCloudBigAlpha);

        }
        if( m_iTimer > CLOUD_BIG_FADEOUT_TIME && m_iTimer < CAPSEL_TOP_OPEN_START_TIME){
            m_bGachaCloudBig = false;
        }
        if( m_iTimer > CAPSEL_TOP_OPEN_START_TIME && m_iTimer < CAPSEL_TOP_OPEN_END_TIME){
            m_bGachaCoreLight = true;
            m_iGachaCapselTopY -= CAPSEL_TOP_OPEN_START_SPEED;
            m_iGachaSpotLightY -= CAPSEL_TOP_OPEN_START_SPEED;
        }
        if( m_iTimer > CAPSEL_TOP_OPEN_END_TIME && m_iTimer < SPOT_LIGHT_START_TIME){
            m_bGachaCoreLight = false;
            m_iGachaCapselTopY -= CAPSEL_TOP_OPEN_SPEED;
            m_iGachaSpotLightY -= CAPSEL_TOP_OPEN_SPEED;
        }
        if(m_iTimer > SPOT_LIGHT_START_TIME && m_iTimer < CAPSEL_TOP_FADEOUT_TIME){
            m_bGachaHighLight= true;
            m_bGachaSpotLight = true;
            m_iGachaCapselTopY -= CAPSEL_TOP_OPEN_AFTER_SPEED;
            m_iGachaSpotLightY -= CAPSEL_TOP_OPEN_AFTER_SPEED;

            // set alpha and fade out object
            int fade_out_speed = 255/(CAPSEL_TOP_FADEOUT_TIME - SPOT_LIGHT_START_TIME);
            m_iGachaCapselTopAlpha -= fade_out_speed;
            m_iGachaSpotLightAlpha -= fade_out_speed;
            if(m_iGachaCapselTopAlpha < ALPHA_OPACITY) m_iGachaCapselTopAlpha = ALPHA_TRANSPARENT;
            if( m_iGachaSpotLightAlpha < ALPHA_OPACITY) m_iGachaSpotLightAlpha = ALPHA_OPACITY;
            m_GachaCapselTop.setAlpha(m_iGachaCapselTopAlpha);
            m_GachaSpotLight.setAlpha(m_iGachaSpotLightAlpha);
        }
        if(m_iTimer > CAPSEL_TOP_FADEOUT_TIME && m_iTimer < ALL_ANIMATION_END_TIME){
            m_bGachaCapselTop = false;
            m_bGachaSpotLight = false;
        }
        if(m_iTimer > ALL_ANIMATION_END_TIME){
            m_bGachaCapselMiddle = false;
            m_bGachaCapselBottom = false;
            m_bGachaHighLight= false;
            m_bGachaEndFlg = true;
        }
    }

    // draw
    public void draw(Canvas c){
        int w = m_GameView.getGameWidth();
        int h = m_GameView.getGameHeight();
        int ww = m_GachaCapselBottom.getIntrinsicWidth();
        int hh = m_GachaCapselBottom.getIntrinsicHeight();
        if( m_GachaCapselMiddle != null && m_bGachaCapselMiddle ){
            m_GachaCapselMiddle.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCapselMiddle.draw(c);
        }
        if( m_GachaCoreLight != null && m_bGachaCoreLight){
            m_GachaCoreLight.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCoreLight.draw(c);
        }
        if(m_GachaCapselBottom != null && m_bGachaCapselBottom){
            m_GachaCapselBottom.setBounds(
                    m_iPosX + m_iGachaCapselBottomX,
                    m_iPosY + m_iGachaCapselBottomY,
                    m_iPosX + w + m_iGachaCapselBottomX,
                    m_iPosY + h + m_iGachaCapselBottomY
            );
            m_GachaCapselBottom.draw(c);
        }
        if( m_GachaHighLight != null && m_bGachaHighLight){
            m_GachaHighLight.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaHighLight.draw(c);
        }
        if(m_GachaSpotLight != null && m_bGachaSpotLight){
            m_GachaSpotLight.setBounds(
                    m_iPosX + m_iGachaSpotLightX,
                    m_iPosY + m_iGachaSpotLightY,
                    m_iPosX + w + m_iGachaSpotLightX,
                    m_iPosY + h + m_iGachaSpotLightY
            );
            m_GachaSpotLight.draw(c);
        }
        if( m_GachaCapselTop != null && m_bGachaCapselTop){
            m_GachaCapselTop.setBounds(
                    m_iPosX + m_iGachaCapselTopX,
                    m_iPosY + m_iGachaCapselTopY,
                    m_iPosX + w + m_iGachaCapselTopX,
                    m_iPosY + h + m_iGachaCapselTopY
            );
            m_GachaCapselTop.draw(c);
        }
        if(m_GachaCloudBig != null && m_bGachaCloudBig){
            m_GachaCloudBig.setBounds(
                    m_iPosX + m_iGachaCloudBigX,
                    m_iPosY + m_iGachaCloudBigY,
                    m_iPosX + w +  m_iGachaCloudBigX,
                    m_iPosY + h + m_iGachaCloudBigY
            );
            m_GachaCloudBig.draw(c);
        }
        if(m_GachaCloudMin != null && m_bGachaCloudMin){
            m_GachaCloudMin.setBounds(
                    m_iPosX + m_iGachaCloudMinX,
                    m_iPosY + m_iGachaCloudMinY,
                    m_iPosX + w + m_iGachaCloudMinX,
                    m_iPosY + h + m_iGachaCloudMinY
            );
            m_GachaCloudMin.draw(c);
        }
    }
    public boolean move(){ return m_bGachaEndFlg;}
}
