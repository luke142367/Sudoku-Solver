package Sudoku;


import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
    private int[][] currentBoard;
    private List<Point> emptySquares = new ArrayList<Point>();
    private List<Point> foundSquares = new ArrayList<Point>();

    public SudokuSolver(int[][] board){
        currentBoard = board;
    }

    public void calculateAllOptions(){
        findEmptySquares();
        while(emptySquares.size() > 0) {
        System.out.println("New search!");
            for (Point square : emptySquares) {
                calculateOptions(square.getX(), square.getY());
            }
            if(foundSquares.size() == 0){
                System.out.println("NO squares found");
                break;
            }
            for (Point sqaure : foundSquares) {
                currentBoard[sqaure.getX()][sqaure.getY()] = sqaure.getSolution();
            }
            foundSquares.clear();
            findEmptySquares();
        }
    }

    public void step(){
        findEmptySquares();
        System.out.println("New search!");
        for (Point square : emptySquares) {
            calculateOptions(square.getX(), square.getY());
        }
        if(foundSquares.size() == 0){
            System.out.println("NO squares found");
        }
        for (Point sqaure : foundSquares) {
            currentBoard[sqaure.getX()][sqaure.getY()] = sqaure.getSolution();
        }
        foundSquares.clear();
    }

    public void calculateOptions(int x,int y){

       List<Integer> options = new ArrayList<Integer>();
       options = generateBlankList();
        for(int Ix = 0;Ix<9;Ix++){
            if(currentBoard[Ix][y] != 0){
                options = removeFromList(options,currentBoard[Ix][y]);
            }
        }
        for(int Iy = 0;Iy<9;Iy++){
            if(currentBoard[x][Iy] != 0){
                options = removeFromList(options,currentBoard[x][Iy]);
            }
        }
        for(Point square:getAdjacentSquares(x,y)){
            if(currentBoard[square.getX()][square.getY()] != 0){
                options = removeFromList(options,currentBoard[square.getX()][square.getY()]);
            }
        }
        System.out.println("Options for " + x + " " + y);
        for(int option:options){
            System.out.println(option);
        }

        if(options.size() == 1){
            System.out.println("Found! at " + x + " " + y + " solution " + options.get(0));
            foundSquares.add(new Point(x,y,options.get(0)));
        }
        System.out.println();

    }

    public int getSquareID(int x,int y){
        int newX = (x - (x % 3))/3;
        newX++;
        int newY = (y - (y % 3))/3;
        int ID = 3*newY + newX;
        return ID;
    }

    public List<Point> getAdjacentSquares(int x,int y){
        List<Point> squares = new ArrayList();
        int ID = getSquareID(x,y);
        int newX = ((ID -1 ) % 3)*3;
        int newY =  ((ID - 1) - ((ID - 1) % 3));
        for(int Ix = newX; Ix < newX + 3; Ix++){
            for(int Iy = newY; Iy < newY + 3; Iy++){
                if(!(x == Ix && y == Iy)) {
                    squares.add(new Point(Ix, Iy));
                }
            }
        }
        return  squares;
    }

    public List generateBlankList(){
        List list = new ArrayList();
        for(int i = 1; i <= 9; i++){
            list.add(i);
        }
        return list;
    }

    public List removeFromList(List<Integer> list, int remove){
        for (int i = 0; i < list.size();i++) {
            if(list.get(i) == remove){
                list.remove(i);
                return list;
            }
        }
        return list;
    }

    public void findEmptySquares(){
        emptySquares.clear();
        for(int x = 0;x<9;x++){
            for(int y = 0; y<9; y++){
                if(currentBoard[x][y] == 0){
                    emptySquares.add(new Point(x,y));
                }
            }
        }
    }

}
