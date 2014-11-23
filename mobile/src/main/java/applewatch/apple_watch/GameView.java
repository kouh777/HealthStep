package applewatch.apple_watch;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.util.Log;
import android.graphics.Canvas;
import android.view.MotionEvent;

import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by KOUHO on 2014/10/13.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private Thread m_Thread = null;
    private boolean m_bActive = false;
    private SurfaceHolder m_SurfaceHolder = null;
    private Paint m_Paint = null;

    // game scene
    private int m_Int = 0;

    // size of display
    private int m_iScreenWidth;
    private int m_iScreenHeight;

    // a field for resizing screen
    private float m_fGamePerWidth;
    private float m_fGamePerHeight;

    // game back ground.this image size is base of screen size
    BitmapDrawable m_GameScr;

    // backgroun_music
    private GameSound m_GameBgm;


    private boolean m_bSceneFlg;

    public GameView(Context context){
        super(context);

        m_Paint = new Paint();
        SurfaceHolder m_holder = getHolder();
        m_holder.addCallback(this);
        m_SurfaceHolder = m_holder;
        m_bActive = true;

        // a resource of background image
        m_GameScr = (BitmapDrawable)context.getResources().getDrawable(R.drawable.bg);

        // crete bgm
        m_GameBgm = new GameSound( SoundKind.SOUND_BGM, this, R.raw.bgm_main);
        m_GameBgm.play();

        // creating Google API Client
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.d("test", "onConnected:" + connectionHint);
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.d("test", "onConnecionSuspend:" + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.d("test", "onConnectionFailed:" + result);
                    }
                })
                .addApi(Wearable.API)
                .build();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("TEST", "surfaceChanged");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("TEST", "surfaceCreated");

        // get surface size
        m_iScreenWidth = getWidth();
        m_iScreenHeight = getHeight();

        // make uint
        int ordWidth  = m_GameScr.getIntrinsicWidth();   //  base image width
        int ordHeight = m_GameScr.getIntrinsicHeight();  //  base image height
        m_fGamePerWidth = (float)getWidth() / ordWidth;		//  per width
        m_fGamePerHeight = (float)getHeight() / ordHeight;	//  per height

        setClickable(true); // for getting motion event
        setFocusable(true); // for getting key event
        m_bActive = true;

        m_Thread = new Thread(this);
        m_Thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("TEST", "surfaceDestroyed");
        //  to inoperable and exit
        m_bActive = false;
        m_Thread = null;
    }

    public void doAnim(){
        m_Int++;
        if(!m_bSceneFlg) {
            new scene_BackMain(this,100);
            new scene_Title(this, 30);
            m_bSceneFlg = true;
        }
        TaskManager.getInstance().update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        TaskManager.getInstance().touch(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas c){
        doDraw(c);
    }

    public void doDraw(Canvas c){
        TaskManager.getInstance().draw(c);
        //PutDataRequest mPutDataRequest = new PutDataRequest("/path/to/data");
    }

    // run thread
    @Override
    public void run(){
        while(m_bActive){
            Canvas c = null;
            doAnim();
            try{
                c = m_SurfaceHolder.lockCanvas(null);
                if(c != null){
                    synchronized (m_SurfaceHolder) {
                        doDraw(c);
                    }
                }
            }finally{
                // after lock surface, if exception happened, unlock surface.
                if(c != null){
                    m_SurfaceHolder.unlockCanvasAndPost(c);
                }
            }
            Thread.yield();
        }
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