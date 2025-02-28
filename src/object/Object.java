package object;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Object {

    //Object Image
    public BufferedImage image;
    
    //Object name
    public String name;
    public boolean is_collidable=false;

    //Object coordinates
    public int world_x,world_y;

    //Object hitbox
    public Rectangle hitbox=new Rectangle(0,0,48,48);

    //hitbox default coordinates
    public int hitbox_default_x=0;
    public int hitbox_default_y=0;

    //function to draw object similar to tiles
    public void draw(Graphics2D g2d,GamePanel gp){

         int screen_x=world_x-gp.player.worldx +gp.player.player_x;
            int screen_y=world_y-gp.player.worldy+gp.player.player_y;

            if(world_x+gp.tileSize>gp.player.worldx-gp.player.player_x && world_x-gp.tileSize<gp.player.worldx+gp.player.player_x && world_y+gp.tileSize>gp.player.worldy-gp.player.player_y  && world_y-gp.tileSize<gp.player.worldy+gp.player.player_y)
            g2d.drawImage(image , screen_x, screen_y, gp.tileSize, gp.tileSize, null);
    }
}
