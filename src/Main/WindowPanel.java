package Main;

import Sudoku.SudokuHandler;
import Sudoku.SudokuReader;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.peer.MouseInfoPeer;

/**
 * Created by LUKE_2 on 31/10/2015.
 */
public class WindowPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener{
    private int Height = 800, Width = 800;
    private boolean running;
    private Thread thread;
    private int targetTps = 60;

    private BufferedImage image;
    private Graphics2D g;
    private SudokuHandler sudokuHandler;

    public WindowPanel() {
        setPreferredSize(new Dimension(Width, Height));
        setFocusable(true);
        requestFocus();
        SudokuReader reader = new SudokuReader();
        sudokuHandler = new SudokuHandler(reader,this);
        init();
    }

    private void init() {
        //gsm = new GameStateManager(Width, Height);
        image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        running = true;
        addMouseListener(this);
        addMouseMotionListener(this);
        g = (Graphics2D)image.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    private void update(){

    }

    private void draw(){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,Width,Height);
        sudokuHandler.draw(g);
    }


    public void run() {
        int fps = 0; int tick = 0;
        double fpsTimer = System.currentTimeMillis();
        double secondsPerTick = 1D / targetTps;
        double nsPerTick = secondsPerTick * 1000000000D;
        double then = System.nanoTime();
        double now;
        double unprocessed = 0;
        while(running){
            now = System.nanoTime();
            unprocessed += (now - then) ;
            then = now;
            while(unprocessed >= nsPerTick){
                update();
                tick++;
                unprocessed -= nsPerTick;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            fps++;
            if(System.currentTimeMillis() - fpsTimer >= 1000){
                //game.setTitle(tick,fps,mean);
//                System.out.println("Fps: " + fps + " Tps: " + tick);
                fps = 0;
                tick = 0;
                fpsTimer += 1000;
            }
        }
    }

    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Width, Height);
        draw();
        g.drawImage(image, 0, 0, Width, Height, null);
    }

    public void mouseClicked(MouseEvent e){
        sudokuHandler.mouseClick(e);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        sudokuHandler.mouseMoved(e);
    }
}
