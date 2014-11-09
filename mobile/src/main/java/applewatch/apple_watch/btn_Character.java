package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by KOUHO on 2014/10/17.
 */
public class btn_Character implements Button{
    private GameView m_GameView;

    private BitmapDrawable m_btn_Resource;

    private int m_iBtnWidth;
    private int m_iBtnHeight;
    private int m_iNextSceneID;

    private int m_iPosX;
    private int m_iPosY;
    private boolean m_bIsTouched;

    private CharacterBase m_CharacterBase;

    public btn_Character(GameView gv, int char_num, boolean found,int posX, int posY){
        m_GameView = gv;
        m_iPosX = posX;
        m_iPosY = posY;
        m_iNextSceneID = m_GameView.m_SceneMenu;

        // load background image resources
        m_btn_Resource = (BitmapDrawable)gv.getResources().getDrawable(R.drawable.border);

        // check whether player have character or don't have character
        if(found){
            switch (char_num) {
                //
                case 0: m_CharacterBase = new char_Akemi(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 1: m_CharacterBase = new char_Urara(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 2: m_CharacterBase = new char_Sizuku(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 3: m_CharacterBase = new char_Nekoko(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 4: m_CharacterBase = new char_Miki(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 5: m_CharacterBase = new char_Momoka(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 6: m_CharacterBase = new char_Rimika(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 7: m_CharacterBase = new char_Rin(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 8: m_CharacterBase = new char_Yukito(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 9: m_CharacterBase = new char_Kaito(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 10: m_CharacterBase = new char_Syu(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 11: m_CharacterBase = new char_Shouta(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 12: m_CharacterBase = new char_Banjyo(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 13: m_CharacterBase = new char_Hibiki(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 14: m_CharacterBase = new char_Huyuki(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 15: m_CharacterBase = new char_Rokurou(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 16: m_CharacterBase = new char_Konsuke(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 17: m_CharacterBase = new char_Donta(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 18: m_CharacterBase = new char_Minmi(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                case 19: m_CharacterBase = new char_Ryu(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
                default: m_CharacterBase = new char_Unknown(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO); break;
            }

            //　image size
            m_iBtnWidth  = (int)((m_btn_Resource.getIntrinsicWidth() >> 1) * menu_Character.SCALE_BUTTON);
            m_iBtnHeight = (int)((m_btn_Resource.getIntrinsicHeight() >> 1) * menu_Character.SCALE_BUTTON);

            //　image resize
            m_iBtnWidth *= gv.getGamePerWidth();
            m_iBtnHeight *= gv.getGamePerHeight();

            m_iPosX *= gv.getGamePerWidth();
            m_iPosY *= gv.getGamePerHeight();

        }else{
            m_CharacterBase = new char_Unknown(gv, posX, posY, menu_Character.SCALE_INBUTTON, menu_Character.ANIM_NO);
        }
    }

    // if image is touched, return true.
    @Override
    public boolean isTouched() {
        return m_bIsTouched;
    }
    //
    @Override
    public int nextSceneID() {
        return m_iNextSceneID;
    }

    @Override
    public void draw(Canvas c) {
        if (m_btn_Resource != null){
            m_btn_Resource.setBounds(m_iPosX, m_iPosY, m_iPosX+m_iBtnWidth, m_iPosY+m_iBtnHeight);
//            m_btn_Resource.draw(c);
        }
        if(m_CharacterBase != null){
            m_CharacterBase.draw(c);
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
                Log.d("TEST", "Touch image inside");

                // if button touch change select character
                PlayerData.getInstance().setSelectCharacter(m_CharacterBase.characterID());
                m_bIsTouched = true;
            }else{
                Log.d("TEST", "Touch image outside");
                return;
            }
        }
    }
}
