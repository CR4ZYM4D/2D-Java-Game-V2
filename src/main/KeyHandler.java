package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean input[] = new boolean[4];
    public boolean boost=false;
    @Override
    public void keyTyped(KeyEvent e) {

        int code  = e.getKeyCode();

        if(code == KeyEvent.VK_W)
            input[0] = true;
        if(code == KeyEvent.VK_S)
            input[1] = true;
        if(code == KeyEvent.VK_A)
            input[2] = true;
        if(code == KeyEvent.VK_D)
            input[3] = true;
        if(code == KeyEvent.VK_SHIFT)
            boost=true;

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code  = e.getKeyCode();

        if(code == KeyEvent.VK_W)
            input[0] = true;
        if(code == KeyEvent.VK_S)
            input[1] = true;
        if(code == KeyEvent.VK_A)
            input[2] = true;
        if(code == KeyEvent.VK_D)
            input[3] = true;
        if(code == KeyEvent.VK_SHIFT)
            boost=true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code  = e.getKeyCode();

        if(code == KeyEvent.VK_W)
            input[0] = false;
        else if(code == KeyEvent.VK_S)
            input[1] = false;
        else if(code == KeyEvent.VK_A)
            input[2] = false;
        else if(code == KeyEvent.VK_D)
            input[3] = false;
        else if(code == KeyEvent.VK_SHIFT)
            boost=false;
    }



}