package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager{
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp ){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxworldrow][gp.maxworldcol];

        getTileImage();
        loadMap("/Maps/map1.txt");

    }
    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tile[1]=new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tile[2]=new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));
            tile[2].can_collide=true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath){
        try{

            InputStream is= getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                row++;
                col=0;

            }
            br.close();
        }
        catch(Exception e)
        {e.printStackTrace();}
    }

    public void draw(Graphics2D g2d){

        int worldcol=0;
        int worldrow=0;

        while(worldcol<gp.maxworldcol && worldrow<gp.maxworldrow){

            int tileNum = mapTileNum[worldrow][worldcol];

            int worldx= worldcol*gp.tileSize;
            int worldy = worldrow*gp.tileSize;
            int player_x=worldx-gp.player.worldx +gp.player.player_x;
            int player_y=worldy-gp.player.worldy+gp.player.player_y;

            if(worldx+gp.tileSize>gp.player.worldx-gp.player.player_x && worldx-gp.tileSize<gp.player.worldx+gp.player.player_x && worldy+gp.tileSize>gp.player.worldy-gp.player.player_y  && worldy-gp.tileSize<gp.player.worldy+gp.player.player_y)
            g2d.drawImage(tile[tileNum].image , player_x, player_y, gp.tileSize, gp.tileSize, null);
            worldcol++;

            if(worldcol == gp.maxworldcol){
                worldcol=0;
                worldrow++;
            }

        }

    }


}