package fileoutput;

import java.util.ArrayList;

public final class OutputDistributors {
    private long id;
    private long energyNeededKW;
    private long contractCost;
    private long budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private ArrayList<OutputContracts> contracts;

    public OutputDistributors(final long id, final long budget, final boolean isBankrupt,
                              final ArrayList<OutputContracts> contracts,
                              final long energyNeededKW, final long contractCost,
                              final String producerStrategy) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.producerStrategy = producerStrategy;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<OutputContracts> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<OutputContracts> contracts) {
        this.contracts = contracts;
    }

    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(final long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public long getContractCost() {
        return contractCost;
    }

    public void setContractCost(final long contractCost) {
        this.contractCost = contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(final String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
