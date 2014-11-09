package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/24.
 */

// this class is made for presentation
public class btn_Walk implements Button{

    private GameView m_GameView;

    private BitmapDrawable m_btn_Resource;

    private int m_iBtnWidth;
    private int m_iBtnHeight;
    private int m_iNextSceneID;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bIsTouched;

    public btn_Walk(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iNextSceneID = 0;

        // load image resources
        m_btn_Resource = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.btn_walk);

        // image size
        int w = gv.getGameWidth() >> 2;
        int h = gv.getGameWidth() >> 2;

        m_iBtnWidth  = w;
        m_iBtnHeight = h;

    }

    // if image is touched, return true.
    @Override
    public boolean isTouched() {
        return m_bIsTouched;
    }
    //
    @Override
    public int nextSceneID() {
        return m_iNextSceneID;
    }

    @Override
    public void draw(Canvas c) {
        if (m_btn_Resource != null){
            m_btn_Resource.setBounds(m_iPosX, m_iPosY, m_iPosX+m_iBtnWidth, m_iPosY+m_iBtnHeight);
            m_btn_Resource.draw(c);
        }
    }

    @Override
    public void touch(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return;
        }else {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if( x > m_iPosX && x < m_iPosX + m_iBtnWidth &&
                    y > m_iPosY && y < m_iPosY + m_iBtnHeight){
                Log.d("TEST", "Touch image inside");
                PlayerData.getInstance().addWalkingCount();
                m_bIsTouched = true;
            }else{
                Log.d("TEST", "Touch image outside");
                return;
            }
        }
    }
}
