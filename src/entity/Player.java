package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler ki;
    public int boost_speed;

    //setting the coordinates of the player
    public final int player_x , player_y; 

    public Player(GamePanel gp ,KeyHandler ki){
        
        this.gp=gp;
        this.ki=ki;

        player_x = gp.maxScreenCol/2*gp.tileSize - gp.tileSize/2;
        player_y = gp.maxScreenRow/2*gp.tileSize - gp.tileSize/2;

        hitbox = new Rectangle(3,3,42,42);

        hitbox_default_x = hitbox.x;
        hitbox_default_y = hitbox.y;
        
        worldx=gp.maxScreenCol/2*gp.tileSize;
        worldy=gp.maxScreenRow/2*gp.tileSize;
        speed=4;
        boost_speed=5;
        direction="up";

    }
    
    public void update(){

        //needed to re-update direction after collision else player cannot move after colliding for first time
        directionUpdate(0);
        is_colliding = false;
        
        //checking tile collision
        gp.collider.checkTile(this);
        
        //checking object collision
        int object_index = gp.collider.checkObject(this, true);
        interactWithObject(object_index);

        //stopping the player if it is colliding with some tile
        if(!is_colliding ){
            if(ki.boost){
                directionUpdate(boost_speed);
            }
            else
                directionUpdate(speed);
        }
        //updating the sprites to make some sort of animation
        spriteCounter++;
        if(spriteCounter==10){
            spriteCounter=0;
            spriteNum= (spriteNum+2)%3;
        }
    }

    //function to handle object interaction
    public void interactWithObject(int object_index){

        if(object_index != Integer.MAX_VALUE){

            String object_name = gp.object_list[object_index].name;

            //if player collides with an object collect it
            switch(object_name){

                case "Key":
                    gp.object_list[object_index]=null;
                break;

            }


        }


    }

    private void directionUpdate(int s) {
        if(ki.input[0]){
            direction="up";
            worldy -= s;
        }

        else if(ki.input[1]){
            direction="down";
            worldy += s;
        }
        
        else if(ki.input[2]){
            direction="left";
            worldx -= s;
        }
        
        else if(ki.input[3]){
            direction="right";
            worldx += s;
        }
    }

    public void draw(Graphics2D g2D){
        
        //rendering the player
        image= null;

        img_path = "/player/" + direction + Integer.toString(spriteNum+1) + ".png";
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        g2D.drawImage(image , player_x ,player_y ,null);//null is the ImageObserver //if we want to draw to different size we can specify that as well

    }
}
