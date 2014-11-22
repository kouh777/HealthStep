package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/11/06.
 */
public class MessageCharacter {
    private GameSprite m_MsgBg;
    private String m_MessageStr;
    private GameView m_GameView;
    private int m_iPosX;
    private int m_iPosY;
    private final int m_FontSize = 30;

    // game texts
    private GameText m_WhetherText;
    private GameText m_YellText;

    // text draw flag
    private boolean m_bTextFlg;
    private boolean m_bTextYellFlg;

    // text index
    private int m_iTextIndex;

    // text date
    String StrWhether = "おはよう！いい天気だね！";    // Hello and Whether
    String StrYell = "今日も元気にいこう。";        // Yell For

    // define char array size
    private final int DISP_BUFFER_SIZE = 32;

    // define Sprite Scale
    private final double BG_SX = 0.9;
    private final double BG_SY = 1.1;

    // define zoom speed
    private final double ZOOM_SPEED =0.2;

    public MessageCharacter(GameView gv, int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_bTextFlg =false;

        m_iTextIndex = 0;
        m_bTextYellFlg = false;

        // sprite
        m_MsgBg = new GameSprite( gv, posX , posY,  R.drawable.hukidasi, BG_SX, 0, 255 );

        // text
        m_WhetherText = new GameText( gv, DISP_BUFFER_SIZE, posX + 20, posY + 108 );
        m_YellText = new GameText( gv, DISP_BUFFER_SIZE, posX + 20, posY + 148 );
    }

    public void update(){
        if( m_MsgBg != null ){
            m_MsgBg.zoomIn( AnimKind.ANIM_SY , ZOOM_SPEED, BG_SY);
            if( m_MsgBg.getScaleY() >= BG_SY ){
                m_bTextFlg = true;
            }
        }
        if( m_bTextFlg ){
            if( !m_bTextYellFlg && m_iTextIndex < StrWhether.length() ){
                // set setring
                m_WhetherText.setText( StrWhether.charAt(m_iTextIndex), m_iTextIndex );
                m_iTextIndex++;
            } else if( !m_bTextYellFlg && m_iTextIndex >= StrWhether.length() )  {
                m_bTextYellFlg = true;
                m_iTextIndex = 0;
            }
            if( m_bTextYellFlg ){
                if( m_iTextIndex < StrYell.length() ){
                    // set setring
                    m_YellText.setText( StrYell.charAt(m_iTextIndex), m_iTextIndex );
                }
                m_iTextIndex++;
            }
        }
        if( m_WhetherText != null ){
            m_WhetherText.update();
        }
        if( m_YellText != null ){
            m_YellText.update();
        }
    }

    public void draw(Canvas c){
        // draw sprite
        if( m_MsgBg != null ){
            m_MsgBg.draw(c);
        }
        if( m_WhetherText != null ){
            m_WhetherText.draw(c);
        }
        if( m_YellText != null ){
            m_YellText.draw(c);
        }
    }
}
