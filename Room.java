import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

interface Drawable {
    void draw(Graphics g);
}

public class Room implements Drawable{

    private Point pos;
    private Room exitEast;
    private Room exitWest;
    private Room exitNorth;
    private Room exitSouth;
    final int size;


    public Room(int x, int y){
        pos = new Point(x,y);
        exitEast = null;
        exitWest = null;
        exitNorth = null;
        exitSouth = null;
        size = 50;
    }
    //collect location
    public Point getPosition(){
        return pos;
    }
    //Open the door
    public void setEastExit(Room r){
        this.exitEast = r;
        r.exitWest = this;
    }

    public void setNorthExit(Room r){
        this.exitNorth = r;
        r.exitSouth = this;
    }

    public void setWestExit(Room r){
        this.exitWest = r;
        r.exitEast = this;
    }

    public void setSouthExit(Room r){
        this.exitSouth = r;
        r.exitNorth = this;
    }

    //checking do the room have a doorexit if not, cannot pass through it
    public boolean hasNorthExit(){
        if(exitNorth == null){
            return false;
        }else 
            return true;
    }

    public boolean hasSouthExit(){
        if(exitSouth == null){
            return false;
        }else
            return true;
    }

    public boolean hasEastExit(){
        if(exitEast == null){
            return false;
        }else
            return true;
    }

    public boolean hasWestExit(){
        if(exitWest == null){
            return false;
        }else
            return true;
    }

    //There has a dooeexit!!
    public Room getNorthExit(){
        return exitNorth;
    }

    public Room getSouthExit(){
        return exitSouth;
    }

    public Room getEastExit(){
        return exitEast;
    }

    public Room getWestExit(){
        return exitWest;
    }

    
public Room[] getAdjacentRooms() {
    // Initialize the ArrayList to store adjacent rooms
    ArrayList<Room> adjacentRoomsList = new ArrayList<>();
    
    // Add adjacent rooms with exits to the list
    if (exitNorth != null) {
        adjacentRoomsList.add(exitNorth);
    }
    if (exitSouth != null) {
        adjacentRoomsList.add(exitSouth);
    }
    if (exitEast != null) {
        adjacentRoomsList.add(exitEast);
    }
    if (exitWest != null) {
        adjacentRoomsList.add(exitWest);
    }
    
    // Convert the ArrayList to an array and return it
    Room[] adjacentRoomsArray = new Room[adjacentRoomsList.size()];
    adjacentRoomsArray = adjacentRoomsList.toArray(adjacentRoomsArray);
    return adjacentRoomsArray;
}

//Draw room and door
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.drawRect(pos.x,pos.y,size,size);
        g.setColor(new Color(29,245,240));
        if (exitEast != null){
            g.drawLine(pos.x+49, pos.y+20, pos.x+49, pos.y+30);
            g.drawLine(pos.x+50, pos.y+20, pos.x+50, pos.y+30);
            //hallway
            g.setColor(Color.RED);
            g.drawLine(pos.x+50, pos.y+20, pos.x+60, pos.y+20);
            g.drawLine(pos.x+50, pos.y+30, pos.x+60, pos.y+30);
            
        }
        if (exitWest != null){
            g.setColor(new Color(29,245,240));
            g.drawLine(pos.x, pos.y+20, pos.x, pos.y+30);
            //hallway
            g.setColor(Color.RED);
            g.drawLine(pos.x, pos.y+20, pos.x-10, pos.y+20);
            g.drawLine(pos.x, pos.y+30, pos.x-10, pos.y+30);
        }
        if (exitNorth != null){
            g.setColor(new Color(29,245,240));
            g.drawLine(pos.x+20, pos.y, pos.x+30, pos.y);
            //hallway
            g.setColor(Color.RED);
            g.drawLine(pos.x+20, pos.y, pos.x+20, pos.y-10);
            g.drawLine(pos.x+30, pos.y, pos.x+30, pos.y-10);
        }
        if (exitSouth != null){
            g.setColor(new Color(29,245,240));
            g.drawLine(pos.x+20, pos.y+50, pos.x+30, pos.y+50);
            //hallway
            g.setColor(Color.RED);
            g.drawLine(pos.x+20, pos.y+50, pos.x+20, pos.y+60);
            g.drawLine(pos.x+30, pos.y+50, pos.x+30, pos.y+60);

        }


    }
    
}
