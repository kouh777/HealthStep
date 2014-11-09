package applewatch.apple_watch;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by KOUHO on 2014/10/23.
 */
public class numImage {

    private Bitmap m_Bitmap;

    private int m_iImgWidth;
    private int m_iImgHeight;
    private int m_iPosX;
    private int m_iPosY;
    private int m_iNum;
    private int m_iFigure;

    // image trimming
    private final int m_iTrimWidth = 64;
    private int m_iImgOX = 0;
    private final int m_iImgOY = 0;

    // constructor
    public numImage(GameView gv,int posX, int posY, float scale){
        m_iNum = PlayerData.getInstance().getWalkingCount();

        m_iPosX = posX;
        m_iPosY = posY;

        // read from resource
        Resources r = gv.getResources();
        m_Bitmap = BitmapFactory.decodeResource(r, R.drawable.ui_numbers);

        //　image size
        m_iImgWidth  = (int)(m_Bitmap.getWidth() * scale);
        m_iImgHeight = (int)(m_Bitmap.getHeight() * scale);

        //　image resize
        m_iImgWidth 	 *= gv.getGamePerWidth();
        m_iImgHeight   *= gv.getGamePerHeight();

        m_iPosX *= gv.getGamePerWidth();
        m_iPosY *= gv.getGamePerHeight();
    }

    public void draw(Canvas c) {
        if (m_Bitmap != null){
            m_iNum = PlayerData.getInstance().getWalkingCount();
            String str = Integer.toString(m_iNum);
            m_iFigure = str.length();

            for(int i = 0; i < m_iFigure; ++i ){
                char LookChar =  str.charAt(i);             // 1
//                int takeNum = Integer.valueOf(LookChar) - '0';    // 1
                int takeNum = Character.getNumericValue(LookChar);
                m_iImgOX =  takeNum* m_iTrimWidth;

                // change here
                Rect src = new Rect(m_iImgOX, m_iImgOY, m_iImgOX+m_iTrimWidth,m_iImgOY+m_iImgHeight);
                Rect dst = new Rect(m_iPosX + (i * m_iTrimWidth),m_iPosY, m_iPosX + m_iTrimWidth + (i * m_iTrimWidth),m_iPosY + m_iImgHeight);

                // use draw bitmap
                c.drawBitmap(m_Bitmap, src, dst, null);

            }
        }
    }
}
