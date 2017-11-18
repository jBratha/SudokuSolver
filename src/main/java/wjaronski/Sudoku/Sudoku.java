package wjaronski.Sudoku;

import java.util.*;

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
        return cells.stream().allMatch(e -> e.get() >= 0 && e.get() < 10) &&
                cells.stream().filter(e -> e.get() > 0 && e.get() < 10).allMatch(new HashSet<>()::add);
    }

    private int[][] parseStringToInts(String board){
        int[][] ints = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ints[i][j] = Integer.parseInt(board.charAt(i*9+j)+"");
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
}
