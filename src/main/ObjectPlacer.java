package main;

import object.KeyObject;

public class ObjectPlacer{

    GamePanel gp;
    
    ObjectPlacer(GamePanel gp){
        this.gp=gp;
    }

    public void setObjects(){

        //initialize object_list in this function
        gp.object_list[0]=new KeyObject();
        gp.object_list[0].world_x = 17*gp.tileSize;
        gp.object_list[0].world_y = 7*gp.tileSize;
    }

} 