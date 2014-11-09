package applewatch.apple_watch;

/**
 * Created by KOUHO on 2014/10/16.
 */

// use a design pattern 'singleton'
public class PlayerData{
    private int SelectCharacter;
    private boolean[] UnlockCharacter;

    private int m_iPrefecture;
    private int m_iWalkingCount;
    private int m_iWalkingDistance;
    private int m_iGachaTicket;

    // use "singleton" design pattern
    private static final PlayerData instance = new PlayerData();
    // constructor
    private PlayerData(){
        UnlockCharacter = new boolean[20];
    }
    public static PlayerData getInstance(){
        return instance;
    }

    // set method
    public void setSelectCharacter(int select_character){
        SelectCharacter = select_character;
    }
    public void setPrefecture(int prefectrure){
        m_iPrefecture = prefectrure;
    }
    public void setWalkingCount(int walking_count){ m_iWalkingCount = walking_count;}
    public void setWalkingDistance(int walking_distance){
        m_iWalkingDistance = walking_distance;
    }
    public void setGachaTicket(int gacha_ticket){
        m_iGachaTicket = gacha_ticket;
    }
    public void setUnlockCharacter(int char_num, boolean unlockflg){ UnlockCharacter[char_num] =  unlockflg;}

    // add method
    public void addWalkingCount(){++m_iWalkingCount;}

    // check complete
    public boolean CharacterComplete(){
        int length = UnlockCharacter.length;
        int num = 0;    // number of unlock character
        for(int i=0;i < length ; ++i){
            if(UnlockCharacter[i] != false){
                num++;
                if(num == length){
                    return true;
                }
            }else{
                break;
            }
        }
        return false;
    }

    // getter
    public int getSelectCharacter(int select_character){
        return SelectCharacter;
    }
    public int getPrefacture(){
        return m_iPrefecture;
    }
    public int getWalkingCount(){
        return m_iWalkingCount;
    }
    public int getWalkingDistance(){
        return m_iWalkingDistance;
    }
    public boolean[] getUnlockCharacter(){ return UnlockCharacter;}
}