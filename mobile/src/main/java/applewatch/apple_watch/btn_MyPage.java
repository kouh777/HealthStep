package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class btn_MyPage implements Button {
    private GameView m_GameView;

    private BitmapDrawable m_btn_Resource;

    private int m_iBtnWidth;
    private int m_iBtnHeight;
    private int m_iNextSceneID;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bIsTouched;

    // same to CSS of padding
    private final int PADDING_TOP = 6;
    private final int PADDING_RIGHT = 0;
    private final int PADDING_BOTTOM = 2;
    private final int PADDING_LEFT = 4;

    public btn_MyPage(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iNextSceneID = m_GameView.m_SceneMenu;

        // load image resources
        m_btn_Resource = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.button_home);

        // image size
        int w = gv.getGameWidth() >> 2;
        int h = (gv.getGameWidth() >> 4 ) * 3;

        m_iBtnWidth  = w;
        m_iBtnHeight = h;

        /*
        //　image size
        m_iBtnWidth  = m_btn_Resource.getIntrinsicWidth()*2;
        m_iBtnHeight = m_btn_Resource.getIntrinsicHeight()*2;

        //　image resize
        m_iBtnWidth 	 *= gv.getGamePerWidth();
        m_iBtnHeight   *= gv.getGamePerHeight();

        m_iPosX *= gv.getGamePerWidth();
        m_iPosY *= gv.getGamePerHeight();
        */
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
            m_btn_Resource.setBounds(
                    m_iPosX + PADDING_LEFT,
                    m_iPosY + PADDING_TOP,
                    m_iPosX+m_iBtnWidth - PADDING_LEFT - PADDING_RIGHT,
                    m_iPosY+m_iBtnHeight - PADDING_TOP - PADDING_BOTTOM
            );
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
                m_bIsTouched = true;
            }else{
                Log.d("TEST", "Touch image outside");
                return;
            }
        }
    }
}
