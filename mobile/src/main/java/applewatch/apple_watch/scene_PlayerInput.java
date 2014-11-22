package applewatch.apple_watch;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.speech.RecognizerIntent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by KOUHO on 2014/11/16.
 */
public class scene_PlayerInput extends Task{

    private boolean m_bMove;
    private GameView m_GameView;

    // input player name
    private EditText p_name;
    private SpannableStringBuilder sb;

    private InputSprite m_InputSprite;

    //--------------------------
    // test sound recognization
    //--------------------------
    private static final int REQUEST_CODE = 100;


    // constract
    public scene_PlayerInput(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_InputSprite = new InputSprite(gv, R.drawable.gacha_no);

        reset();
    }

    @Override
    public void update(){
        if( m_InputSprite != null ){
//            m_InputSprite.update();
        }
    }

    @Override
    public void    reset(){
        m_bMove = false;

        setTouchable( true );
        PlayerData.getInstance().setUnlockCharacter(0,true);

        Log.d("TEST", "New PlayerInput Class");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_InputSprite != null ){
            m_InputSprite.draw(c);
        }
        if( m_bMove ){
            new scene_Menu( m_GameView, 25);
        }
    }

    public void onButtonClick(){
        // create intent
        try{
            Intent intent = new Intent(
                    RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    "VoiceRecognitionStart");


            // result of intent
            //startActivityForResult(intent, REQUEST_CODE);
        }catch( ActivityNotFoundException e){
            // if activity doesn't install
            Toast.makeText( m_GameView.getContext(), "AcivityNotFoundException",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if( m_InputSprite != null ){
                m_InputSprite.touch(event);
                if( m_InputSprite.getTouch() ){
                    onButtonClick();
                    m_bMove = true;
                    new scene_Menu(  m_GameView, 24);
                }
            }
        }
    }
    // getter
}
