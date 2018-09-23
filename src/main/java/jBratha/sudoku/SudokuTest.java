package jBratha.sudoku;

import org.junit.Test;


public class SudokuTest {
    @Test
    public void getBox() throws Exception {
        Sudoku sudoku = new Sudoku();
        int[][] ints = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 3, 0, 0, 0},
                {0, 0, 0, 4, 5, 6, 0, 0, 0},
                {0, 0, 0, 7, 8, 9, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        Cell[][] board = sudoku.parseIntArr(ints);
//        for (int i = 3; i < 6; i++) {
//            for (int j = 3; j < 6; j++) {
//                System.out.print("[" + i + "," + j + "]: ");
//                sudoku.getBox(board, i, j).forEach(e -> System.out.print(e.get()));
//                System.out.println();
//
//            }
//        }
        sudoku.getRow(board, 3).forEach(e -> System.out.print(e.get()));
        System.out.println();
        sudoku.getCol(board, 3).forEach(e -> System.out.print(e.get()));
//        sudoku.getBox(board, 2,2).forEach(e-> System.out.print(e.get()));
//        System.out.println();
//        assertEquals()
    }

}
