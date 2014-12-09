package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/04.
 */
public class scene_PartnerSelect extends Task{
    private boolean m_bMove;
    private GameView m_GameView;

    // characters
    private menu_Character m_MsgCaracter;
    private menu_Character m_SelectAkemi;
    private menu_Character m_SelectYukito;

    // game sprites
    private GameSprite m_OK;
    private GameSprite m_Balloon;

    // game text
    private GameText m_InputMsg;

    // message strings
    private String m_StrMsg = "次に君の最初のパートナーを"+GameText.BR+"選択して欲しいコン。";

    // define sprite position
    private final int OK_X = 450;
    private final int OK_Y = 700;
    private final int BALLON_X = 150;
    private final int BALLON_Y = 85;

    // define dark sprite index
    private final int INDEX_DARK = 5;
    private final int INDEX_BRIG = 0;

    // define Character Pos
    private final int CH_AKEMI_X = 15;
    private final int CH_YUKITO_X = 265;
    private final int CH_SELECT_Y = 300;

    // define text pos
    private final int IN_MSG_X = 200;
    private final int IN_MSG_Y  = 130;
    private final int CH_X = -70;
    private final int CH_Y = -70;

    // define text area width
    private final int IN_MSG_WIDTH = 400;
    private final int IN_MSG_LINE_HEIGHT = 10;

    // Game Sound
//    private GameSound m_SeYes;

    // constract
    public scene_PartnerSelect(GameView gv, int prio){
        super(prio);
        m_GameView = gv;
        // game sprite
        m_OK = new GameSprite(gv, OK_X, OK_Y, R.drawable.ok);
        m_Balloon = new GameSprite( gv, BALLON_X , BALLON_Y , R.drawable.hukidashi_yoko );

        // game text
        m_InputMsg = new GameText(gv,m_StrMsg.toCharArray(),IN_MSG_X,IN_MSG_Y);
        m_MsgCaracter = new menu_Character(gv,CH_X,CH_Y,menu_Character.CHAR_KONSUKE_ID);
        m_SelectAkemi = new menu_Character( gv, CH_AKEMI_X, CH_SELECT_Y, menu_Character.CHAR_AKEMI_ID );
        m_SelectYukito = new menu_Character( gv,  CH_YUKITO_X, CH_SELECT_Y, menu_Character.CHAR_YUKITO_ID );

        reset();
    }

    @Override
    public void update(){
        if( m_bMove ){
//            new scene_Menu(m_GameView, 24);
        }
        if( m_InputMsg != null){
            m_InputMsg.update();
        }
    }

    @Override
    public void    reset(){
        m_bMove = false;

        if( m_SelectAkemi != null )
            m_SelectAkemi.getCharacter().setIndex(INDEX_DARK);
        if( m_SelectYukito != null )
            m_SelectYukito.getCharacter().setIndex(INDEX_DARK);

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
        if( m_OK != null ){
            m_OK.draw(c);
        }
        if( m_MsgCaracter != null ){
            m_MsgCaracter.draw(c);
        }
        if( m_Balloon != null ){
            m_Balloon.draw(c);
        }
        if( m_InputMsg != null ){
//            m_InputMsg.multiline_draw(c,IN_MSG_WIDTH,IN_MSG_LINE_HEIGHT);
            m_InputMsg.draw_with_br( c, IN_MSG_LINE_HEIGHT );
        }
        if( m_SelectAkemi != null ){
            m_SelectAkemi.draw(c);
        }
        if( m_SelectYukito != null ){
            m_SelectYukito.draw(c);
        }
    }


    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if( m_OK != null ){
                m_OK.touch(event);
                if( m_OK.getTouch() ){
                    if( checkInput() ) {
                        m_GameView.playSE( R.raw.se_yes );
                        m_bMove = true;
                        new scene_Menu(m_GameView, 24);
                    }
                }
            }
            if( m_SelectAkemi != null ){
                if( m_SelectAkemi.touch(event) ){
                    // touch action
                    PlayerData.getInstance().setUnlockCharacter( 8, false );
                    PlayerData.getInstance().setUnlockCharacter( 0, true );
                    PlayerData.getInstance().setSelectCharacter( menu_Character.CHAR_AKEMI_ID );
                    m_SelectAkemi.getCharacter().setIndex(INDEX_BRIG);
                    m_SelectYukito.getCharacter().setIndex(INDEX_DARK);
                    checkInput();
                    m_GameView.playSE( R.raw.se_yes );
                }
            }
            if( m_SelectYukito != null ){
                if( m_SelectYukito.touch(event) ){
                    PlayerData.getInstance().setUnlockCharacter( 0, false );
                    PlayerData.getInstance().setUnlockCharacter( 8, true );
                    PlayerData.getInstance().setSelectCharacter( menu_Character.CHAR_YUKITO_ID );
                    m_SelectYukito.getCharacter().setIndex(INDEX_BRIG);
                    m_SelectAkemi.getCharacter().setIndex(INDEX_DARK);
                    checkInput();
                    m_GameView.playSE( R.raw.se_yes );
                }
            }
        }
    }

    // check input
    public boolean checkInput(){
        if( PlayerData.getInstance().getUnlockCharacter()[0] == false
                && PlayerData.getInstance().getUnlockCharacter()[8] == false ){
            m_InputMsg.setText("キャラクターが選択されていないコン。"+GameText.BR+"初めの君のパートナーとなる"+GameText.BR+"キャラクターを選択して欲しいコン。");
            return false;
        }else if( PlayerData.getInstance().getUnlockCharacter()[0] == true ){
            m_InputMsg.setText("君が選択したキャラクターは"+GameText.BR+"「アケミ」でいいコン？"+GameText.BR+"これでよければ"+GameText.BR+"「ＯＫ」を押して欲しいコン。");
        }else if( PlayerData.getInstance().getUnlockCharacter()[8] == true ){
            m_InputMsg.setText("君が選択したキャラクターは"+GameText.BR+"「ユキト」でいいコン？"+GameText.BR+"これでよければ"+GameText.BR+"「ＯＫ」を押して欲しいコン。");
        }
        return true;
    }
}
