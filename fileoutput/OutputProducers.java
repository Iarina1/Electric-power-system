package fileoutput;

import java.util.ArrayList;

public final class OutputProducers {
    private long id;
    private long maxDistributors;
    private double priceKW;
    private String energyType;
    private long energyPerDistributor;
    private ArrayList<OutputMonthlyStats> monthlyStats;


    public OutputProducers(final long id, final long maxDistributors,
                           final double priceKW, final String energyType,
                           final long energyPerDistributor,
                           final ArrayList<OutputMonthlyStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public ArrayList<OutputMonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final ArrayList<OutputMonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
