import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Saahiti on 2015-12-19.
 */
public class SudokuCell implements Serializable {
    private ArrayList<Integer> possibleValues;
    private ArrayList<Integer> triedValues;
    public ArrayList<Integer> neighbours;
    public boolean mutable;
    private int value;

    public SudokuCell() {
        this.possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.triedValues = new ArrayList<>();
    }

    public void clear() {
        this.value = 0;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setValue(int value) {
        this.value = value;
        this.setTriedValue(value);
    }

    public int getValue(){
        return this.value;
    }

    public void setTriedValue (int value) {
        triedValues.add(value);
    }

    public ArrayList<Integer> getTriedValues () {
        return this.triedValues;
    }

    public void setPossibleValues (ArrayList<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }


}
