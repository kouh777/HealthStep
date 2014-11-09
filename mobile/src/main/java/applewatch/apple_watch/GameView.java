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
    private GamePart m_GamePart = null;

    // define scene names
    private int m_iPhase = 0;           // control game scene
    private int m_iNowSceneID = 0;     // new GamePart's secondary class one time
//    private boolean m_bSceneChangeFlg = false;

    static final int m_SceneInit = 1;
    static final int m_SceneTitle = 10;
    static final int m_SceneMenu = 20;
    static final int m_SceneRanking = 30;
    static final int m_SceneGallery = 40;
    static final int m_SceneSelect = 50;
    static final int m_SceneGacha = 60;

    // size of display
    private int m_iScreenWidth;
    private int m_iScreenHeight;

    // a field for resizing screen
    private float m_fGamePerWidth;
    private float m_fGamePerHeight;

    // game back ground.this image size is base of screen size
    BitmapDrawable m_GameScr;


    int test = 0;

    public GameView(Context context){
        super(context);

        m_Paint = new Paint();
        SurfaceHolder m_holder = getHolder();
        m_holder.addCallback(this);
        m_SurfaceHolder = m_holder;
        m_bActive = true;
        m_iPhase = m_SceneInit;

        // a resource of background image
        m_GameScr = (BitmapDrawable)context.getResources().getDrawable(R.drawable.bg);


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
        switch(m_iPhase){
            case m_SceneInit:
                m_GamePart = new DummyScene(this);
                m_iPhase = m_GamePart.nextSceneID();
                break;

            case m_SceneTitle:
                if(m_GamePart != null){
                    // new Title only at once
                    if(m_iNowSceneID != m_SceneTitle){
                        m_GamePart = new scene_Title(this);
                        m_iNowSceneID = m_SceneTitle;
                    }
                    // move()
                    if(m_GamePart.move() == false){
                        break;
                    }
                }
                // 次に遷移するページのID
                m_iPhase = m_GamePart.nextSceneID();
                break;

            case m_SceneMenu:
                if(m_GamePart != null){
                    // new Menu only at once
                    if(m_iNowSceneID != m_SceneMenu){
                        m_GamePart = new scene_Menu(this);
                        m_iNowSceneID = m_SceneMenu;
                    }
                    // move()
                    if(m_GamePart.move() == false){
                        break;
                    }
                }
                // 次に遷移するページのID
                m_iPhase = m_GamePart.nextSceneID();
                break;

            case m_SceneRanking:
                if(m_GamePart != null){
                    // new Title only at once
                    if(m_iNowSceneID != m_SceneRanking){
                        m_GamePart = new scene_Ranking(this);
                        m_iNowSceneID = m_SceneRanking;
                    }
                    // move()
                    if(m_GamePart.move() == false){
                        break;
                    }
                }
                // 次に遷移するページのID
                m_iPhase = m_GamePart.nextSceneID();
                break;

            case m_SceneGallery:
                if(m_GamePart != null){
                    // new Galeery only at once
                    if(m_iNowSceneID != m_SceneGallery){
                        m_GamePart = new scene_Gallery(this);
                        m_iNowSceneID = m_SceneGallery;
                    }
                    // move()
                    if(m_GamePart.move() == false){
                        break;
                    }
                }
                // 次に遷移するページのID
                m_iPhase = m_GamePart.nextSceneID();
                break;

            case m_SceneGacha:
                if(m_GamePart != null){
                    // new Gacha only at once
                    if(m_iNowSceneID != m_SceneGacha){
                        m_GamePart = new scene_Gacha(this);
                        m_iNowSceneID = m_SceneGacha;
                    }
                    // move()
                    if(m_GamePart.move() == false){
                        break;
                    }
                }
                // 次に遷移するページのID
                m_iPhase = m_GamePart.nextSceneID();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (m_GamePart != null) {
            m_GamePart.touch(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas c){
        doDraw(c);
    }

    public void doDraw(Canvas c){
        if(m_GamePart != null){
            m_GamePart.draw(c);
        }
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
