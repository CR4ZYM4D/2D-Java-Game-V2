package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;
import object.Object;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //tile dimension
    public final int tileSize = 48 ;
    
    //screen dimension
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    
    //screen pixels
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;

    //map dimensions
    public final int maxworldcol=50;
    public final int maxworldrow=50;

    //map pixels
    public final int worldmaxwidth=maxworldcol*tileSize;
    public final int worldmaxheight=maxworldrow*tileSize;

    final int fps = 60;

    //Map assets
    TileManager tm = new TileManager(this) ;

    //Input Handling
    KeyHandler kh = new KeyHandler();

    //GameThread
    Thread gameThread;

    //Asset Placer
    ObjectPlacer op = new ObjectPlacer(this);

    //Player initialization
    public Player player = new Player(this,kh);

    //collsion checker initialization
    public Collision collider = new Collision(this);

    //object list 
    public Object object_list[]=new Object[10];

        //constructor
        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth , screenHeight));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true);
            this.addKeyListener(kh);
            this.setFocusable(true);
        }
    
        public void startGameThread(){
    
            gameThread = new Thread(this);
            gameThread.start();
    
        }
    
        //gameloop
        @Override
        public void run() {
        
            double drawInterval = 1000/fps;
            double delta = 0;
            long lastTime = System.currentTimeMillis();
            long currentTime;

            while(gameThread != null){
                currentTime = System.currentTimeMillis();

                delta += (currentTime - lastTime)/drawInterval;
                lastTime = currentTime;

                if(delta >=1){
                    update();
                    repaint();
                    delta--;
                }
            }
    
        }

        //objects and asset setup function
        public void setUpWorld(){
            op.setObjects();
        }
    
        //loop update function
        public void update(){

            player.update();            

        }
    
        //screen rendering function
        public void paintComponent(Graphics g){
    
            super.paintComponent(g);
    
            Graphics2D g2d = (Graphics2D)g;
    
            //drawing tiles
            tm.draw(g2d);

            //drawing objects
            for(Object i : object_list){
                if(i!=null)
                    i.draw(g2d, this);
            }

            player.draw(g2d);

            g2d.dispose();

    }

}