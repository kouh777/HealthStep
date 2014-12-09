package applewatch.apple_watch;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/12/03.
 */
public class scene_Detail extends Task{
    private boolean m_bMove;

    // game sprite
    private GameSprite m_DetailBack;
    private GameSprite m_DetailWaku;
    private GameSprite m_Return;
    private menu_Character m_DetailCharacter;

    // game texts
    private GameText m_NameText;
    private GameText m_BirthdayText;
    private GameText m_GenderText;
    private GameText m_CommentText;

    // define Text Buffer
    private final int LIST_BUFFER_SIZE = 8;
    private final int COMMENT_BUFFER_SIZE = 64;

    // define Text Positions
    private final int TEXT_COMMENT_X = 90;
    private final int TEXT_LIST_X = 420;
    private final int TEXT_NAME_Y = 120;
    private final int TEXT_BIRTHDAY_Y = 170;
    private final int TEXT_GENDER_Y = 210;
    private final int TEXT_COMMENT_Y = 450;

    // define text width
    private final int COMMENT_WIDTH = 460;
    private final int COMMENT_LINE_HEGHT = 10;

    // common
    private GameView m_GameView;
//    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;
    private GameSprite m_H1;

    // define fade in speed
    private final int FADE_IN_SPEED = 40;

    // define position and scale
    private final int H1_Y = 140;
    private final int BG_POS_Y = 240;
    private final int WAKU_X = 50;
    private final int WAKU_Y = 20;
    private final int CH_POS_X = 15;
    private final int CH_POS_Y = 55;
    private final float WAKU_SCALE = 0.8f;
    private final int RETURN_X = 400;
    private final int RETURN_Y = 1000;

    // constract
    public scene_Detail(GameView gv, int prio, CharacterSprite ch){
        super(prio);
        m_GameView = gv;
        switch(ch.getCharacterKind()){
            case KIND_GIRL:
                m_DetailWaku = new GameSprite(gv,WAKU_X, BG_POS_Y + WAKU_Y,R.drawable.girl_waku);
                break;

            case KIND_BOY:
                m_DetailWaku = new GameSprite(gv,WAKU_X, BG_POS_Y + WAKU_Y,R.drawable.boy_waku);
                break;

            case KIND_BEAST:
                m_DetailWaku = new GameSprite(gv,WAKU_X, BG_POS_Y + WAKU_Y,R.drawable.beast_waku);
                break;
        }
        m_DetailBack = new GameSprite(gv,R.drawable.setsumei);
        m_Return = new GameSprite( gv, RETURN_X,RETURN_Y , R.drawable.modoru );
        m_DetailCharacter = new menu_Character(gv,CH_POS_X,BG_POS_Y + CH_POS_Y,ch.characterID());

        m_NameText = new GameText( gv, LIST_BUFFER_SIZE, TEXT_LIST_X, BG_POS_Y + TEXT_NAME_Y );
        m_BirthdayText  = new GameText( gv, LIST_BUFFER_SIZE, TEXT_LIST_X, BG_POS_Y + TEXT_BIRTHDAY_Y ); ;
        m_GenderText  = new GameText( gv, LIST_BUFFER_SIZE, TEXT_LIST_X, BG_POS_Y + TEXT_GENDER_Y ); ;
        m_CommentText  = new GameText( gv, COMMENT_BUFFER_SIZE, TEXT_COMMENT_X, BG_POS_Y + TEXT_COMMENT_Y ); ;

        //set text
        if( m_NameText != null ){
            m_NameText.setText( ch.getStrName() );
            m_NameText.setDetailListPreset();
        }
        if( m_BirthdayText != null ){
            m_BirthdayText.setText( ch.getStrBirthday() );
            m_BirthdayText.setDetailListPreset();
        }
        if( m_GenderText != null ){
            m_GenderText.setText( ch.getStrGender() );
            m_GenderText.setDetailListPreset();
        }
        if( m_CommentText != null ){
            m_CommentText.setText( ch.getStrComment() );
            m_CommentText.setDetailCommentPreset();
        }

        // common
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_garaly);
  //      m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv, 0, 0);

        reset();
    }

    @Override
    public void reset(){
        if( m_DetailBack != null ){
            m_DetailBack.alignCenterHorizontal();
            m_DetailBack.setY( m_DetailBack.getY() + BG_POS_Y );
        }
        if( m_DetailWaku != null ){
            m_DetailWaku.setScaleX(WAKU_SCALE);
            m_DetailWaku.setScaleY(WAKU_SCALE);
        }
        m_H1.setAlpha(0);
        m_bMove = false;
        setTouchable( true );
        Log.d("TEST", "scene_Detail::Reset");
    }

    @Override
    public void update(){
        if( m_UiGroup != null ) {
            m_UiGroup.update();
        }
        if( m_H1 != null){
            m_H1.fade_in( FADE_IN_SPEED );
        }
        if( m_DetailCharacter != null ){
            m_DetailCharacter.update();
        }
        if( m_NameText != null ){
            m_NameText.update();
        }
        if( m_BirthdayText != null ){
            m_BirthdayText.update();
        }
        if( m_GenderText != null ){
            m_GenderText.update();
        }
        if( m_CommentText != null ){
            m_CommentText.update();
        }
        if( m_Return != null ){
            if( m_Return.getTouch() ) {
                m_bMove = true;
                new scene_Gallery(m_GameView, 20);
            }
        }
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_UiGroup != null){
            m_UiGroup.draw(c);
        }
        if(m_H1 != null){
            m_H1.draw(c);
        }
        if( m_DetailBack != null ){
            m_DetailBack.draw(c);
        }
        if( m_DetailWaku != null ){
            m_DetailWaku.draw(c);
        }
        if( m_DetailCharacter != null ){
            m_DetailCharacter.draw(c);
        }
        if( m_NameText != null ){
            m_NameText.draw(c);
        }
        if( m_BirthdayText != null ){
            m_BirthdayText.draw(c);
        }
        if( m_GenderText != null ){
            m_GenderText.draw(c);
        }
        if( m_CommentText != null ){
            m_CommentText.multiline_draw(c, COMMENT_WIDTH, COMMENT_LINE_HEGHT );
        }
        if( m_Return != null ){
            m_Return.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if ( m_Return != null) {
                m_Return.touch(event);
            }
        }
    }
}
