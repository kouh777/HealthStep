package applewatch.apple_watch;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/12/15.
 */
public class RankGroup extends ObjectGroup{
    private GameView m_GameView;

    private final int GOLD_Y = 230;
    private final int SILVER_Y = 350;
    private final int BROND_Y = 470;
    private final int OTHERS_Y = 580;
    private final double BEST_SY = 0.52;
    private final double OHTERS_SY = 0.52;

    public static enum RankKind{
        BY_PREFECTURE , WITH_IN_PREFECTURE, WHOLE_COUNTRY
    }

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
    private final int RA_NUM_X = 185;     // num position x
    private final int RA_NAME_X = 230;    // name position x
    private final int RA_OH_NUM_X = 235;  // other name positionX
    private final int RA_OH_NAME_X = 240;  // other name positionX
    private final int RA_SC_X = 570;      // score position x
    private final int RA_OH_SC_X = 520;    // other score x

    private final int RA_NO_1 = 335;    // no1 position y
    private final int RA_NO_2 = 455;    // no2 position y
    private final int RA_NO_3 = 575;    // no3 position y

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

    public RankGroup( RankKind kind ,GameView gv ){
        super(gv);

        // sprites
        addSprites( new GameSprite( gv, R.drawable.ranking01_new)  );
        addSprites( new GameSprite( gv, R.drawable.ranking02_new)  );
        addSprites( new GameSprite( gv, R.drawable.ranking03_new)  );
        addSprites( new GameSprite( gv, R.drawable.ranking_others)  );
    }
}
