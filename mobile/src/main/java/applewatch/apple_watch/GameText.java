package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by KOUHO on 2014/11/22.
 */
// Text Base Class
public class GameText {

    protected GameView m_GameView;

    protected int m_iPosX;               // position X
    protected int m_iPosY;               // position Y

    protected Paint m_Paint;             // paint
    protected char[] m_CharBuffer;     // drawing char[]

    protected boolean m_bDisplay;       // display f;ag
    protected int m_iTextIndex;
    protected boolean m_bType;          // typing animation flag

    // define default text size
    protected final int DEFAULT_TEXT_SIZE = 30;

    // main constructor
    public GameText( GameView gv, char[] char_buffer, int posX, int posY ){
        m_iPosX = (int)( posX * gv.getGamePerWidth() );
        m_iPosY = (int)( posY * gv.getGamePerHeight() );
        m_CharBuffer = new char[char_buffer.length];
        m_CharBuffer = char_buffer;

        m_bDisplay = false;
        m_bType = false;

        // set default paint
        m_Paint = new Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setColor(Color.BLACK);
        m_Paint.setTextSize(DEFAULT_TEXT_SIZE);
    }

    // constructor : text is empty
    public GameText( GameView gv, int buffer_size, int posX, int posY ){
        m_iPosX = (int)( posX * gv.getGamePerWidth() );
        m_iPosY = (int)( posY * gv.getGamePerHeight() );
        m_CharBuffer = new char[buffer_size];

        m_bDisplay = false;
        m_bType = false;

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

    // set position
    public void setX( int x ){ m_iPosX = x; }
    public void setY( int y ){ m_iPosY = y; }
    public void setType( boolean type_flg ){ m_bType = type_flg; }

    // gettter
    public int getX(){ return m_iPosX; }
    public int getY(){ return m_iPosY; }
    public char[] getCharBuffer(){ return m_CharBuffer; }
    public boolean getType(){ return m_bType; }
}
