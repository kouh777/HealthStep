package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/11/08.
 */
public class UiGroup {

    private BitmapDrawable m_UiBackGround;
    private BitmapDrawable m_UiPlayerName;
    private BitmapDrawable m_UiPrefacture;
    private BitmapDrawable m_UiWalkingCount;

    private GameView m_GameView;
    private int m_iPosX;
    private int m_iPosY;

    public UiGroup(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;

        // ui background resource
        m_UiBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.name_bar);
        // ui player_name resource
        m_UiPlayerName = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.moji_playername);
        // ui prefacture_name resource
        m_UiPrefacture = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.moji_todouhuken);
        // ui walking count resource
        m_UiWalkingCount = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.moji_totalwalk);

    }

    public void draw(Canvas c){
        double x = m_iPosX * m_GameView.getGamePerWidth();
        double y = m_iPosY * m_GameView.getGamePerHeight();
        double w = x + m_UiPlayerName.getIntrinsicWidth()  * m_GameView.getGamePerWidth();
        double h = y + m_UiPlayerName.getIntrinsicHeight() * m_GameView.getGamePerHeight();
        double sx = 0.82;
        double sy = 0.19;

        if(m_UiBackGround != null){
            m_UiBackGround.setBounds(0,0,m_GameView.getGameWidth(),m_UiBackGround.getIntrinsicHeight());
            m_UiBackGround.draw(c);
        }

        // for test ui context
        if(m_UiPlayerName != null){
            m_UiPlayerName.setBounds(
                    370,
                    20,
                    370+m_UiPlayerName.getIntrinsicWidth(),
                    20+m_UiPlayerName.getIntrinsicHeight()
            );
            m_UiPlayerName.draw(c);
        }

        // for test ui context
        if(m_UiPrefacture != null){
            m_UiPrefacture.setBounds(
                    20,
                    20,
                    20+m_UiPrefacture.getIntrinsicWidth(),
                    20+m_UiPrefacture.getIntrinsicHeight()
            );
            m_UiPrefacture.draw(c);
        }

        if(m_UiWalkingCount != null){
            m_UiWalkingCount.setBounds(
                    60,
                    75,
                    60+m_UiWalkingCount.getIntrinsicWidth(),
                    75+m_UiWalkingCount.getIntrinsicHeight()
            );
            m_UiWalkingCount.draw(c);
        }
    }
}
