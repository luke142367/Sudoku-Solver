package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuReader {

  private int currentLine = 0;
  private static final String filePath = "src/res/sudoku.txt";
  BufferedReader br;
  FileReader fr;

  public SudokuReader() {

  }


  public int[][] readSudoku() {
    int[][] board = new int[9][9]; // [x][y] 0,0 top left
    try {
      fr = new FileReader(filePath);
      br = new BufferedReader(fr);
      String line = "";
      for (int i = 0; i <= currentLine; i++) {
        line = br.readLine();
        if(line == null){
          currentLine = 0;
          return readSudoku();
        }
      }
      currentLine++;
      String row;

      if (line != null) {
        for (int i = 0; i < 9; i++) {
          row = line.substring(9 * i, 9 * (i + 1));
          for (int j = 0; j < 9; j++) {
            board[j][i] = Integer.parseInt(row.substring(j, j + 1));
          }
        }
      } else {
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
