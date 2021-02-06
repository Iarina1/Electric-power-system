package myclasses.factory;

public class People {
    private long id = 0;
    private long budget = 0;
    private boolean isBankrupt = false;

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public long getBudget() {
        return budget;
    }

    /**
     *
     * @param budget
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     *
     * @return
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     *
     * @param bankrupt
     */
    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
