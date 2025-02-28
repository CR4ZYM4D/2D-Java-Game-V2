package object;

import javax.imageio.ImageIO;

public class KeyObject extends Object{

    //constructor
    public KeyObject(){

        name = "Key";

        //loading the image
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));//image path 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
