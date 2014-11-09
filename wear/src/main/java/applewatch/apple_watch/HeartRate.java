package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/10.
 */

 import android.graphics.Canvas;
 import android.graphics.drawable.BitmapDrawable;
 import android.graphics.Matrix;

public class HeartRate {
    int mTick;
    int mX;     //  X 座標
    int mY;     //  Y 座標

    int mOffsetX;       //  X オフセット
    int mOffsetY;       //  Y オフセット

    BitmapDrawable mTex = null;

    Matrix  mMatrix;

    //  定数関連
    static final int HEARTRATE_WIDTH    = 49;
    static final int HEARTRATE_HEIGHT   = 15;
    static final int TEXTURE_WIDTH  = 49;
    static final int TEXTURE_HEIGHT = 15;
    //  static final int ANIMATION_CYCLE    = 120;  //  120フレームで一周とする。
    int mWidth  = HEARTRATE_WIDTH;
    int mHeight = HEARTRATE_HEIGHT;

    //  スレッド間同期用オブジェクト
    Object    mObj = new Object();

    public HeartRate(int x, int y, BitmapDrawable tex){
        mX = x;
        mY = y;
        mTex = tex;
        mMatrix = new Matrix();
    }

    public void draw(Canvas c){
        int texWidth, texHeight;
        int dispX, dispY;
        int frame = 0;
        texWidth = mTex.getIntrinsicWidth();
        texHeight = mTex.getIntrinsicHeight();
        mWidth  = (HEARTRATE_WIDTH  * texWidth)  / TEXTURE_WIDTH;
        mHeight = (HEARTRATE_HEIGHT * texHeight) / TEXTURE_HEIGHT;

        //  排他処理区間
        synchronized(mObj){
            dispX = mX;
            dispY = mY;

            dispX -= (frame * HEARTRATE_WIDTH * texWidth) / TEXTURE_WIDTH;
            c.save();  //  キャンバスのクリッピングを保存
            float cx, cy, rad2;
            rad2 = mWidth * 0.5f;
            cx = mX + rad2;  //  中心点
            cy = mY + rad2;  //    〃
            c.setMatrix(mMatrix);
            c.clipRect(mX, mY, mX + mWidth, mY + mHeight);
            mTex.setBounds(dispX,dispY,dispX + texWidth, dispY + texHeight);
            mTex.draw(c);
            mMatrix.reset();
            c.setMatrix(mMatrix);
            c.restore();  //  キャンバスのクリッピングを戻す
        }
    }
}