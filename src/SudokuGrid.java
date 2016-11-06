import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Saahiti on 2015-12-17.
 */
public class SudokuGrid implements Serializable {
    private SudokuCell[][] cells;
    private String gridString;
    public static int gridCount = 0;

    /*
    Gary McGuire of University College Dublin shows in a proof posted online on 1 January1 that the minimum number of clues — or starting digits — needed to complete a puzzle is 17; puzzles with 16 or fewer clues do not have a unique solution. Most newspaper puzzles have around 25 clues, with the difficulty of the puzzle decreasing as more clues are given.
     */

    public SudokuGrid(){
        initializeGrid();
        printGrid();
        print("Number of Grids Tried: " + gridCount);
    }

    public void initializeGrid() {
        cells = new SudokuCell[9][9];
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                cells[row][col] = new SudokuCell();

        gridCount++;
    }
    public void setCell(SudokuCell cell, int row, int col){
        this.cells[row][col] = cell;
    }

    private void print(Object t){
        System.out.print(t);
    }

    public void printGrid(){
        print(getGridString());
    }

    private String getGridString() {
        gridString = "";
        for (int row = 0; row < 9; row++) {
            if (row == 0)
                gridString += "-------------\n";
            else if (row%3 == 0)
                gridString += "----+---+----\n";

            for (int col = 0; col < 9; col++) {
                if (col%3 == 0)
                    gridString += "|";

                gridString += cells[row][col].getValue();
            }
            gridString += "|\n";
        }
        gridString += "-------------\n";
        return gridString;
    }



    public SudokuCell[][] getCells() {
        return cells;
    }
}