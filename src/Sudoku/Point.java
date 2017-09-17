package Sudoku;

public class Point {
    private int x,y,solution;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int solution){
        this.x = x;
        this.y = y;
        this.solution = solution;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSolution() {
        return solution;
    }
}
