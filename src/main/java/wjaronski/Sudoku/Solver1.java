package wjaronski.Sudoku;

public class Solver1 {
//    jesli gdzies miejsce ma tylko 1 mozliwosc to nadaj wartosc
    private Cell[][] board;
    private boolean boardChanged;
    private Sudoku sudoku;

    public Solver1(Cell[][] board){
        this.board = board;
    }

    public Cell[][] solve(){
        Cell[][] newBoard = board;
        boardChanged = true;
        while(hasEmptyEntry(board) && boardChanged){
            board = newBoard;
//            board[0][0] = new Cell();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //delete posibilites
                }
            }
            //noinspection ConstantConditions
            if(board==newBoard)boardChanged = false;
        }
        return newBoard;
    }

    private boolean canValBeInxyBoard(int val, int x, int y, Cell[][] board){
        return false;

    }

    private boolean hasEmptyEntry(Cell[][] board) {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if(cell.get()==0) return true;
            }
        }
        return false;
    }

}
