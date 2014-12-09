package applewatch.apple_watch;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by KOUHO on 2014/12/05.
 */
public class SoundManager {

    private Map<Object, MediaPlayer> media_map;
    private int m_StreamType;
    private Context m_Context;

    public SoundManager( Context context ){
        media_map = new HashMap<Object, MediaPlayer>();
        m_StreamType = AudioManager.STREAM_MUSIC;
        m_Context = context;
    }

    public void setStreamType( int type ){
        m_StreamType = type;
    }

    private MediaPlayer init( MediaPlayer player ){
        player.setAudioStreamType(m_StreamType);
        return player;
    }

    private MediaPlayer setLoop( MediaPlayer player ){
        player.setLooping(true);
        return player;
    }

    public boolean load( Object id, Uri uri ){
        unload(id);
        try{
            MediaPlayer player = init( MediaPlayer.create( m_Context, uri ) );
            media_map.put(id, player);
        } catch( Exception e ){
            Log.d("SoundManager::load()",e.toString());
            return false;
        }
        return false;
    }

    public boolean loadFromRaw( Object id , int raw ){
        unload(id);
        try{
            MediaPlayer player = init( MediaPlayer.create( m_Context , raw) );
            media_map.put(id,player);
            return true;
        }catch( Exception e ){
            Log.d("SoundManager::loadFromRaw()",e.toString());
            return false;
        }
    }

    public void unload( Object id ){
        MediaPlayer player = media_map.get(id);
        if( player != null ){
            release( player );
            media_map.remove(id);
        }
    }

    public void play( Object id ){
        try{
            MediaPlayer player = media_map.get(id);
            if( player != null ){
                if( player.isPlaying() ){
                    player.seekTo(0);
                }else{
                    player.start();
                }
            }
        }catch( Exception e ){
            Log.d("SoundManager::play()",e.toString());
        }
    }

    public void play( Object id , boolean loop){
        try{
            MediaPlayer player = media_map.get(id);
            if( player != null ){
                if( loop ) {
                    setLoop(player);
                }
                if( player.isPlaying() ){
                    player.seekTo(0);
                }else{
                    player.start();
                }
            }
        }catch( Exception e ){
            Log.d("SoundManager::play()",e.toString());
        }
    }

    public void release( MediaPlayer player ){
        try{
            if( player.isPlaying() ){
                player.stop();
            }
            player.release();
        } catch( Exception e ){
            Log.d("SoundManager::release()",e.toString());
        }
    }

    public boolean isLoaded( Object id ){
        return media_map.get(id) != null;
    }

    public void dispose(){
        Iterator<Map.Entry<Object,MediaPlayer>> iterator = media_map.entrySet().iterator();
        while( iterator.hasNext() ){
            Map.Entry<Object,MediaPlayer> entry = iterator.next();
            try{
                release(entry.getValue());
            }catch(Exception e){
                Log.d("SoundManager::dispose()",e.toString());
            }
            iterator.remove();
        }
    }

}
