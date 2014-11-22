package applewatch.apple_watch;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;

/**
 * Created by KOUHO on 2014/11/22.
 */
enum SoundKind{
    SOUND_SE,
    SOUND_VOICE,
    SOUND_BGM
}
// sound total class
public class GameSound {

    //---------------
    // Common
    //---------------
    private SoundKind m_SoundKind;  // kind of sound
    private boolean m_bPlay;

    //---------------
    // SE
    //---------------
    private SoundPool m_SoundPool;
    private int m_iSoundID;

    //---------------
    // Voice and BGM
    //---------------
    private MediaPlayer m_MediaPlayer;
    private boolean m_bPlayEnd;

    // constructor
    public GameSound( SoundKind kind, GameView gv, int raw ){
        m_SoundKind = kind;
        m_bPlayEnd = false;
        m_bPlay = false;

        // voice and se
        if( kind == SoundKind.SOUND_SE ) {
            m_SoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            m_iSoundID = m_SoundPool.load(gv.getContext(), raw, 0);
            m_SoundPool.stop(m_iSoundID);
        }

        // voice
        if( kind == SoundKind.SOUND_VOICE ){
            m_MediaPlayer = MediaPlayer.create(gv.getContext(), raw);
            m_MediaPlayer.stop();
            try{
                m_MediaPlayer.prepare();
            }catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // bgm
        if( kind == SoundKind.SOUND_BGM ){
            m_MediaPlayer = MediaPlayer.create(gv.getContext(), raw);
            m_MediaPlayer.setLooping(true);
        }
    }

    // release
    public void release(){

        if( m_SoundPool!= null ) {
            m_SoundPool.release();
        }

        if( m_MediaPlayer != null ){
            m_MediaPlayer.release();
        }
    }

    // play
    public void play(){
        if( !m_bPlay ) {

            if (m_SoundPool != null) {
                m_SoundPool.play(m_iSoundID, 1.0F, 1.0F, 0, 0, 1.0F);
                m_bPlay = true;
            }

            if (m_MediaPlayer != null) {
                m_MediaPlayer.start();
                m_bPlay = true;
            }
        }
    }

    // pause
    public void pause(){
        if( m_SoundPool != null ) {
            m_SoundPool.autoPause();
        }
        if( m_MediaPlayer != null){
            m_MediaPlayer.pause();
        }
    }

    public boolean getIsPlaying(){
        if( m_MediaPlayer != null ){
            return m_MediaPlayer.isPlaying();
        } else {
            return false;
        }
    }

    /*
    public boolean play_end(){
        if( m_MediaPlayer != null ) {
            int i = m_MediaPlayer.getCurrentPosition();
            int j = m_MediaPlayer.getDuration();
            if (i >= j) {
                m_MediaPlayer.release();
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }
    */
}
