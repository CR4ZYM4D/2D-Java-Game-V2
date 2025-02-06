package entity;

import java.awt.Image;
import java.awt.Rectangle;

public class Entity{

    public Rectangle hitbox;
    Image image;
    String img_path;
    public int worldx,worldy, speed;
    public String direction;
    public boolean is_colliding=false;
    int spriteNum,spriteCounter;
}