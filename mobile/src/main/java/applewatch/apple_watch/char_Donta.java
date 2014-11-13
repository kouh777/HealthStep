package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by KOUHO on 2014/10/28.
 */
public class char_Donta extends CharacterBase {

    public char_Donta(GameView gv, int posX, int posY, float scale, boolean anim_flg){
        super( gv, posX, posY, scale, anim_flg);
        m_CharacterKind = CharacterKind.KIND_BEAST;
        m_CharacterID = menu_Character.CHAR_DONTA_ID;

        m_CharResource.addElement( (BitmapDrawable)gv.getResources().getDrawable(R.drawable.char301_donta1) );
        // if anim_flg == false , don't load those image files
        if(anim_flg) {
            m_CharResource.addElement((BitmapDrawable) gv.getResources().getDrawable(R.drawable.char301_donta2));
        }
        m_CharNowImage = m_CharResource.elementAt(0);

        //　image size
        m_iCharWidth  = (int)((m_CharNowImage.getIntrinsicWidth()) * scale);
        m_iCharHeight = (int)((m_CharNowImage.getIntrinsicHeight()) * scale);

        //　image resize
        m_iCharWidth 	 *= gv.getGamePerWidth();
        m_iCharHeight  *= gv.getGamePerHeight();

        m_iPosX *= gv.getGamePerWidth();
        m_iPosY *= gv.getGamePerHeight();
    }

    public void draw(Canvas c) { super.draw(c); }
    public void doAnim(){ super.doAnim(); }
    public int characterID(){ return super.characterID(); }
}
