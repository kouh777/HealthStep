package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class scene_Ranking extends Task {

    private boolean m_bMove;

    private GameSprite m_RankingNo1;
    private GameSprite m_RankingNo2;
    private GameSprite m_RankingNo3;
    private GameSprite m_RankingOthers;

    private GameView m_GameView;
    private MenuGroup m_MenuGroup;
    private UiGroup m_UiGroup;

    private GameSprite m_H1;

    // define position and scale
    private final int H1_Y = 140;

    private final int GOLD_Y = 230;
    private final int SILVER_Y = 350;
    private final int BROND_Y = 470;
    private final int OTHERS_Y = 580;
    private final double BEST_SY = 0.52;
    private final double OHTERS_SY = 0.52;

    // define fade in speed
    private final int FADE_IN_SPEED = 40;


    private GameText m_Rank1Num;   // rank 1
    private GameText m_Rank2Num;   // rank 2
    private GameText m_Rank3Num;   // rank 3
    private GameText m_Rank4Num;   // rank 4
    private GameText m_Rank5Num;   // rank 5
    private GameText m_Rank6Num;   // rank 6
    private GameText m_Rank7Num;   // rank 7
    private GameText m_Rank8Num;   // rank 8
    private GameText m_Rank9Num;   // rank 9
    private GameText m_Rank10Num;  // rank 10
    private GameText m_Rank11Num;  // rank 11
    private GameText m_Rank12Num;  // rank 12
    private GameText m_Rank13Num;  // rank 13
    private GameText m_Rank14Num;  // rank 14

    private GameText m_Rank1Name;   // rank 1 name
    private GameText m_Rank2Name;   // rank 2 name
    private GameText m_Rank3Name;   // rank 3 name
    private GameText m_Rank4Name;   // rank 4 name
    private GameText m_Rank5Name;   // rank 5 name
    private GameText m_Rank6Name;   // rank 6 name
    private GameText m_Rank7Name;   // rank 7 name
    private GameText m_Rank8Name;   // rank 8 name
    private GameText m_Rank9Name;   // rank 9 name
    private GameText m_Rank10Name;  // rank 10 name
    private GameText m_Rank11Name;  // rank 11 name
    private GameText m_Rank12Name;  // rank 12 name
    private GameText m_Rank13Name;  // rank 13 name
    private GameText m_Rank14Name;  // rank 14 name


    private GameText m_Rank1Score;  // rank 1 score
    private GameText m_Rank2Score;  // rank 2 score
    private GameText m_Rank3Score;  // rank 3 score
    private GameText m_Rank4Score;  // rank 4 score
    private GameText m_Rank5Score;  // rank 5 score
    private GameText m_Rank6Score;  // rank 6 score
    private GameText m_Rank7Score;  // rank 7 score
    private GameText m_Rank8Score;  // rank 8 score
    private GameText m_Rank9Score;  // rank 9 score
    private GameText m_Rank10Score;  // rank 10 score
    private GameText m_Rank11Score;  // rank 11 score
    private GameText m_Rank12Score;  // rank 12 score
    private GameText m_Rank13Score;  // rank 13 score
    private GameText m_Rank14Score;  // rank 4 score

    // define Text Positions
    private final int RA_NUM_X = 140;     // num position x
    private final int RA_NAME_X = 190;    // name position x
    private final int RA_OH_NUM_X = 165;  // other name positionX
    private final int RA_OH_NAME_X = 210;  // other name positionX
    private final int RA_SC_X = 370;      // score position x

    private final int RA_NO_1 = 320;    // no1 position y
    private final int RA_NO_2 = 440;    // no2 position y
    private final int RA_NO_3 = 560;    // no3 position y

    private final int RA_NO_4 = 670;    // no1 position y
    private final int RA_NO_5 = 695;    // no2 position y
    private final int RA_NO_6 = 720;    // no3 position y
    private final int RA_NO_7 = 745;    // no1 position y
    private final int RA_NO_8 = 770;    // no2 position y
    private final int RA_NO_9 = 795;    // no3 position y
    private final int RA_NO_10 = 820;    // no2 position y
    private final int RA_NO_11 = 845;    // no3 position y
    private final int RA_NO_12 = 870;    // no2 position y
    private final int RA_NO_13 = 895;    // no3 position y
    private final int RA_NO_14 = 920;    // no3 position y

    // define Rank otheers' text size
    private final int RA_OT_SIZE = 20;

    // constract
    public scene_Ranking(GameView gv, int prio){
        super(prio);
        m_GameView = gv;

        // sprites
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_ranking);
        m_RankingNo1 = new GameSprite( gv, R.drawable.ranking_no1 );
        m_RankingNo2 = new GameSprite( gv, R.drawable.ranking_no2 );
        m_RankingNo3 = new GameSprite( gv, R.drawable.ranking_no3 );
        m_RankingOthers = new GameSprite( gv, R.drawable.ranking_others );

        // common
        m_H1 = new GameSprite(gv, 0, H1_Y, R.drawable.h1_ranking);
        m_MenuGroup = new MenuGroup(gv);
        m_UiGroup = new UiGroup(gv, 0, 0);

        m_Rank1Num = new GameText( gv , "1位".toCharArray() , RA_NUM_X , RA_NO_1 );
        m_Rank2Num = new GameText( gv , "2位".toCharArray() , RA_NUM_X , RA_NO_2 );
        m_Rank3Num = new GameText( gv , "3位".toCharArray() , RA_NUM_X , RA_NO_3 );
        m_Rank4Num = new GameText( gv , "4位".toCharArray() , RA_OH_NUM_X , RA_NO_4 );
        m_Rank5Num = new GameText( gv , "5位".toCharArray() , RA_OH_NUM_X , RA_NO_5 );
        m_Rank6Num = new GameText( gv , "6位".toCharArray() , RA_OH_NUM_X , RA_NO_6 );
        m_Rank7Num = new GameText( gv , "7位".toCharArray() , RA_OH_NUM_X , RA_NO_7 );
        m_Rank8Num = new GameText( gv , "8位".toCharArray() , RA_OH_NUM_X , RA_NO_8 );
        m_Rank9Num = new GameText( gv , "9位".toCharArray() , RA_OH_NUM_X , RA_NO_9 );
        m_Rank10Num = new GameText( gv , "10位".toCharArray() , RA_OH_NUM_X , RA_NO_10 );
        m_Rank11Num = new GameText( gv , "11位".toCharArray() , RA_OH_NUM_X , RA_NO_11 );
        m_Rank12Num = new GameText( gv , "12位".toCharArray() , RA_OH_NUM_X , RA_NO_12 );
        m_Rank13Num = new GameText( gv , "13位".toCharArray() , RA_OH_NUM_X , RA_NO_13 );
        m_Rank14Num = new GameText( gv , "14位".toCharArray() , RA_OH_NUM_X , RA_NO_14 );

        m_Rank1Name = new GameText( gv , "兵庫県".toCharArray() , RA_NAME_X , RA_NO_1 );
        m_Rank2Name = new GameText( gv , "大阪府".toCharArray() , RA_NAME_X , RA_NO_2 );
        m_Rank3Name = new GameText( gv , "愛媛県".toCharArray() , RA_NAME_X , RA_NO_3 );
        m_Rank4Name = new GameText( gv , "京都府".toCharArray() , RA_OH_NAME_X , RA_NO_4 );
        m_Rank5Name = new GameText( gv , "沖縄県".toCharArray() , RA_OH_NAME_X , RA_NO_5 );
        m_Rank6Name = new GameText( gv , "新潟県".toCharArray() , RA_OH_NAME_X , RA_NO_6 );
        m_Rank7Name = new GameText( gv , "岡山県".toCharArray() , RA_OH_NAME_X , RA_NO_7 );
        m_Rank8Name = new GameText( gv , "長野県".toCharArray() , RA_OH_NAME_X , RA_NO_8 );
        m_Rank9Name = new GameText( gv , "東京都".toCharArray() , RA_OH_NAME_X , RA_NO_9 );
        m_Rank10Name = new GameText( gv , "神奈川県".toCharArray() , RA_OH_NAME_X , RA_NO_10 );
        m_Rank11Name = new GameText( gv , "福島県".toCharArray() , RA_OH_NAME_X , RA_NO_11 );
        m_Rank12Name = new GameText( gv , "千葉県".toCharArray() , RA_OH_NAME_X , RA_NO_12 );
        m_Rank13Name = new GameText( gv , "山梨県".toCharArray() , RA_OH_NAME_X , RA_NO_13 );
        m_Rank14Name = new GameText( gv , "北海道".toCharArray() , RA_OH_NAME_X , RA_NO_14 );

        m_Rank1Score = new GameText( gv , "12345".toCharArray() , RA_SC_X , RA_NO_1 );
        m_Rank2Score = new GameText( gv , "12344".toCharArray() , RA_SC_X , RA_NO_2 );
        m_Rank3Score = new GameText( gv , "12343".toCharArray() , RA_SC_X , RA_NO_3 );
        m_Rank4Score = new GameText( gv , "10000".toCharArray() , RA_SC_X , RA_NO_4 );
        m_Rank5Score = new GameText( gv , "9999".toCharArray() , RA_SC_X , RA_NO_5 );
        m_Rank6Score = new GameText( gv , "9989".toCharArray() , RA_SC_X , RA_NO_6 );
        m_Rank7Score = new GameText( gv , "7789".toCharArray() , RA_SC_X , RA_NO_7 );
        m_Rank8Score = new GameText( gv , "6789".toCharArray() , RA_SC_X , RA_NO_8 );
        m_Rank9Score = new GameText( gv , "5789".toCharArray() , RA_SC_X , RA_NO_9 );
        m_Rank10Score = new GameText( gv , "5000".toCharArray() , RA_SC_X , RA_NO_10 );
        m_Rank11Score = new GameText( gv , "4000".toCharArray() , RA_SC_X , RA_NO_11 );
        m_Rank12Score = new GameText( gv , "3000".toCharArray() , RA_SC_X , RA_NO_12 );
        m_Rank13Score = new GameText( gv , "2000".toCharArray() , RA_SC_X , RA_NO_13 );
        m_Rank14Score = new GameText( gv , "1200".toCharArray() , RA_SC_X , RA_NO_14 );

        reset();
    }

    @Override
    public void update(){
        if( m_MenuGroup != null ) {
            m_MenuGroup.update();
            m_bMove = m_MenuGroup.getMove();
        }
    }

    @Override
    public void reset(){
        // set Sprites
        m_RankingNo1.alignCenterHorizontal();
        m_RankingNo1.setY(GOLD_Y);
        m_RankingNo1.setScaleY(BEST_SY);

        m_RankingNo2.alignCenterHorizontal();
        m_RankingNo2.setY(SILVER_Y);
        m_RankingNo2.setScaleY(BEST_SY);

        m_RankingNo3.alignCenterHorizontal();
        m_RankingNo3.setY(BROND_Y);
        m_RankingNo3.setScaleY(BEST_SY);

        m_RankingOthers.alignCenterHorizontal();
        m_RankingOthers.setY(OTHERS_Y);
        m_RankingOthers.setScaleY(OHTERS_SY);

        // set text size
        m_Rank4Num.setTextSize(RA_OT_SIZE);
        m_Rank5Num.setTextSize(RA_OT_SIZE);
        m_Rank6Num.setTextSize(RA_OT_SIZE);
        m_Rank7Num.setTextSize(RA_OT_SIZE);
        m_Rank8Num.setTextSize(RA_OT_SIZE);
        m_Rank9Num.setTextSize(RA_OT_SIZE);
        m_Rank10Num.setTextSize(RA_OT_SIZE);
        m_Rank11Num.setTextSize(RA_OT_SIZE);
        m_Rank12Num.setTextSize(RA_OT_SIZE);
        m_Rank13Num.setTextSize(RA_OT_SIZE);
        m_Rank14Num.setTextSize(RA_OT_SIZE);

        m_Rank4Name.setTextSize(RA_OT_SIZE);
        m_Rank5Name.setTextSize(RA_OT_SIZE);
        m_Rank6Name.setTextSize(RA_OT_SIZE);
        m_Rank7Name.setTextSize(RA_OT_SIZE);
        m_Rank8Name.setTextSize(RA_OT_SIZE);
        m_Rank9Name.setTextSize(RA_OT_SIZE);
        m_Rank10Name.setTextSize(RA_OT_SIZE);
        m_Rank11Name.setTextSize(RA_OT_SIZE);
        m_Rank12Name.setTextSize(RA_OT_SIZE);
        m_Rank13Name.setTextSize(RA_OT_SIZE);
        m_Rank14Name.setTextSize(RA_OT_SIZE);

        m_Rank4Score.setTextSize(RA_OT_SIZE);
        m_Rank5Score.setTextSize(RA_OT_SIZE);
        m_Rank6Score.setTextSize(RA_OT_SIZE);
        m_Rank7Score.setTextSize(RA_OT_SIZE);
        m_Rank8Score.setTextSize(RA_OT_SIZE);
        m_Rank9Score.setTextSize(RA_OT_SIZE);
        m_Rank10Score.setTextSize(RA_OT_SIZE);
        m_Rank11Score.setTextSize(RA_OT_SIZE);
        m_Rank12Score.setTextSize(RA_OT_SIZE);
        m_Rank13Score.setTextSize(RA_OT_SIZE);
        m_Rank14Score.setTextSize(RA_OT_SIZE);

        m_H1.setAlpha(0);

        m_bMove = false;
        setTouchable( true );
        Log.d("TEST", "scene_Ranking::Reset");
    }

    @Override
    // go to next scene
    public boolean move(){
        return m_bMove;
    }

    @Override
    // draw
    public void    draw(Canvas c){
        if( m_H1 != null){
            m_H1.fade_in( FADE_IN_SPEED );
        }
        if( m_UiGroup != null){
            m_UiGroup.draw(c);
        }
        if( m_RankingNo1 != null ){
            m_RankingNo1.draw(c);
        }
        if(m_RankingNo2 != null){
            m_RankingNo2.draw(c);
        }
        if(m_RankingNo3 != null){
            m_RankingNo3.draw(c);
        }
        if(m_RankingOthers != null){
            m_RankingOthers.draw(c);
        }
        if(m_H1 != null){
            m_H1.draw(c);
        }
        if( m_MenuGroup != null){
            m_MenuGroup.draw(c);
        }
        if( m_Rank1Num != null ){
            m_Rank1Num.draw(c);
        }
        if( m_Rank2Num != null ){
            m_Rank2Num.draw(c);
        }
        if( m_Rank3Num != null ){
            m_Rank3Num.draw(c);
        }
        if( m_Rank4Num != null ){
            m_Rank4Num.draw(c);
        }
        if( m_Rank5Num != null ){
            m_Rank5Num.draw(c);
        }
        if( m_Rank6Num != null ){
            m_Rank6Num.draw(c);
        }
        if( m_Rank7Num != null ){
            m_Rank7Num.draw(c);
        }
        if( m_Rank8Num != null ){
            m_Rank8Num.draw(c);
        }
        if( m_Rank9Num != null ){
            m_Rank9Num.draw(c);
        }
        if( m_Rank10Num != null ){
            m_Rank10Num.draw(c);
        }
        if( m_Rank11Num != null ){
            m_Rank11Num.draw(c);
        }
        if( m_Rank12Num != null ){
            m_Rank12Num.draw(c);
        }
        if( m_Rank13Num != null ){
            m_Rank13Num.draw(c);
        }
        if( m_Rank14Num != null ){
            m_Rank14Num.draw(c);
        }

        if( m_Rank1Name != null ){
            m_Rank1Name.draw(c);
        }
        if( m_Rank2Name != null ){
            m_Rank2Name.draw(c);
        }
        if( m_Rank3Name != null ){
            m_Rank3Name.draw(c);
        }
        if( m_Rank4Name != null ){
            m_Rank4Name.draw(c);
        }
        if( m_Rank5Name != null ){
            m_Rank5Name.draw(c);
        }
        if( m_Rank6Name != null ){
            m_Rank6Name.draw(c);
        }
        if( m_Rank7Name != null ){
            m_Rank7Name.draw(c);
        }
        if( m_Rank8Name != null ){
            m_Rank8Name.draw(c);
        }
        if( m_Rank9Name != null ){
            m_Rank9Name.draw(c);
        }
        if( m_Rank10Name != null ){
            m_Rank10Name.draw(c);
        }
        if( m_Rank11Name != null ){
            m_Rank11Name.draw(c);
        }
        if( m_Rank12Name != null ){
            m_Rank12Name.draw(c);
        }
        if( m_Rank13Name != null ){
            m_Rank13Name.draw(c);
        }
        if( m_Rank14Name != null ){
            m_Rank14Name.draw(c);
        }
        if( m_Rank1Score != null ){
            m_Rank1Score.draw(c);
        }
        if( m_Rank2Score != null ){
            m_Rank2Score.draw(c);
        }
        if( m_Rank3Score != null ){
            m_Rank3Score.draw(c);
        }
        if( m_Rank4Score != null ){
            m_Rank4Score.draw(c);
        }
        if( m_Rank5Score != null ){
            m_Rank5Score.draw(c);
        }
        if( m_Rank6Score != null ){
            m_Rank6Score.draw(c);
        }
        if( m_Rank7Score != null ){
            m_Rank7Score.draw(c);
        }
        if( m_Rank8Score != null ){
            m_Rank8Score.draw(c);
        }
        if( m_Rank9Score != null ){
            m_Rank9Score.draw(c);
        }
        if( m_Rank10Score != null ){
            m_Rank10Score.draw(c);
        }
        if( m_Rank11Score != null ){
            m_Rank11Score.draw(c);
        }
        if( m_Rank12Score != null ){
            m_Rank12Score.draw(c);
        }
        if( m_Rank13Score != null ){
            m_Rank13Score.draw(c);
        }
        if( m_Rank14Score != null ){
            m_Rank14Score.draw(c);
        }
    }

    @Override
    // touch event
    public void    touch(MotionEvent event){
        if( getTouchable() ) {
            if (m_MenuGroup != null) {
                m_MenuGroup.touch(event);
            }
        }
    }
}