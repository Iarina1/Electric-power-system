package fileoutput;

import java.util.ArrayList;

public final class OutputMonthlyStats {
    private int month;
    private ArrayList<Long> distributorsIds;


    public OutputMonthlyStats(final int month,
                              final ArrayList<Long> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public ArrayList<Long> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(final ArrayList<Long> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
