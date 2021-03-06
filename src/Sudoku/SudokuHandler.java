package Sudoku;

import Main.Button;
import Main.ButtonClick;
import Main.WindowPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class SudokuHandler {

    private int[][] sudokuBoard;
    private SudokuReader sudokuReader;
    private BufferedImage logo;
    private WindowPanel windowPanel;
    private boolean init = false;
    private boolean loaded = false;
    private SudokuSolver solver;
    private List<Integer> options;

    //buttons
    private Button loadButton;
    private Button stepButton;
    private Button solveButton;
    private Color loadColor;

    //Graphics Variables
    private int Wpadding = 103; //Width padding
    private int Tpadding = 100; //Top padding
    private int width = 800, height = 800;
    Font font = new Font("Arial", Font.PLAIN, 45);





    public SudokuHandler(SudokuReader sudokuReader, WindowPanel windowPanel){
        this.sudokuReader = sudokuReader;
        logo = loadImage("res/logo.png");
        this.windowPanel = windowPanel;
        init();
    }

    private void loadSudoku(){
        sudokuBoard = sudokuReader.readSudoku();
        System.out.println("Sudoku loaded");
        loaded = true;
        solver = new SudokuSolver(sudokuBoard);
    }

    public void init(){
        ButtonClick loadClicker = new ButtonClick() { public void click() { loadSudoku(); } };
        ButtonClick stepClicker = new ButtonClick() { public void click() {
            solver.step();
            }
        };
        ButtonClick solveClicker = new ButtonClick() {
            public void click() {
                solver.calculateAllOptions();
            }
        };
        loadColor = new Color(103,152,152);
        Color temp = new Color(80, 126, 126);
        loadButton = new Button(loadClicker,175,90,120,703,loadColor,"Load");
        stepButton = new Button(stepClicker,175,90,312,703,5,loadColor,"Step");
        solveButton = new Button(solveClicker,175,90,504,703,loadColor,"Solve");

        init = true;

    }

    public BufferedImage loadImage(String fileLocation){
        ClassLoader cl = this.getClass().getClassLoader();
        try {
            BufferedImage image = ImageIO.read((cl.getResource(fileLocation)));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void draw(Graphics2D g){
        if(init) {
            g.setColor(Color.BLACK);
            g.drawImage(logo, 72, 2, null);
            g.setColor(new Color(242, 242, 242));
            g.fillRect(Wpadding, Tpadding, (width - Wpadding * 2), (width - Wpadding * 2));
            g.setStroke(new BasicStroke(4));
            g.setColor(Color.BLACK);
            g.drawRect(Wpadding, Tpadding, (width - Wpadding * 2), (width - Wpadding * 2));
            //Main Lines
            g.drawLine(301,Tpadding,301,694);
            g.drawLine(499,Tpadding,499,694);
            g.drawLine(103,298,800-Wpadding,298);
            g.drawLine(103,496,800-Wpadding,496);
            //Smaller Lines
            //Vertical
            g.setStroke(new BasicStroke(2));
            g.drawLine(169,Tpadding, 169,694);
            g.drawLine(235,Tpadding, 235,694);
            g.drawLine(367,Tpadding, 367,694);
            g.drawLine(433,Tpadding, 433,694);
            g.drawLine(565,Tpadding, 565,694);
            g.drawLine(631,Tpadding, 631,694);
            //Horizontal
            g.drawLine(103,166,697,166);
            g.drawLine(103,232,697,232);
            g.drawLine(103,364,697,364);
            g.drawLine(103,430,697,430);
            g.drawLine(103,562,697,562);
            g.drawLine(103,628,697,628);
            //Draw numbers

            g.setFont(font);
            if(loaded) {
                for(int x = 0;x<9;x++){
                    for(int y = 0;y < 9;y++){
                        if(sudokuBoard[x][y] != 0) {
                            g.drawString(String.valueOf(sudokuBoard[x][y]), Wpadding + 22 + x * 66, Tpadding + 48 + y * 66);
                        }
                    }
                }
            }
            loadButton.draw(g);
            stepButton.draw(g);
            solveButton.draw(g);
        }
    }


    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(loadButton.mouseOver(x,y)|| stepButton.mouseOver(x,y)|| solveButton.mouseOver(x,y)){
            windowPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            windowPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void mouseClick(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        loadButton.clickAt(x,y);
        stepButton.clickAt(x,y);
        solveButton.clickAt(x,y);
    }
}
