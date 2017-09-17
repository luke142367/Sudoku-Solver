package Main;

import Sudoku.SudokuReader;

import javax.swing.*;

/**
 * Created by LUKE_2 on 31/10/2015.
 */
public class Window extends JFrame{

    public static void main(String[] args){
        new Window();
    }

    public Window(){
        super("Sudoku Solver");
        this.setContentPane(new WindowPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
