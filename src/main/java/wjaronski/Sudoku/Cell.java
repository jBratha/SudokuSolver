package wjaronski.Sudoku;


import java.util.Arrays;

public class Cell {
    private int[] posib;
    private int certainNumber;

    public Cell(int certainNumber) {
        posib = new int[]{1,2,3,4,5,6,7,8,9};
        this.certainNumber = certainNumber;
    }

    public int[] getPosib() {
        return Arrays.stream(posib).filter(e->e!=0).toArray();
    }

    public void setPosib(int[] posib) {
        this.posib = posib;
    }

    public int get() {
        return certainNumber;
    }

    public void setCertainNumber(int certainNumber) {
        this.certainNumber = certainNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return certainNumber == cell.certainNumber;
    }

    @Override
    public int hashCode() {
        return certainNumber;
    }

    public void addPosibility(int val) {
        if (Arrays.stream(posib).anyMatch(e -> e == val)) return;
        for (int i = 0; i < posib.length; i++) {
            if (posib[i] == 0) posib[i] = val;
        }
    }

    public void deletePosiibility(int val){
        for (int i = 0; i < posib.length; i++) {
            if(posib[i]==val) posib[i]=0;
        }
    }
}
