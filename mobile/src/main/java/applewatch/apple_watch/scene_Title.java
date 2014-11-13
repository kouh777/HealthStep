package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/13.
 */
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.util.Log;

public class scene_Title extends Task{
    private Paint m_Paint;
    // image resources
    private BitmapDrawable m_TitleLogo;
    // image status
    private int	m_iTitleLogoWidth;
    private int 	m_iTitleLogoHeight;

    private boolean m_bMove;

    private GameView m_GameView;

    // constract
    public scene_Title(GameView gv, int prio){
        super(prio);
        m_Paint = null;
        m_GameView = gv;

        // logo resource
        m_TitleLogo = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.title_logo);

        //　タイトルロゴの描画サイズの決定
        m_iTitleLogoWidth  = m_TitleLogo.getIntrinsicWidth();
        m_iTitleLogoHeight = m_TitleLogo.getIntrinsicHeight();

        //　画像の縮小率を適用
        m_iTitleLogoWidth	 *= gv.getGamePerWidth();
        m_iTitleLogoHeight *= gv.getGamePerHeight();

        reset();
    }

    @Override
    public void update(){

    }

    @Override
    public void    reset(){
        m_bMove = false;
        Log.d("TEST", "New Title Class");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        int w = m_GameView.getWidth();
        int h = m_GameView.getHeight();
        if (m_TitleLogo != null){
            // for centering logo
            m_TitleLogo.setBounds(
                    (w - m_iTitleLogoWidth) >> 1,
                    (h - m_iTitleLogoHeight) >> 1,
                    (w + m_iTitleLogoWidth) >> 1,
                    (h + m_iTitleLogoHeight) >> 1
            );
            m_TitleLogo.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        // if player don't touch
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            // break
            return;
        }else {
            Log.d("TEST", "get Touch action");
            new scene_Menu(m_GameView, 22);
            m_bMove = true;
        }
    }
}