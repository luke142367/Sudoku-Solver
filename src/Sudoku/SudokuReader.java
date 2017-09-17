package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuReader {
    private static final String filePath = "C:/Users/LUKE_2/Desktop/Sudokus.txt";
    BufferedReader br;
    FileReader fr;

    public SudokuReader(){

    }


    public int[][] readSudoku(){
        int[][] board = new int[9][9]; // [x][y] 0,0 top left
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            String line = br.readLine();
            String row = "";

            board[8][8] = 5;
            if (line != null){
                for(int i = 0; i < 9; i++) {
                    row = line.substring(9 * i, 9 * (i + 1));
                    for (int j = 0; j < 9; j++) {
                        board[j][i] = Integer.parseInt(row.substring(j, j + 1));
                    }
                }
            }else{
                System.out.println("No text in the file");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return board;
    }


}
