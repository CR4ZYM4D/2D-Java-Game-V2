package entity;

import java.awt.Image;
import java.awt.Rectangle;

public class Entity{

    public Rectangle hitbox;
    Image image;
    String img_path;
    public int hitbox_default_x , hitbox_default_y;
    public int worldx,worldy, speed;
    public String direction;
    public boolean is_colliding=false;
    int spriteNum,spriteCounter;
}