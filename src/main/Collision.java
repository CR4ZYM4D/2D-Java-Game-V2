package main;

import entity.Entity;
import object.Object;
import tile.TileManager;


//class to handle and resolve collisions
public class Collision {
    GamePanel gp;
    TileManager tm;
    public Collision(GamePanel gp){

        this.gp = gp;

    }
    public Collision(GamePanel gp , TileManager tm){
        this.gp = gp;
        this.tm = tm;
    }

    public void checkTile(Entity entity){

        //finding the global coordinates of the entity 
        int entity_world_left = entity.worldx + entity.hitbox.x;
        int entity_world_right = entity_world_left+entity.hitbox.width;
        int entity_world_top = entity.worldy + entity.hitbox.y;
        int entity_world_bottom=entity_world_top+entity.hitbox.height;

        //finding the row & column in which entity is present
        int entity_left_column = entity_world_left/gp.tileSize;
        int entity_right_column = entity_world_right/gp.tileSize;
        int entity_top_row = entity_world_top/gp.tileSize;
        int entity_bottom_row = entity_world_bottom/gp.tileSize; 

        //finding the two tile entity can collide with
        int tile_1 , tile_2;

        switch(entity.direction){

            case "up":
                entity_top_row = (entity_world_top - entity.speed) / gp.tileSize;
                tile_1 = gp.tm.mapTileNum[entity_top_row][entity_left_column];
                tile_2 = gp.tm.mapTileNum[entity_top_row][entity_right_column];
                if(gp.tm.tile[tile_1].can_collide == true || gp.tm.tile[tile_2].can_collide == true)
                    entity.is_colliding = true;
            break;
            case "down":
                entity_bottom_row = (entity_world_bottom + entity.speed) / gp.tileSize;
                tile_1 = gp.tm.mapTileNum[entity_bottom_row][entity_left_column];
                tile_2 = gp.tm.mapTileNum[entity_bottom_row][entity_right_column];
                if(gp.tm.tile[tile_1].can_collide == true || gp.tm.tile[tile_2].can_collide == true)
                    entity.is_colliding = true;
            break;
            case "left":
                entity_left_column = (entity_world_left - entity.speed) / gp.tileSize;
                tile_1 = gp.tm.mapTileNum[entity_top_row][entity_left_column];
                tile_2 = gp.tm.mapTileNum[entity_bottom_row][entity_left_column];
                if(gp.tm.tile[tile_1].can_collide == true || gp.tm.tile[tile_2].can_collide == true)
                    entity.is_colliding = true;
            break;
            case "right":
                entity_right_column = (entity_world_right + entity.speed) / gp.tileSize;
                tile_1 = gp.tm.mapTileNum[entity_top_row][entity_right_column];
                tile_2 = gp.tm.mapTileNum[entity_bottom_row][entity_right_column];
                if(gp.tm.tile[tile_1].can_collide == true || gp.tm.tile[tile_2].can_collide == true)
                    entity.is_colliding = true;
            break;

        }
    
    }

    //function that checks if player is colliding with some object if any
    public int checkObject(Entity entity , boolean player){

        int object_index=Integer.MAX_VALUE;

        for(int j =0; j<gp.object_list.length ; j++){

            Object i = gp.object_list[j];
            
            if(i!=null){
                //finding global coordinates of entity hitbox in X and Y
                entity.hitbox.x += entity.worldx;
                entity.hitbox.y += entity.worldy;

                //finding global X and Y coordinates of object hitbox
                i.hitbox.x += i.world_x;
                i.hitbox.y += i.world_y;

                switch (entity.direction) {
                    case "up":
                    entity.hitbox.y-=entity.speed;
                    if(entity.hitbox.intersects(i.hitbox)){
                        if(i.is_collidable)
                            entity.is_colliding= true;
                        if(player)
                            object_index=j;
                    }
                    break;
                    case "down":
                    entity.hitbox.y+=entity.speed;
                    if(entity.hitbox.intersects(i.hitbox)){
                        if(i.is_collidable)
                            entity.is_colliding= true;
                        if(player)
                            object_index=j;
                    }
                    break;
                    case "left":
                    entity.hitbox.x-=entity.speed;
                    if(entity.hitbox.intersects(i.hitbox)){
                        if(i.is_collidable)
                            entity.is_colliding= true;
                        if(player)
                            object_index=j;
                    }
                    break;
                    case "right":
                    entity.hitbox.x+=entity.speed;
                    if(entity.hitbox.intersects(i.hitbox)){
                        if(i.is_collidable)
                            entity.is_colliding= true;
                        if(player)
                            object_index=j;
                    }
                    break;
                }

                //resetting hitbox positions
                entity.hitbox.x = entity.hitbox_default_x;
                entity.hitbox.y = entity.hitbox_default_y;
                i.hitbox.x = i.hitbox_default_x;
                i.hitbox.y = i.hitbox_default_y;
            } 
        }

        return object_index;
    }
}
