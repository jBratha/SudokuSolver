package jBratha.controller;

import jBratha.solvers.BruteForce;
import jBratha.sudoku.Sudoku;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class Controller implements Initializable {
    private ArrayList<ArrayList<TextField>> textFields;
    private ArrayList<GridPane> grids;
    private Sudoku sudoku;
    private int x = 0, y = 0;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label statusLabel;

    @FXML
    private Label cordsLabel;

    @FXML
    private Button solveButton;

    private void testTable() {
        updateStatusLabel(sudoku.isBoardValid(getInts()));
    }

    @FXML
    private void solveSudoku() {
        BruteForce bf = new BruteForce(getInts());
        if (bf.solve(0, 0)) {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Udalo sie rozwiazac !");
            printSudokuBoard(sudoku.parseCellArr(bf.getBoard()));
        }
        else {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Nie udalo sie rozwiazac !");
        }
    }

    private void printSudokuBoard(int[][] ints) {
        Platform.runLater(() -> {
            IntStream.range(0, 9)
                    .forEach(e -> IntStream.range(0, 9)
                            .forEach(f -> textFields.get(e).get(f).setText(ints[e][f] + "")));

        });
    }

    private void updateStatusLabel(boolean boardValid) {
        statusLabel.setText(boardValid ? "Board valid" : "Board invalid");
        statusLabel.setTextFill((boardValid) ? Color.GREEN : Color.RED);
    }

    private int[][] getInts() {
        int[][] ints = new int[9][9];
        IntStream.range(0, 9)
                .forEach(e ->
                        IntStream.range(0, 9)
                                .forEach(f -> {
                                    String strVal = textFields.get(e).get(f).getText();
                                    int val = (strVal.length() == 1) ? Integer.parseInt(strVal) : 0;
                                    ints[e][f] = val;
                                }));
        return ints;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        x = y = 0;
        sudoku = new Sudoku();
        textFields = new ArrayList<>(9);
        initGridPane();
    }

    private void keyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                x = (x == 0) ? x : x - 1;
                break;
            case DOWN:
                x = (x == 8) ? x : x + 1;
                break;
            case RIGHT:
                y = (y == 8) ? y : y + 1;
                break;
            case LEFT:
                y = (y == 0) ? y : y - 1;
                break;
            case ENTER:
                solveSudoku();
                break;
            case ESCAPE:
                clearTable();
                break;
        }
        textFields.get(x).get(y).requestFocus();
    }

    private void clearTable() {
        Platform.runLater(() -> {
            IntStream.range(0, 9)
                    .forEach(e -> IntStream.range(0, 9)
                            .forEach(f -> textFields.get(e).get(f).setText("")));

        });
    }

    private void initGridPane() {
        grids = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            GridPane gp = newGridPane();
            gridPane.add(gp, i % 3, i / 3);
            grids.add(gp);
        }
        for (int i = 0; i < 9; i++) {
            ArrayList<TextField> tfs = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                TextField tf = newTextField();
                tfs.add(tf);
                grids.get(i / 3 * 3 + j / 3).add(tf, j % 3, i % 3);
            }
            textFields.add(i, tfs);
        }
    }

    private TextField newTextField() {
        TextField tf = new TextField();
        tf.setAlignment(Pos.CENTER);
        tf.setMaxWidth(35);
        tf.addEventHandler(KeyEvent.KEY_PRESSED, this::keyEvent);
        addTextLimiter(tf);
        return tf;
    }

    private GridPane newGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.addColumn(0);
        gridPane.addColumn(1);
        gridPane.addColumn(2);
        gridPane.addRow(0);
        gridPane.addRow(1);
        gridPane.addRow(2);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private static void addTextLimiter(final TextField tf) {
        tf.textProperty().addListener((ov, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (tf.getText().length() > 1) {
                String s = tf.getText().substring(0, 1);
                tf.setText(s);
            }
        });
    }
}
