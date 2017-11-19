package wjaronski.Sudoku;

import java.util.Arrays;

public class Solver1 {
    //    jesli gdzies miejsce ma tylko 1 mozliwosc to nadaj wartosc
    private Cell[][] board;
    private boolean boardChanged;
    private Sudoku sudoku;

    public Solver1(Cell[][] board) {
        this.board = board;
        sudoku = new Sudoku();
    }

    public Cell[][] solve() {
        if (sudoku.isBoardValid(board)) {
            Cell[][] newBoard = board;
            boardChanged = true;
            while (hasEmptyEntry(board) && boardChanged) {
                boardChanged = true;
                newBoard = board;
//            board[0][0] = new Cell();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        //delete posibilites
                        if (newBoard[i][j].get() == 0){
                            sudoku.posibilities(newBoard,i,j);
//                            newBoard[i][j].setPosib(sudoku.posibilities(newBoard, i, j));

                        }
                    }
                }

                if (Arrays.deepEquals(newBoard, board)) boardChanged = false;
            }
            return newBoard;
        }
        return board; // nie da sie
    }

    private boolean canValBeInxyBoard(int val, int x, int y, Cell[][] board) {
        return false;

    }

    private boolean hasEmptyEntry(Cell[][] board) {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.get() == 0) return true;
            }
        }
        return false;
    }

}
