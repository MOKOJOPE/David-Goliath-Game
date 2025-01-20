
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.Timer;


public class Main extends JPanel implements KeyListener{
    // private ArrayList<Room> momoRooms;
    private Room [][] d1 = new Room[4][4];
    private Room [][] d2 = new Room[2][2];
    private Room [][] d3 = new Room[4][4];
    private David person;
    private ArrayList<Stone> block = new ArrayList<>();
    
    private Goliath people;
    private ArrayList<Drawable> drawables = new ArrayList<>();


    public Main(){
        // momoRooms = new ArrayList<>();
    //setting room position
        for (int i=0; i<4; i++){
            for (int k=0; k<4;k++){
                d1[i][k] = new Room(i*60+40,k*60+40);
                d3[i][k] = new Room (i*60+400,k*60+280);
            }
        }
        for (int i=0; i<2; i++){
            for (int k=0; k<2;k++){
                d2[i][k] = new Room (i*60+280,k*60+220);
            }
        }
        //Left-Right
        d1[0][0].setEastExit(d1[1][0]);
        d1[1][0].setEastExit(d1[2][0]);
        d1[2][1].setEastExit(d1[3][1]);
        d1[0][3].setEastExit(d1[1][3]);
        d1[0][2].setEastExit(d1[1][2]);
        d1[2][2].setWestExit(d1[1][2]);
        d1[2][3].setEastExit(d1[3][3]);
        d1[3][3].setEastExit(d2[0][0]);
        d2[0][0].setEastExit(d2[1][0]);
        d2[0][1].setEastExit(d2[1][1]);
        d2[1][1].setEastExit(d3[0][0]);
        d3[0][0].setEastExit(d3[1][0]);
        d3[1][0].setEastExit(d3[2][0]);
        d3[2][1].setEastExit(d3[3][1]);
        d3[0][3].setEastExit(d3[1][3]);
        d3[0][2].setEastExit(d3[1][2]);
        d3[2][2].setWestExit(d3[1][2]);
        d3[2][3].setEastExit(d3[3][3]);

        //Up-Down
        d1[1][0].setSouthExit(d1[1][1]);
        d1[2][0].setSouthExit(d1[2][1]);
        d1[3][0].setSouthExit(d1[3][1]);
        d1[0][1].setSouthExit(d1[0][2]);
        d1[0][2].setSouthExit(d1[0][3]);
        d1[1][1].setSouthExit(d1[1][2]);
        d1[1][2].setSouthExit(d1[1][3]);
        d1[2][2].setSouthExit(d1[2][3]);
        d1[3][2].setSouthExit(d1[3][3]);
        d2[0][1].setNorthExit(d2[0][0]);
        d2[1][1].setNorthExit(d2[1][0]);
        d3[1][0].setSouthExit(d3[1][1]);
        d3[2][0].setSouthExit(d3[2][1]);
        d3[3][0].setSouthExit(d3[3][1]);
        d3[0][1].setSouthExit(d3[0][2]);
        d3[0][2].setSouthExit(d3[0][3]);
        d3[1][1].setSouthExit(d3[1][2]);
        d3[1][2].setSouthExit(d3[1][3]);
        d3[2][2].setSouthExit(d3[2][3]);
        d3[3][2].setSouthExit(d3[3][3]);
        
        //David location
        person = new David();
        person.setCurrentRoom(d1[0][3]);

        //Stone location

        for (int i=0; i < 13; i++){
            block.add(new Stone());
            
        }
        block.get(0).setCurrentRoom(d1[0][0]);
        block.get(1).setCurrentRoom(d1[1][0]);
        block.get(2).setCurrentRoom(d1[2][1]);
        block.get(3).setCurrentRoom(d1[1][2]);
        block.get(4).setCurrentRoom(d1[3][2]);
        block.get(5).setCurrentRoom(d2[0][0]);
        block.get(6).setCurrentRoom(d2[0][1]);
        block.get(7).setCurrentRoom(d2[1][1]);
        block.get(8).setCurrentRoom(d3[0][0]);
        block.get(9).setCurrentRoom(d3[1][0]);
        block.get(10).setCurrentRoom(d3[2][1]);
        block.get(11).setCurrentRoom(d3[1][2]);
        block.get(12).setCurrentRoom(d3[3][2]);



        //Goliath location
        people = new Goliath();
        people.setCurrentRoom(d2[1][0]);

        //Timer for Goliath's movement
        Timer goliathTimer = new Timer(1000, e -> {
            moveGoliathRandomly();
        });
        goliathTimer.start();

        //KeyListener
        addKeyListener(this);
    }
    //Make the Sound Effect
    public void playSound(String soundFile){
        File f = new File("./" + soundFile);
        try{
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {

    // Make a Background Color
        g.setColor(new Color(29,245,240));
        g.fillRect(0,0,getWidth(), getHeight());
        // g.setColor(Color.RED);

    //Drawing the box
        for (int i=0; i<4; i++){
            for (int k=0; k<4;k++){
                drawables.add(d1[i][k]);
                drawables.add(d3[i][k]);
                // d1[i][k].draw(g);
                // d3[i][k].draw(g);
            }
        }
        
        for (int i=0; i<2; i++){
            for (int k=0; k<2;k++){
            drawables.add(d2[i][k]);
            // d2[i][k].draw(g);
            }
        }
        //draw David
        //person.draw(g);
        drawables.add(person);

        //draw stones from the array
        for (int i=0; i < block.size() - 1; i++){
            block.get(i).draw(g);
            
        }

        //draw Goliath
        //people.draw(g);
        drawables.add(people);
        
        //modify drawing method -> Draw all graphics as "drawables" not as sprites and rooms.
        for (int i=0; i <drawables.size(); i++){
            drawables.get(i).draw(g);
        }

        requestFocusInWindow();
    }

  // movement
    @Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                person.moveNorth();
                break;
            case KeyEvent.VK_DOWN:
                person.moveSouth();
                break;
            case KeyEvent.VK_LEFT:
                person.moveWest();
                break;
            case KeyEvent.VK_RIGHT:
                person.moveEast();
                break;
        }
        
        switch(keyCode){
            case KeyEvent.VK_W:
                people.moveNorth();
                break;
            case KeyEvent.VK_S:
                people.moveSouth();
                break;
            case KeyEvent.VK_A:
                people.moveWest();
                break;
            case KeyEvent.VK_D:
                people.moveEast();
                break;
        }

    
        //David get the stone 
        for (int i = 0; i < block.size(); i++) {
            if (person.getCurrentRoom() == block.get(i).getCurrentRoom()) {
                person.pickUpStone();
                playSound("Retro Game Coin Sound Effect.wav");
                block.get(i).setCurrentRoom(null);
                block.remove(i);
                break;
            }
        }

        // //Win or Lose when David touched Goliath
        // if (person.getCurrentRoom() == people.getCurrentRoom()) {
        //     if (person.isArmed()) {
        //         JOptionPane.showMessageDialog(this, "Congratulations David! You slew Goliath!");
        //         reset();
        //     } else {
        //         JOptionPane.showMessageDialog(this, "Oh no David! Goliath got you! Try again.");
        //         reset();
        //     }
        // }

        // Win or Lose when David touched Goliath
        if (person.getCurrentRoom().equals(people.getCurrentRoom())) {
            if (person.isArmed()) {
                JOptionPane.showMessageDialog(this, "Congratulations David! You slew Goliath!");
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Oh no David! Goliath got you! Try again.");
                reset();
            }
        }


        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){
        
    }

    @Override
    public void keyTyped(KeyEvent e){

    }
    //Goliath move randomly

    private void moveGoliathRandomly() {
        // Get Goliath's current room
        Room currentRoom = people.getCurrentRoom();

        // Get adjacent rooms
        Room[] adjacentRooms = currentRoom.getAdjacentRooms();

        // Choose a random adjacent room
        Random random = new Random();
        Room nextRoom = adjacentRooms[random.nextInt(adjacentRooms.length)];

        // Move Goliath to the chosen room
        people.setCurrentRoom(nextRoom);

        // Win or Lose when David touched Goliath
        if (people.getCurrentRoom().equals(person.getCurrentRoom())) {
            if (person.isArmed()) {
                JOptionPane.showMessageDialog(this, "Congratulations David! You slew Goliath!");
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Oh no David! Goliath got you! Try again.");
                reset();
                }
            }
                
        //Repaint the panel to reflect the changes
        repaint();
    }


    //RETSET everthing after finish the game
    public void reset() {
        Random random = new Random();
        
        // Reset David to his non-armed state
        person.reset();
        
        // Generate random indices for David's starting location
        // int davidRow = random.nextInt(4); // Assuming 4 rows for d1
        // int davidCol = random.nextInt(4); // Assuming 4 columns for d1
        // int goliathRow = random.nextInt(2); // Assuming 2 rows for d2
        // int goliathCol = random.nextInt(2); // Assuming 2 columns for d2
        
        //Move David and Goliath back to their starting locations
        //Reset Goliath
    //people.setCurrentRoom(d2[random.nextInt(2)][random.nextInt(2)]);

    //Move David and Goliath back to their starting locations
    for (int i = 0; i < 13; i++) {
        // Reset David's positionnot in the stone room
        boolean davidInStoneRoom = true;
        while (davidInStoneRoom) {
            int davidRow = random.nextInt(4); // Assuming 4 rows for d1
            int davidCol = random.nextInt(4); // Assuming 4 columns for d1
            person.setCurrentRoom(d1[davidRow][davidCol]);
            davidInStoneRoom = false;
            // Check if David's room contains a stone
            for (Stone stone : block) {
                if (stone.getCurrentRoom().equals(person.getCurrentRoom())) {
                    davidInStoneRoom = true;
                    break;
                }
            }
        }

        // Reset Goliath's position not in the stone room
        boolean goliathInStoneRoom = true;
        while (goliathInStoneRoom) {
            int goliathRow = random.nextInt(2); // Assuming 2 rows for d2
            int goliathCol = random.nextInt(2); // Assuming 2 columns for d2
            people.setCurrentRoom(d2[goliathRow][goliathCol]);
            goliathInStoneRoom = false;
            // Check if Goliath's room contains a stone
            for (Stone stone : block) {
                if (stone.getCurrentRoom().equals(people.getCurrentRoom())) {
                    goliathInStoneRoom = true;
                    break;
                }
            }
        }
    }

        // person.setCurrentRoom(d1[0][3]);
        // people.setCurrentRoom(d2[1][0]);
        
        // Remove all stones from the block list
        block.clear();
        
        // Reset stone positions randomly
        for (int i = 0; i < 13; i++) {
            // Choose a random room from available rooms
            int row = random.nextInt(4); // Assuming 4 rows for d1, d3
            int col = random.nextInt(4); // Assuming 4 columns for d1, d3
            int roomType = random.nextInt(3); // Assuming 3 types of rooms
            
            Room randomRoom;
            if (roomType == 0) {
                randomRoom = d1[row][col];
            } else if (roomType == 1) {
                randomRoom = d2[row % 2][col % 2]; // Modulus to ensure index within bounds
            } else {
                randomRoom = d3[row][col];
            }
            
            // Create a new stone and set its current room
            Stone stone = new Stone();
            stone.setCurrentRoom(randomRoom);
            boolean isOccupied = false;

            // Check if the room is occupied by David or Goliath
            if (stone.getCurrentRoom().equals(person.getCurrentRoom()) ||
                stone.getCurrentRoom().equals(people.getCurrentRoom())) {
                isOccupied = true;
            }

            // Check if the room is occupied by another stone
            for (Stone existingStone : block) {
                if (stone.getCurrentRoom().equals(existingStone.getCurrentRoom())) {
                    isOccupied = true;
                    break;
                }
            }

            // Add the stone to the block list if the room is not occupied
            if (!isOccupied) {
                block.add(stone);
            }

        // //reset Stone location
        // for (int i=0; i < 13; i++){
        //     block.add(new Stone());
            
        // }
        // block.get(0).setCurrentRoom(d1[0][0]);
        // block.get(1).setCurrentRoom(d1[1][0]);
        // block.get(2).setCurrentRoom(d1[2][1]);
        // block.get(3).setCurrentRoom(d1[1][2]);
        // block.get(4).setCurrentRoom(d1[3][2]);
        // block.get(5).setCurrentRoom(d2[0][0]);
        // block.get(6).setCurrentRoom(d2[0][1]);
        // block.get(7).setCurrentRoom(d2[1][1]);
        // block.get(8).setCurrentRoom(d3[0][0]);
        // block.get(9).setCurrentRoom(d3[1][0]);
        // block.get(10).setCurrentRoom(d3[2][1]);
        // block.get(11).setCurrentRoom(d3[1][2]);
        // block.get(12).setCurrentRoom(d3[3][2]);
        
        
        // Repaint the panel to reflect the changes
        repaint();
    }
}

    

    public static void main(String[] args) {
        var window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700,600);
        window.setContentPane(new Main());
        window.setVisible(true);
    }


}  