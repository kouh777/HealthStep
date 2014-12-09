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
    private boolean        bActive;
    private Thread          mThread = null;
    private SurfaceHolder   mSurfaceHolder = null;
    private Paint           mPaint = null;
    private int            mInt = 0;
//    static final  Object m_Obj = new Object();

    private Rect    mRect = new Rect();

    // size of display
    private int m_iScreenWidth;
    private int m_iScreenHeight;

    // a field for resizing screen
    private float m_fGamePerWidth;
    private float m_fGamePerHeight;

    // game back ground.this image size is base of screen size
    BitmapDrawable m_GameScr = null;

    private boolean m_bSceneFlag;

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
        if(!m_bSceneFlag) {
            new scene_WearTotal(this, 30);
            m_bSceneFlag = true;
        }
        TaskManager.getInstance().update();
    }

    //
    //  画面の再描画
    //
    public void    doDraw(Canvas c){
        //  fill white
        mPaint.setARGB(255, 255, 255, 255);
        c.drawRect(0,0,c.getWidth(),getHeight(),mPaint);

        // draw tasks
        TaskManager.getInstance().draw(c);

        //  draw test
        mPaint.setARGB(255, 255, 255, 255);
        c.drawText(String.format("%d", mInt), 10, 10, mPaint);

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
        m_bSceneFlag = false;

        m_GameScr = (BitmapDrawable)context.getResources().getDrawable(R.drawable.m_wear_screen);

        // check resource kind
        if( !(m_GameScr instanceof BitmapDrawable) ){
            m_GameScr = null;
        }
    }
    //
    //  ウィンドウがフォーカスを得た／失った
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        Log.d("GameView","onWindowFocusChanged");
    }

    //
    //  Surface の大きさが変わった際に呼び出される
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mRect.top = 0;
        mRect.left = 0;
        mRect.bottom = getHeight();
        mRect.right = getWidth();
        Log.d("GameView","surfaceChanged");
    }

    //  サーフェイスの生成が完了した際コールバックされる
    public void surfaceCreated(SurfaceHolder holder) {
        //  Thread をここで開始
        bActive = true;

        // get surface size
        m_iScreenWidth = getWidth();
        m_iScreenHeight = getHeight();

        // make uint
        int ordWidth  = m_GameScr.getIntrinsicWidth();   //  base image width
        int ordHeight = m_GameScr.getIntrinsicHeight();  //  base image height
        m_fGamePerWidth = (float)getWidth() / ordWidth;		//  per width
        m_fGamePerHeight = (float)getHeight() / ordHeight;	//  per height

        //  Touch イベントに対応
        setClickable(true);

        mThread = new Thread(this);
        mThread.start();

        Log.d("GameView","surfaceCreated");
    }

    //
    //  Surface が破棄され、使用不能になると呼び出される
    //
    public void surfaceDestroyed(SurfaceHolder holder) {
        //  操作不能にし、Thread を終了させる
        bActive = false;
        mThread = null;
        Log.d("GameView","surfaceDestroyed");
    }


    //
    //  Touch イベントの処理
    public boolean onTouchEvent(MotionEvent event) {
        TaskManager.getInstance().touch(event);
        return super.onTouchEvent(event);
    }

    // getter
    public int getGameWidth(){
        return m_iScreenWidth;
    }
    public int getGameHeight(){
        return m_iScreenHeight;
    }
    public float getGamePerWidth(){
        return m_fGamePerWidth;
    }
    public float getGamePerHeight(){
        return m_fGamePerHeight;
    }
}
