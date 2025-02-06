package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    public final int tileSize = 48 ;
    
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;

    public final int maxworldcol=50;
    public final int maxworldrow=50;
    public final int worldmaxwidth=maxworldcol*tileSize;
    public final int worldmaxheight=maxworldrow*tileSize;

    final int fps = 120;

    TileManager tm = new TileManager(this) ;
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,kh);
    public Collision collider = new Collision(this);
    
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
    
        public void update(){

            player.update();            

        }
    
        public void paintComponent(Graphics g){
    
            super.paintComponent(g);
    
            Graphics2D g2d = (Graphics2D)g;
    
            tm.draw(g2d);
            player.draw(g2d);

            g2d.dispose();

    }

}