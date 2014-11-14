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

    private boolean m_bFlg;

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

    private final int DELAY_TIME = 10;  // gacha delay time
    private final int OPEN_SPEED = 10;

    // construct
    gacha_Animation(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_bFlg = false;
        m_iPosX = posX;
        m_iPosY = posY;

        // read resource
        m_GachaCapselBottom =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_bottom);
        m_GachaCapselMiddle =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_middle);
        m_GachaCapselTop =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_capsel_top);
        m_GachaCloudMin =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_cloud_min);
        m_GachaCloudBig =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_cloud_big);
        m_GachaHighLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_high_light);
        m_GachaSpotLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_spot_light);
        m_GachaCoreLight =  (BitmapDrawable)gv.getResources().getDrawable(R.drawable.gacha_core_light);

        // reset flag
        m_bGachaCapselBottom = false;
        m_bGachaCapselMiddle = false;
        m_bGachaCapselTop = false;
        m_bGachaCloudMin = false;
        m_bGachaCloudBig = false;
        m_bGachaHighLight = false;
        m_bGachaSpotLight = false;
        m_bGachaCoreLight = false;
    }

    public void update(){
        m_iTimer++;
        // delay time = 10
        if( m_iTimer > DELAY_TIME){
            m_bGachaCloudMin = true;
        }
        if( m_iTimer > 20){
            m_bGachaCloudMin = false;
        }
    }

    // draw
    public void draw(Canvas c){
        int w = m_GameView.getGameWidth();
        int h = m_GameView.getGameHeight();
        int ww = m_GachaCapselBottom.getIntrinsicWidth();
        int hh = m_GachaCapselBottom.getIntrinsicHeight();
        if(m_GachaCloudMin != null && m_bGachaCloudMin){
            m_GachaCloudMin.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCloudMin.draw(c);
        }
        if(m_GachaCapselBottom != null && m_bGachaCapselBottom){
            m_GachaCapselBottom.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCapselBottom.draw(c);
        }
        if( m_GachaCapselMiddle != null && m_bGachaCapselMiddle ){
            m_GachaCapselMiddle.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCapselMiddle.draw(c);
        }
        if( m_GachaCoreLight != null && m_bGachaCoreLight){
            m_GachaCoreLight.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCoreLight.draw(c);
        }
        if( m_GachaHighLight != null && m_bGachaHighLight){
            m_GachaHighLight.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaHighLight.draw(c);
        }
        if( m_GachaCapselTop != null && m_bGachaCapselTop){
            m_GachaCapselTop.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCapselTop.draw(c);
        }
        if(m_GachaSpotLight != null && m_bGachaSpotLight){
            m_GachaSpotLight.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaSpotLight.draw(c);
        }
        if(m_GachaCloudBig != null && m_bGachaCloudBig){
            m_GachaCloudBig.setBounds(m_iPosX,m_iPosY,m_iPosX + w,m_iPosY + h);
            m_GachaCloudBig.draw(c);
        }
    }
}
