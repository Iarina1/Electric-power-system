package myclasses.instances;

import common.Constants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public final class Producer {
    private long id;
    private String energyType;
    private long maxDistributors;
    private double priceKW;
    private long energyPerDistributor;
    private HashMap<Long, ArrayList<Long>> monthlyStats;
    private ArrayList<Distributor> distributorProducer;


    public Producer(final long id, final String energyType, final long maxDistributors,
                    final double priceKW, long energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        monthlyStats = new HashMap<>();
        distributorProducer = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public long getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public HashMap<Long, ArrayList<Long>> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(final HashMap<Long, ArrayList<Long>> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public void setMaxDistributors(final long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public ArrayList<Distributor> getDistributorProducer() {
        return distributorProducer;
    }

    public void setDistributorProducer(final ArrayList<Distributor> distributorProducer) {
        this.distributorProducer = distributorProducer;
    }


    /**
     * Sort the producers based on the green strategy (energy type, price, quantity, id)
     * @param producers the list of the producers
     * @return a list of sorted producers
     */
    public static ArrayList<Producer> sortGreen(final ArrayList<Producer> producers) {
        ArrayList<Producer> renewableProducers = new ArrayList<>();
        ArrayList<Producer> nonrenewableProducers = new ArrayList<>();
        ArrayList<Producer> producerGreenSorted = new ArrayList<>();

        // create 2 ArrayList: one which contains the producers with renewable energy and one
        // which contains the others
        for (Producer producer : producers) {
            if (producer.getEnergyType().equals(Constants.WIND)
            || producer.getEnergyType().equals(Constants.SOLAR)
            || producer.getEnergyType().equals(Constants.HYDRO)) {
                renewableProducers.add(producer);
            } else {
                nonrenewableProducers.add(producer);
            }
        }

        // sort the producers with renewable energy
        ArrayList<Producer> renewableProducersSorted = Producer.sortPrice(renewableProducers);

        // sort the producers with non-renewable energy

        ArrayList<Producer> nonrenewableProducersSorted = Producer.sortPrice(nonrenewableProducers);

        // create the list of the sorted producers
        producerGreenSorted.addAll(renewableProducersSorted);
        producerGreenSorted.addAll(nonrenewableProducersSorted);

        return producerGreenSorted;
    }

    /**
     * Sort the producers based on the price strategy (price, quantity, id)
     * @param producers the list of the producers
     * @return a list of sorted producers
     */
    public static ArrayList<Producer> sortPrice(final ArrayList<Producer> producers) {
        ArrayList<Producer> producerPriceSorted = new ArrayList<>();

        // sort the producers
        TreeSet<Producer> treeSetPrice = new TreeSet<>(Comparator.comparingDouble(
                Producer::getPriceKW).thenComparing(Producer::getEnergyPerDistributor,
                Comparator.reverseOrder()).thenComparing(Producer::getId));
        treeSetPrice.addAll(producers);

        // create an ArrayList with the sorted producers
        producerPriceSorted.addAll(treeSetPrice);

        return producerPriceSorted;
    }

    /**
     * Sort the producers based on the price strategy (quantity, id)
     * @param producers the list of the producers
     * @return a list of sorted producers
     */
    public static ArrayList<Producer> sortQuantity(final ArrayList<Producer> producers) {
        ArrayList<Producer> producerQuantitySorted = new ArrayList<>();

        // sort the producers
        TreeSet<Producer> treeSetQuantity = new TreeSet<>(Comparator
                .comparingLong(Producer::getEnergyPerDistributor).reversed()
                .thenComparing(Producer::getId));
        treeSetQuantity.addAll(producers);

        // create a list of the sorted producers
        producerQuantitySorted.addAll(treeSetQuantity);

        return producerQuantitySorted;
    }

    /**
     * Verify if the producer can give energy to distributors
     * @param numberOfMonth the actual month
     * @return true if it is full and false otherwise
     */
    public boolean isFull(final int numberOfMonth) {
        if (!this.monthlyStats.containsKey(numberOfMonth)) {
            return false;
        } else if (this.monthlyStats.get(numberOfMonth).size() < this.maxDistributors) {
            return false;
        }
        return true;
    }

    /**
     * Add a distributor to the producer's list of distributors
     * @param distributor the new distributor
     */
    public void addDistributor(final Distributor distributor) {
        distributorProducer.add(distributor);
    }

    /**
     * Update monthly stats for the current month
     * @param numberOfMonth the current month
     * @param producers the list of the producers
     */
    public static void updateMonthlyStats(final int numberOfMonth,
                                   final ArrayList<Producer> producers) {
        for (Producer producer : producers) {
            ArrayList<Long> newDistributorList = new ArrayList<>();
            for (Distributor distributor : producer.distributorProducer) {
                newDistributorList.add(distributor.getId());
            }
            producer.monthlyStats.put((long) numberOfMonth, newDistributorList);
        }
    }
}
