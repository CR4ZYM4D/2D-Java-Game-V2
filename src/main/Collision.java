package main;

import entity.Entity;
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
                entity_right_column = (entity_world_right - entity.speed) / gp.tileSize;
                tile_1 = gp.tm.mapTileNum[entity_top_row][entity_right_column];
                tile_2 = gp.tm.mapTileNum[entity_bottom_row][entity_right_column];
                if(gp.tm.tile[tile_1].can_collide == true || gp.tm.tile[tile_2].can_collide == true)
                    entity.is_colliding = true;
            break;

        }
    
    }
}
