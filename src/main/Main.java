package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

    public static void main(String args[]){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.setTitle("something something 2D game");
        ImageIcon image = new ImageIcon("res/player/right1.png");
        window.setIconImage(image.getImage());
        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);

        //setting up the object assets
        gp.setUpWorld();

        //starting the gameloop
        gp.startGameThread();

    }

}
