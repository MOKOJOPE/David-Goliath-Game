import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.Objects;

interface Drawable {
    void draw(Graphics g);
}

public abstract class Sprite implements Drawable{
    protected Room currentRoom;
    protected ImageIcon image;


    public Sprite(){
        currentRoom = null;
        image = null;

    }

    //setter method setting the location
    public void setCurrentRoom(Room r){
        this.currentRoom = r;
    }

    //getter method getting the location
    public Room getCurrentRoom(){
        return currentRoom;
    }
//making direction position
    public void moveSouth(){
        if(currentRoom.hasSouthExit()){
            currentRoom = currentRoom.getSouthExit();
        }
    }

    public void moveNorth(){
        if(currentRoom.hasNorthExit()){
            currentRoom = currentRoom.getNorthExit();
        }
    }

    public void moveEast(){
        if(currentRoom.hasEastExit()){
            currentRoom = currentRoom.getEastExit();
        }
    }

    public void moveWest(){
        if(currentRoom.hasWestExit()){
            currentRoom = currentRoom.getWestExit();
        }
    }

    // Override the equals method in the Sprite class so that two sprites are equal iff they are in the same room. Use your newly-created equals method when you check if David touches a stone, or meets Goliath.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sprite otherSprite = (Sprite) obj;
        return Objects.equals(this.getCurrentRoom(), otherSprite.getCurrentRoom());
    }


    //draw the image with specific location
    public void draw(Graphics g){
        if (image != null){
            image.paintIcon(null,g,currentRoom.getPosition().x+5, currentRoom.getPosition().y+8);
        }
    }
}
