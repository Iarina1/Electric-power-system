package fileinput;

import java.util.ArrayList;

public final class Input {
    private int numberOfTurns;
    private InputInitialData initialData;
    private ArrayList<InputMonthlyUpdates> monthlyUpdates;


    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InputInitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InputInitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<InputMonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<InputMonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
