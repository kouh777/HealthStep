package applewatch.apple_watch;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    boolean  bActive;
    Thread   mThread = null;
    SurfaceHolder    mSurfaceHolder = null;
    Paint    mPaint = null;
    int      mInt = 0;
    BitmapDrawable mSmile = null;  //
    BitmapDrawable mHeartRate = null;

    Rect    mRect = new Rect();

    HeartRate mHearRateObject = null;

    //
    //  Thread を走らせるための run メソッド
    //
    public void run(){
        while(bActive){
            Canvas c = null;
            doAnim();
            try {
                c = mSurfaceHolder.lockCanvas(null);
                if (c != null){
                    synchronized (mSurfaceHolder) {
                        doDraw(c);
                    }
                }
            } finally {
                //  サーフェイスをロックした後、何らかの理由で例外が発生したとき、
                //  サーフェイスがアンロックされない事態をさけるため、finally にて、
                //  アンロックする。
                if (c != null) {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }
            Thread.yield();
        }
    }
    @Override
    public void draw(Canvas c){
        doDraw(c);
    }
    //
    //  アニメーション
    //
    public void    doAnim(){
        mInt++;
//        mSmileObject.doAnim();
    }

    //
    //  画面の再描画
    //
    public void    doDraw(Canvas c){
        //  塗りつぶし
        mPaint.setARGB(255, 255, 255, 255);
        c.drawRect(0,0,c.getWidth(),getHeight(),mPaint);

//        mSmileObject.draw(c);
        mHearRateObject.draw(c);

        //  文字描画
        mPaint.setARGB(255, 255, 255, 255);
       /*
        c.drawText(String.format("%d", mInt), 10, 10, mPaint);
        */
    }

    //
    //  コンストラクタ
    //
    public GameView(Context context) {
        super(context);
        mPaint = new Paint();
        // サーフェイスに対する変更を受け取るため、holder にコールバックを設定する
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        mSurfaceHolder = holder;
        bActive = true;

        //mSmile = (BitmapDrawable)context.getResources().getDrawable(R.drawable.smile2);
        mHeartRate = (BitmapDrawable)context.getResources().getDrawable(R.drawable.sinpaku);

        //  読み込んだリソースの種類を念のため確認
        if (!(mSmile instanceof BitmapDrawable)){
            mSmile =null;
        }

        if (!(mHeartRate instanceof BitmapDrawable)){
            mHeartRate =null;
        }

        //  Smile オブジェクトの生成
  //      mSmileObject = new Smile(20,20,mSmile);

        mHearRateObject = new HeartRate(20,20,mHeartRate);

    }
    //
    //  ウィンドウがフォーカスを得た／失った
    public void onWindowFocusChanged(boolean hasWindowFocus) {
    }

    //
    //  Surface の大きさが変わった際に呼び出される
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mRect.top = 0;
        mRect.left = 0;
        mRect.bottom = getHeight();
        mRect.right = getWidth();
    }

    //  サーフェイスの生成が完了した際コールバックされる
    public void surfaceCreated(SurfaceHolder holder) {
        //  Thread をここで開始
        bActive = true;

        //  Touch イベントに対応
        setClickable(true);

        mThread = new Thread(this);
        mThread.start();
    }

    //
    //  Surface が破棄され、使用不能になると呼び出される
    //
    public void surfaceDestroyed(SurfaceHolder holder) {
        //  操作不能にし、Thread を終了させる
        bActive = false;
        mThread = null;
    }


    //
    //  Touch イベントの処理
    boolean mSnatch=false;
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /*
                //  つかむ
                if(mSmileObject.snatch(x, y)) {
                    //  Smile をSnatch したら・・・・
                    this.getParent().requestDisallowInterceptTouchEvent(true);  //  ACTION_CANCEL の防止
                    mSnatch = true;
                }
                */
                break;
            case MotionEvent.ACTION_MOVE:
                /*
                //  動かす
                if (mSnatch) {
                    mSmileObject.move(x, y);
                }
                */
                break;
            case MotionEvent.ACTION_UP:
                /*
                //  離す
                if (mSnatch) {
                    mSmileObject.release(x, y);
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                    mSnatch = false;
                }
                */
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.v("warn","ACTION_CANCEL");
                break;
        }
        if (!mSnatch)
            return  super.onTouchEvent(event);
        return  true;
    }


}
