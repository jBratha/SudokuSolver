package jBratha.solvers;

import jBratha.sudoku.Cell;
import jBratha.sudoku.Sudoku;

public class BruteForce implements Solver {
    private Cell[][] board;
    private Sudoku sudoku;

    public BruteForce(Cell[][] board) {
        this.board = board;
        this.sudoku = new Sudoku();
    }

    public BruteForce(int[][] ints) {
        sudoku = new Sudoku();
        this.board = sudoku.parseIntArr(ints);
    }

    public boolean solve(int x_cord, int y_cord) {
        if (board[x_cord][y_cord].get() != 0) {

            if (sudoku.isBoardValid(board)) {
                if (x_cord == 8 && y_cord == 8) {
                    return true;
                }

                // Find next slot on this row
                int next_x = x_cord + 1;
                int next_y = y_cord;

                // If at end of row, start next
                if (next_x >= 9) {
                    next_x = 0;
                    next_y++;
                }

                // Move on to next piece
                return solve(next_x, next_y);
            }

            // Value doesn't work. Early guess is bad
            else {
                return false;
            }

        }
        for (int val = 1; val < 10; val++) {
            board[x_cord][y_cord].setVal(val);
            // If value works, on to next
            if (sudoku.isBoardValid(board)) {
                // If last piece, puzzle solved!
                if (x_cord == 8 && y_cord == 8) {
                    return true;
                }

                // Find next slot on this row
                int next_x = x_cord + 1;
                int next_y = y_cord;

                // If at end of row, start next
                if (next_x >= 9) {
                    next_x = 0;
                    next_y++;
                }
                if (solve(next_x, next_y)) {
                    return true;
                }
            }
        }
        board[x_cord][y_cord].setVal(0);// = 0;
        return false;
    }

    @Override
    public void solve() {
        if (solve(0, 0)) System.out.println("Udalo sie rozwiazac !");
        else System.out.println("Nie udalo sie !");
    }

    @Override
    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    @Override
    public Cell[][] getBoard() {
        return board;
    }
}
