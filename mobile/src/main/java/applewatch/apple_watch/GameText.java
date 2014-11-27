package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by KOUHO on 2014/11/22.
 */
// Text Base Class
public class GameText {

    protected GameView m_GameView;

    protected int m_iPosX;               // position X
    protected int m_iPosY;               // position Y

    protected int m_iWidth;             // game text width
    protected int m_iHeight;            // game text height

    protected Paint m_Paint;             // paint
    protected char[] m_CharBuffer;     // drawing char[]

    protected boolean m_bDisplay;       // display flag

    // relation to typing text
    protected int m_iIndex;             // typing text index
    protected boolean m_bType;          // typing animation flag

    // relation to wait
    protected int m_iTimer;
    protected boolean m_bWait;

    // define default text size
    protected final int DEFAULT_TEXT_SIZE = 30;

    // main constructor
    public GameText( GameView gv, char[] char_buffer, int posX, int posY ){
        m_GameView = gv;
        m_iPosX = (int)( posX * gv.getGamePerWidth() );
        m_iPosY = (int)( posY * gv.getGamePerHeight() );
        m_CharBuffer = new char[char_buffer.length];
        m_CharBuffer = char_buffer;

        m_iWidth = 0;
        m_iHeight = 0;

        m_iIndex = 0;
        m_bDisplay = false;
        m_bType = false;
        m_iTimer = 0;
        m_bWait = false;

        // set default paint
        m_Paint = new Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setColor(Color.BLACK);
        m_Paint.setTextSize(DEFAULT_TEXT_SIZE);
    }

    // constructor : text is empty
    public GameText( GameView gv, int buffer_size, int posX, int posY ){
        m_GameView = gv;
        m_iPosX = (int)( posX * gv.getGamePerWidth() );
        m_iPosY = (int)( posY * gv.getGamePerHeight() );
        m_CharBuffer = new char[buffer_size];

        m_iWidth = 0;
        m_iHeight = 0;

        m_iIndex = 0;
        m_bDisplay = false;
        m_bType = false;
        m_iTimer = 0;
        m_bWait = false;

        // set default paint
        m_Paint = new Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setColor(Color.BLACK);
        m_Paint.setTextSize(DEFAULT_TEXT_SIZE);
    }

    // update
    public void update(){
    }

    // draw
    public void draw( Canvas c ){
        if( !m_bDisplay && m_CharBuffer != null) {
            c.drawText(String.valueOf(m_CharBuffer), m_iPosX, m_iPosY, m_Paint);
        }
    }

    // this method can be used in update method
    public void type_anim( String type_str ){
        if( m_iIndex >= type_str.length() ){
            m_bType = false;
        }
        if( m_bType && !m_bWait ){
            setText( type_str, m_iIndex );
            m_iIndex++;
        }
    }

    // animation wait
    public void add_wait( int wait_time ){
        if( m_iTimer++ >= wait_time ){
            m_bWait = false;
            m_iTimer = 0;
        }
    }

    // return char byte
    public int checkByte( char ch ){
        if( String.valueOf(ch).getBytes().length < 2 ){
            return 1;    // single byte
        }else{
            return 2;    // double byte
        }
    }

    // set text Argument:String str
    public void setText(String str){ m_CharBuffer = str.toCharArray();}

    // set text Argument:String ,int
    public void setText( String str, int index ){
        m_CharBuffer[index] = str.charAt(index);
    }

    // set text Argument:char[], int
    public void setText( char[] str, int index ){
        m_CharBuffer[index] = str[index];
    }

    // set text Argument:char[], int
    public void setText( char chr, int index ){
        m_CharBuffer[index] = chr;
    }

    // set setAntiAlias
    public void setAntiAlias( boolean antialias_flg ){
        m_Paint.setAntiAlias(antialias_flg);
    }

    // set color
    public void setColor( int color ){
       m_Paint.setColor(color);
    }

    // set color
    public void setColor( int alpha, int red, int green, int blue ){
        m_Paint.setARGB( alpha, red, green , blue );
    }

    // set font
    public void setTextSize( int size ){
        m_Paint.setTextSize( size );
    }

    // set Alpha
    public void setAlpha( int alpha ){
        m_Paint.setAlpha( alpha );
    }

    // set shadow
    public void setShadow( float radius, float dx, float dy, int color ){
        m_Paint.setShadowLayer( radius, dx, dy , color );
    }

    //set font-family
    public void setFamily(Typeface type_face){
        m_Paint.setTypeface( type_face );
    }

    // set position
    public void setX( int x ){ m_iPosX = x; }
    public void setY( int y ){ m_iPosY = y; }
    public void setType( boolean type_flg ){ m_bType = type_flg; }
    public void setWait( boolean wait_flg ){ m_bWait = wait_flg; }

    // gettter
    public int getX(){ return m_iPosX; }
    public int getY(){ return m_iPosY; }
    public char[] getCharBuffer(){ return m_CharBuffer; }
    public boolean getType(){ return m_bType; }
    public int getWidth(){
        int size = 0;   // one character space
        for( int i=0; m_CharBuffer[i] != '\0' ; ++i ){
            ++size;
        }
        int w = (int)( m_Paint.getTextSize() * size * m_GameView.getGamePerWidth() );
        return w;
    }
}
