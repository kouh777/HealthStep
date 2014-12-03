package applewatch.apple_watch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;

import java.util.List;
import java.util.Vector;

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

    static final String BR = System.getProperty("line.separator");

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

    // draw stroke
    public void draw_stroke( Canvas c, float stroke_width, int color ){
        if( !m_bDisplay && m_CharBuffer != null) {
            Paint paint = new Paint(m_Paint);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(stroke_width);
            paint.setColor(color);
            c.drawText(String.valueOf(m_CharBuffer), m_iPosX, m_iPosY, paint);
        }
    }

    // draw from right egde
    public void align_right( final int rx ){
        m_iPosX = rx - ( (int)( m_Paint.getTextSize() *  extra_legnth(m_CharBuffer)) ) ;
    }

    // check char whether half or all
    public float extra_legnth( char[] char_buffer ){
        float ex_len = 0;
        for( int i = 0; i < char_buffer.length ; i++ ){
            if( Character.isLetterOrDigit(char_buffer[i]) ){
                ex_len += 0.5f;
            }else{
                ex_len += 1.0f;
            }
        }
        return ex_len;
    }

    // separate draw
    public void multiline_draw( Canvas c, int box_width , int line_height ){
        if( !m_bDisplay && m_CharBuffer != null) {
            int w = (int)( box_width * m_GameView.getGamePerWidth() );
            int text_size = (int)m_Paint.getTextSize();
            int NumOfCharInRow = (int)(w / text_size );
            char[] l = new char[NumOfCharInRow];
            int lines = m_CharBuffer.length / NumOfCharInRow + 1;
            for(int j = 0; j < lines ; j++){
                for(int i=0; i < NumOfCharInRow; i++) {
                    if( NumOfCharInRow*j + i < m_CharBuffer.length  ) {
                        l[i] = m_CharBuffer[i + j * NumOfCharInRow];
                    }else{
                        l[i] = '\0';
                    }
                }
                c.drawText( String.valueOf(l), m_iPosX,m_iPosY + j * text_size + j * line_height, m_Paint );
            }
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

    //set font-family using normal font
    public void setFamily(Typeface family, int style){
        Typeface type_face = Typeface.create( family, style );
        m_Paint.setTypeface( type_face );
    }
    // set font-family using original font
    public void setFamily( String tff ){
        Typeface type_face = Typeface.createFromAsset( m_GameView.getContext().getAssets(), tff);
        m_Paint.setTypeface( type_face );
    }
    // set fill Style
   public void setFillStyle(){
       m_Paint.setStyle(Style.FILL);
   }
   public void setFillAndStrokeStyle(){
       m_Paint.setStyle(Style.FILL_AND_STROKE);
   }
   // set fill Style
   public void setStrokeStyle(){
       m_Paint.setStyle(Style.STROKE);
   }

    // set text stroke
    public void setStrokeWidth( float width ){
        m_Paint.setStrokeWidth(width);
    }

    // presets likely css class
    // detail list texts decoration
    public void setUiPreset(){
        setTextSize(30);
        setFamily("HGRPP1.TTC");
        setColor(255,255,255,255);
        setShadow(3.f,0,0,Color.argb(255,194,81,114));
    }

    public void setDetailListPreset(){
        setTextSize(24);
        m_Paint.setARGB(255,0,0,0);
        setShadow(2,0,0,Color.argb( 255,255,255,255) );
    }
    // detail comment text decoration
    public void setDetailCommentPreset(){

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
