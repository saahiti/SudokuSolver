import org.jetbrains.annotations.Contract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Saahiti on 2016-01-10.
 */
public class Solver {
    private static SudokuGrid grid;
    private static SudokuCell[][] cells;

    public static void main(String[] args) throws Exception {
        FileReader in = new FileReader(new File("C:\\Users\\Saahiti\\OneDrive\\Programming\\Sudoku\\src\\Puzzles.txt"));
        BufferedReader br = new BufferedReader(in);

        String line = br.readLine();
        System.out.println(line);
        grid = new SudokuGrid();
        int count = 0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                SudokuCell cell = new SudokuCell();
                int num = Character.getNumericValue(line.charAt(count));
                if (num == 0) {
                    cell.setMutable(true);
                } else {
                    cell.setValue(num);
                }

                grid.setCell(cell, row, col);
                count++;
            }
        }
        cells = grid.getCells();

        grid.printGrid();
        solveGrid();
        grid.printGrid();
    }

    private static void solveGrid() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                SudokuCell cell = grid.getCells()[row][col];
                if (!cell.isMutable())
                    continue;

                cell.clear();
                ArrayList<Integer> possibleValues = getPossibleValues(row, col);
                ArrayList<Integer> triedValues = cell.getTriedValues();
                for (Integer triedValue : triedValues) {
                    possibleValues.remove(triedValue);
                }

                if (possibleValues.isEmpty()) {
                    if (row == 0 && col == 0) {
                        grid.initializeGrid();
                        col --;
                    } else if (col == 0) {
                        row --;
                        col = 7;
                    } else {
                        col -= 2;
                    }
                } else {
                    int randomIndex = (int) (Math.random() * possibleValues.size());
                    cell.setValue(possibleValues.get(randomIndex));
                    grid.setCell(cell, row, col);
                }
            }
        }
    }

    private static ArrayList<Integer> getPossibleValues(int row, int col){
        ArrayList<Integer> possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        int valueInRow, valueInColumn, valueInBox;

        for (int i = 0; i < 9; i++){
            if (i != col) {
                valueInRow = cells[row][i].getValue();
                possibleValues.remove((Object) valueInRow);
            }
            if (i != row) {
                valueInColumn = cells[i][col].getValue();
                possibleValues.remove((Object) valueInColumn);
            }
        }

        int xCentre = getAnchor(col);
        int yCentre = getAnchor(row);
        if (row == 3) {
            int i = 9;
        }
        ArrayList<Integer> valuesInBox = new ArrayList<>();
        for (int x = xCentre - 1; x <= xCentre + 1; x++){
            for (int y = yCentre -1; y <= yCentre + 1; y++){
                valuesInBox.add(cells[y][x].getValue());

                if (x != col || y != row) {
                    valueInBox = cells[y][x].getValue();
                    possibleValues.remove((Object) valueInBox);
                }
            }
        }

        return possibleValues;
    }

    public static void print(Object o) {
        System.out.println(o.toString());
    }

    @Contract(pure = true)
    private static int getAnchor(int num){
        if (num < 3){
            return 1;
        } else if (num < 6){
            return 4;
        }
        return 7;
    }
}
