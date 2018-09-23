package jBratha.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Sudoku {

    public boolean isBoardValid(Cell[][] board) {
        ArrayList<Cell> row = new ArrayList<>();
        ArrayList<Cell> col = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            row.clear();
            col.clear();
            for (int j = 0; j < 9; j++) {
                row.add(board[i][j]);
                col.add(board[j][i]);
            }
            if (!isValidLine(row) || !isValidLine(col)) return false;
        }
        return areValidBoxes(board);
    }

    public boolean isBoardValid(int[][] ints) {
        return isBoardValid(parseIntArr(ints));
    }

    public boolean isBoardValid(String board) {
        int[][] ints = parseStringToInts(board);
        Cell[][] cells = parseIntArr(ints);
        return isBoardValid(cells);
    }

    private boolean areValidBoxes(Cell[][] board) {
        ArrayList<Cell> box = new ArrayList<>();
        box.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box.clear();
                for (int k = 0; k < 9; k++)
                    box.add(board[i * 3 + k / 3][j * 3 + k % 3]);
                if (!isValidLine(box)) return false;
            }
        }
        return true;
    }

    private boolean isValidLine(ArrayList<Cell> cells) {
        return cells.stream().allMatch(e -> e.get() > 0 && e.get() < 10) &&
                cells.stream().filter(e -> e.get() > 0 && e.get() < 10).allMatch(new HashSet<>()::add);
    }

    private int[][] parseStringToInts(String board) {
        int[][] ints = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ints[i][j] = Integer.parseInt(board.charAt(i * 9 + j) + "");
            }
        }
        return ints;
    }

    public Cell[][] parseIntArr(int[][] arr) {
        Cell[][] cels = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cels[i][j] = new Cell(arr[i][j]);
            }
        }
        return cels;
    }

    public int[][] parseCellArr(Cell[][] cells) {
        int[][] ints = new int[9][9];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                ints[i][j] = cells[i][j].get();
            }
        }
        return ints;
    }

    public void posibilities(Cell[][] board, int x, int y) {
        List<Cell> row = getRow(board, x);
        List<Cell> col = getCol(board, y);
        List<Cell> box = getBox(board, x, y);

        row.stream().filter(e -> e.get() > 0).forEach(e -> board[x][y].deletePosibility(e.get()));
        col.stream().filter(e -> e.get() > 0).forEach(e -> board[x][y].deletePosibility(e.get()));
        box.stream().filter(e -> e.get() > 0).forEach(e -> board[x][y].deletePosibility(e.get()));
    }

    public List<Cell> getBox(Cell[][] board, int x, int y) {
        List<Cell> box = new ArrayList<>(9);
        for (int i = 0; i < 9; i++)
            box.add(board[x / 3 * 3 + i / 3][y / 3 * 3 + i % 3]);
        return box;
    }

    public List<Cell> getRow(Cell[][] board, int x) {
        List<Cell> row = new ArrayList<>(9);
        for (int i = 0; i < 9; i++)
            row.add(board[x][i]);
        return row;
    }

    public List<Cell> getCol(Cell[][] board, int y) {
        List<Cell> col = new ArrayList<>(9);
        for (int i = 0; i < 9; i++)
            col.add(board[i][y]);
        return col;
    }

    public boolean canValBePlacedInBoardxy(int val, Cell[][] tempBoard, int i, int j) {
        tempBoard[i][j].setVal(val);
        return isBoardValid(tempBoard);
    }
}
