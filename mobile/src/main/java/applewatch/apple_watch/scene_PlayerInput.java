package applewatch.apple_watch;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

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
    private GameSprite m_Test;      // for test

    // relation to Edit Text
    private EditText m_PlayerEdit;

    // prefectures list
    private CharSequence[] m_PreList={
            "北海道","青森県","岩手県",
            "宮城県","秋田県","山形県",
            "福島県","茨城県","栃木県",
            "群馬県","埼玉県","千葉県",
            "東京都","神奈川県","新潟県",
            "富山県","石川県","福井県",
            "山梨県","長野県","岐阜県",
            "静岡県","愛知県","三重県",
            "滋賀県","京都府","大阪府",
            "兵庫県","奈良県","和歌山県",
            "鳥取県","島根県","岡山県",
            "広島県","山口県","徳島県",
            "香川県","愛媛県","高知県",
            "福岡県","佐賀県","長崎県",
            "熊本県","大分県","宮崎県",
            "鹿児島県","沖縄県"

    };

    //--------------------------
    // test sound recognization
    //--------------------------
    private static final int REQUEST_CODE = 100;

    // constract
    public scene_PlayerInput(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        m_InputSprite = new InputSprite(gv, R.drawable.gacha_no);
        m_Test = new GameSprite(gv,200,0, R.drawable.gacha_yes);

        //　SurfaceViewと重ねるTextViewを準備
        m_PlayerEdit = new EditText(gv.getRootView().getContext());
//        m_PlayerEdit.setText("");
//        tv.setY(200f);
        m_PlayerEdit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT ));    // important!
        m_PlayerEdit.setHint("Player Name");
        m_PlayerEdit.setMaxEms(32);     // max chars
        m_PlayerEdit.setY((int) (gv.getHeight() * 0.3));
        m_PlayerEdit.setX((int) (gv.getWidth() * 0.1));
        m_PlayerEdit.setWidth((int) (gv.getWidth() * 0.8));
        m_PlayerEdit.setMaxHeight(80);
        m_PlayerEdit.setTextColor(Color.BLACK);
        m_PlayerEdit.setBackgroundColor(Color.WHITE);
        m_PlayerEdit.setInputType(InputType.TYPE_CLASS_TEXT);   // neglect enter key
        m_PlayerEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int key_code, KeyEvent keyEvent) {
                if( keyEvent.getAction() == KeyEvent.ACTION_DOWN ){
                  // enter key
                  if( key_code == KeyEvent.KEYCODE_ENTER ){
                      hideKeyBoard();
                  }
                }
                return false;
            }
        });
        MobileActivity ma = (MobileActivity)gv.getContext();
        ma.getFrame().addView(m_PlayerEdit);

        reset();
    }

    @Override
    public void update(){
        if( m_InputSprite != null ){
//            m_InputSprite.update();
            // if m_PlayerEdit isn't focused, hide window
        }
    }

    @Override
    public void    reset(){
        m_bMove = false;

        setTouchable( true );
        PlayerData.getInstance().setUnlockCharacter(0,true);
        PlayerData.getInstance().setSelectCharacter( menu_Character.CHAR_AKEMI_ID );
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
        if( m_Test != null ){
            m_Test.draw(c);
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

                    // delete input space
                    MobileActivity ma = (MobileActivity)m_GameView.getContext();
                    ma.getFrame().removeView(m_PlayerEdit);
                    hideKeyBoard();
                    PlayerData.getInstance().setPlayerName(m_PlayerEdit.getText().toString());
                    new scene_Menu(  m_GameView, 24);
                }
            }
            if( m_Test != null ) {
                m_Test.touch(event);
                if (m_Test.getTouch()) {
                    showList();
                }
            }
        }
    }

    // hide keyboard
    public void hideKeyBoard(){
        // hide keyboard
        InputMethodManager imm = (InputMethodManager)m_GameView.getContext().getSystemService( m_GameView.getContext().INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow( m_PlayerEdit.getWindowToken() , InputMethodManager.HIDE_NOT_ALWAYS );
    }

    // item listener
    DialogInterface.OnClickListener mItemListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            PlayerData.getInstance().setPrefecture( m_PreList[which].toString() );
//            Log.d("test",String.valueOf(get));
        }
    };

    // show list dialog
    public void showList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(m_GameView.getContext());
        builder.setTitle("都道府県");
        builder.setItems(m_PreList, mItemListener );
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}