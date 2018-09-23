package jBratha.solvers;

import jBratha.sudoku.Cell;

public interface Solver {
    public void solve();
    public void setBoard(Cell[][] board);
    public Cell[][] getBoard();
}
