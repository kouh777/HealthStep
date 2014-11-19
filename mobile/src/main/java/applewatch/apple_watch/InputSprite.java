package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/11/17.
 */


public class InputSprite extends GameSprite{

    // sprites
    private GameView m_GameView;

    // string buffer
    private String m_InputBuffer;

    // construct
    public InputSprite( GameView gv , int img_res){
        super(gv, img_res);
        reset();
    }

    public void reset(){

    }

    public void update(){

    }

    public void draw(Canvas c){
        super.draw(c);
    }

    public void touch(MotionEvent event){
        super.touch(event);
    }

    public void onKeyDown(  ){}
}
