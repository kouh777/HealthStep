package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class btn_Character implements Buttons {

    private GameView m_GameView;
    private scene_Gallery m_sceneGallery;

    private BitmapDrawable m_btnBackGround;
    // for now select image
    private BitmapDrawable m_SelectBorder;
    private BitmapDrawable m_SelectScreen;
    private BitmapDrawable m_SelectLogo;
    // select image flag
    private boolean m_bIsSelected;

    // character number
    private int m_CharacterNumber;

    private int m_iBtnWidth;
    private int m_iBtnHeight;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bIsTouched;

    private CharacterBase m_CharacterBase;

    // for animation
    private int m_iTimer;
    private boolean m_bBlink;

    // define animation
    private final int BLINK_SPAN = 10;

    public btn_Character(GameView gv, scene_Gallery sg,int char_num, boolean found,int posX, int posY){
        m_GameView = gv;
        m_sceneGallery = sg;

        m_iPosX = posX;
        m_iPosY = posY;
        m_bIsSelected = false;  // check select
        m_bBlink = false;       // blink select logo
        m_CharacterNumber = char_num;

        // load background image resources
        m_btnBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.boy_waku);

        // load select image resource
        m_SelectBorder = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.sentaku1);
        m_SelectScreen = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.sentaku2);
        m_SelectLogo = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.sentaku3);

        // for centering character image
        int w = (int)( m_btnBackGround.getIntrinsicWidth() *  menu_Character.SCALE_INBUTTON);
        int h = (int)( m_btnBackGround.getIntrinsicHeight() *  menu_Character.SCALE_INBUTTON);

        int ww = (int)( m_btnBackGround.getIntrinsicWidth() *  menu_Character.SCALE_BUTTON);
        int hh =  (int)( m_btnBackGround.getIntrinsicHeight() *  menu_Character.SCALE_BUTTON);

        int cx = (ww-w) >> 1;
        int cy = (hh-h) >> 1;

        // check whether player have character or don't have character
        if(found){
            switch (m_CharacterNumber) {
                //
                case 0: m_CharacterBase = new char_Akemi(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 1: m_CharacterBase = new char_Urara(gv,posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 2: m_CharacterBase = new char_Sizuku(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 3: m_CharacterBase = new char_Nekoko(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 4: m_CharacterBase = new char_Miki(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 5: m_CharacterBase = new char_Momoka(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 6: m_CharacterBase = new char_Rimika(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 7: m_CharacterBase = new char_Rin(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 8: m_CharacterBase = new char_Yukito(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 9: m_CharacterBase = new char_Kaito(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 10: m_CharacterBase = new char_Syu(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 11: m_CharacterBase = new char_Shouta(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 12: m_CharacterBase = new char_Banjyo(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 13: m_CharacterBase = new char_Hibiki(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 14: m_CharacterBase = new char_Huyuki(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 15: m_CharacterBase = new char_Rokurou(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 16: m_CharacterBase = new char_Konsuke(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 17: m_CharacterBase = new char_Donta(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 18: m_CharacterBase = new char_Minmi(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 19: m_CharacterBase = new char_Ryu(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                default: m_CharacterBase = new char_Unknown(gv, posX+cx, posY+cy, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
            }

            //　image size
            m_iBtnWidth  = (int)((m_btnBackGround.getIntrinsicWidth() ) * menu_Character.SCALE_BUTTON);
            m_iBtnHeight = (int)((m_btnBackGround.getIntrinsicHeight() ) * menu_Character.SCALE_BUTTON);

            //　image resize
            m_iBtnWidth *= gv.getGamePerWidth();
            m_iBtnHeight *= gv.getGamePerHeight();

            m_iPosX *= gv.getGamePerWidth();
            m_iPosY *= gv.getGamePerHeight();

            // cahnge border
            switch( m_CharacterBase.getCharacterKind() ){
                case KIND_BOY:
                    m_btnBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.boy_waku);
                    break;

                case KIND_GIRL:
                    m_btnBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.girl_waku);
                    break;

                case KIND_BEAST:
                    m_btnBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.beast_waku);
                    break;

                default:
                    m_btnBackGround = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.unknown_waku);
                    break;
            }
        }else{
            m_CharacterBase = new char_Unknown(gv, posX, posY, menu_Character.SCALE_BUTTON, menu_Character.ANIM_NO);
        }
    }
    public void update(){
        m_iTimer++;
        CheckSelect();
        if( m_SelectLogo != null ) {
            if (m_iTimer % BLINK_SPAN == 0) {
                m_bBlink = m_sceneGallery.Switch(m_bBlink);
            }
        }
    }

    // if image is touched, return true.
    @Override
    public boolean isTouched() {
        return m_bIsTouched;
    }
    //
    @Override
    public int nextSceneID() { return 0;}

    @Override
    public void draw(Canvas c) {
        // if select this character
        if(m_bIsSelected){
            // draw select border
            if(m_SelectBorder != null){
                m_SelectBorder.setBounds(m_iPosX, m_iPosY, m_iPosX + m_iBtnWidth, m_iPosY + m_iBtnHeight);
                m_SelectBorder.draw(c);
            }
            // draw buton background
            if (m_btnBackGround != null) {
                m_btnBackGround.setBounds(m_iPosX, m_iPosY, m_iPosX + m_iBtnWidth, m_iPosY + m_iBtnHeight);
                m_btnBackGround.draw(c);
            }
            // draw character
            if (m_CharacterBase != null) {
                m_CharacterBase.draw(c);
            }
            // draw select screen
            if(m_SelectScreen != null){
                m_SelectScreen.setBounds(m_iPosX, m_iPosY, m_iPosX + m_iBtnWidth, m_iPosY + m_iBtnHeight);
                m_SelectScreen.draw(c);
            }
            // draw select logo
            if(m_SelectLogo != null && m_bBlink ){
                m_SelectLogo.setBounds(m_iPosX, m_iPosY, m_iPosX + m_iBtnWidth, m_iPosY + m_iBtnHeight);
                m_SelectLogo.draw(c);
            }
        }
        else{
            if (m_btnBackGround != null) {
                m_btnBackGround.setBounds(m_iPosX, m_iPosY, m_iPosX + m_iBtnWidth, m_iPosY + m_iBtnHeight);
                m_btnBackGround.draw(c);
            }
            if (m_CharacterBase != null) {
                m_CharacterBase.draw(c);
            }
        }
    }

    @Override
    public void touch(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return;
        }else {
            int x = (int)event.getX();
            int y = (int)event.getY();
            if( x > m_iPosX && x < m_iPosX + m_iBtnWidth &&
                    y > m_iPosY && y < m_iPosY + m_iBtnHeight){

                int id = menu_Character.numToID(m_CharacterNumber);
                PlayerData.getInstance().setSelectCharacter(m_CharacterBase.characterID());
                m_bIsTouched = true;
            }else{
                return;
            }
        }
    }

    // this method is only used in this class
    private void CheckSelect(){
        int s = PlayerData.getInstance().getSelectCharacter();
        if( s == menu_Character.numToID(m_CharacterNumber) ){
            m_bIsSelected = true;
        }else{
            m_bIsSelected = false;
        }
    }

    // settter
    public void setIsSelected( boolean select){
        m_bIsSelected = select;
    }

}
