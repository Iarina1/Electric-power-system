package myclasses.instances;

import common.Constants;
import myclasses.factory.People;

public final class Consumer extends People {
    private long monthlyIncome;
    private boolean hasPenalties;
    private long factureValue;
    private long penalty;
    private long contractTime;
    private Distributor distributor;
    private Distributor oldDistributor;


    public Consumer() {
    }

    /**
     * Initialize the class
     * @param idX consumer's id
     * @param budgetX the buget in round 0
     * @param monthlyIncomeX the monthlyIncomeX in round 0
     */
    public void setClass(final long idX, final long budgetX, final long monthlyIncomeX) {
        super.setId(idX);
        super.setBudget(budgetX);
        monthlyIncome = monthlyIncomeX;
        factureValue = 0;
        hasPenalties = false;
        super.setIsBankrupt(false);
        penalty = 0;
        contractTime = 0;
    }

    public long getId() {
        return super.getId();
    }

    /**
     * Set the id for the consumer
     * @param id
     */
    public void setId(final long id) {
        super.setId(id);
    }

    public long getBudget() {
        return super.getBudget();
    }

    /**
     * Set the bufget for the consumer
     * @param budget
     */
    public void setBudget(final long budget) {
        super.setBudget(budget);
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public long getFactureValue() {
        return factureValue;
    }

    public void setFactureValue(final long factureValue) {
        this.factureValue = factureValue;
    }

    public boolean getIsBankrupt() {
        return super.getIsBankrupt();
    }

    /**
     * Set if the consumer is bankrupt
     * @param bankrupt
     */
    public void setIsBankrupt(final boolean bankrupt) {
        super.setIsBankrupt(bankrupt);
    }

    public long getContractTime() {
        return contractTime;
    }

    public void setContractTime(final long contractTime) {
        this.contractTime = contractTime;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(final Distributor distributor) {
        this.distributor = distributor;
    }

    /**
     * calculate the bill when the costumer can't pay
     * @param invoice the bill value
     */
    public void setPenalty(final long invoice) {
        penalty = Math.round(Math.floor(Constants.PENALTY * invoice) + invoice);
    }

    public long getPenalty() {
        return penalty;
    }

    /**
     * calculate the buget for the consumer and of his old/ current distributor
     */
    public void calculateBuget() {
        if (!super.getIsBankrupt()) {
            super.setBudget(super.getBudget() + monthlyIncome);

            // verify is the consumer can pay the bill
            if ((super.getBudget() - factureValue) < 0) {
                if (!hasPenalties) {
                    hasPenalties = true;
                    setPenalty(factureValue);
                    oldDistributor = distributor;
                } else {
                    // he can't pay for the second time
                    if (oldDistributor.equals(distributor)) {
                        setIsBankrupt(true);
                    } else {
                        setPenalty(getPenalty() + factureValue);
                        oldDistributor = distributor;
                    }
                }
            } else {
                if (hasPenalties) {
                    if ((super.getBudget() - penalty - factureValue) < 0) {
                        // the consumer can't pay the bill and the penalty
                        setIsBankrupt(true);
                    } else {
                        super.setBudget(super.getBudget() - factureValue);
                        super.setBudget(super.getBudget() - penalty);
                        hasPenalties = false;
                        if (!distributor.getIsBankrupt()) {
                            // calculate the buget of his distributor
                            distributor.setBudget(distributor.getBudget() + factureValue);
                        }

                        // calculate the buget of his ooldDistributor
                        if (!oldDistributor.getIsBankrupt()) {
                            oldDistributor.setBudget(oldDistributor.getBudget() + penalty);
                        }
                    }
                } else {
                    super.setBudget(super.getBudget() - factureValue);
                    distributor.setBudget(distributor.getBudget() + factureValue);
                }
            }
        }
    }
}
