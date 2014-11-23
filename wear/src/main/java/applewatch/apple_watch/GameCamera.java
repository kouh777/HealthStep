package applewatch.apple_watch;

import java.util.Vector;

/**
 * Created by KOUHO on 2014/11/23.
 */
// camera class
public class GameCamera {

    private int m_iCameraX;         // position X
    private int m_iCameraY;         // position Y
    private boolean m_bCameraMove; //
    private boolean m_bCameraSet;   //
    private Object m_Obj = new Object();    // for synchronize

    public GameCamera(){
        reset();
    }
    /*
    // singleton
    private static GameCamera instance = new GameCamera();
    private GameCamera(){ }
    public static GameCamera getInstance(){ return instance; }
    */


    public void reset(){
        m_iCameraX = 0;
        m_iCameraY = 0;
        m_bCameraMove = false;
        m_bCameraSet = false;
    }

    public void update(){
        // cramp
        // top
        // bottom
    }

    public void setCamera( Vector<GameSprite> game_sprites , int[] initial_y ){
        if( !m_bCameraSet ) {
            synchronized (m_Obj) {
                for (int i = 0; i < game_sprites.size(); i++) {
                    if (game_sprites.get(i) != null) {
//                    game_sprites.get(i).setX( game_sprites.get(i).getX() + m_iCameraX);
                        game_sprites.get(i).setY(initial_y[i] + m_iCameraY);
                    }
                }
            }
        }
    }

    public boolean moveToCamera( int goal_y , int speed){
        synchronized (m_Obj){
            int before_y = m_iCameraY;  // before moving
            if( m_iCameraY < goal_y ) {
                m_iCameraY += speed;
            }
            if( m_iCameraY > goal_y ){
                m_iCameraY -= speed;
            }
            int after_y = m_iCameraY;   // after moving
            // check move end
            if(  before_y != after_y )
                return false;
            else
                return true;
        }
    }

    // setter
    public void setX( int x ){ m_iCameraX = x; }
    public void setY( int y ){ m_iCameraY = y; }

    // getter
    public int getX(){ return m_iCameraX; }
    public int getY(){ return m_iCameraY; }
}
