import javax.swing.ImageIcon;

public class David extends Sprite {
    private int numStones;
    
    //save the image resource 
    public David(){
    image = new ImageIcon("david-1.png");
    numStones = 0;
    }

    public void pickUpStone(){
        numStones +=1;
    }

    public boolean isArmed(){
        if (numStones == 12){
            return true;
        }else
            return false;
        }
    
    public void reset(){
        numStones = 0;
    }
}
