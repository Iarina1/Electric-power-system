package myclasses.instances;

import common.Constants;
import myclasses.factory.People;
import myclasses.strategy.Contex;
import myclasses.strategy.GreenStrategy;
import myclasses.strategy.PriceStrategy;
import myclasses.strategy.QuantityStrategy;

import java.util.ArrayList;

public final class Distributor extends People {
    private long contractLength;
    private long infrastructureCost;
    private long productionCost;
    private long contractCost;
    private ArrayList<Consumer> consumers;
    private long energyNeededKW;
    private String producerStrategy;
    private long energyPerDistributor;
    private double priceKW;
    private ArrayList<Producer> producerDistributor;
    private boolean needsProducer;


    public Distributor() {
    }

    /**
     *
     * @param idX the consumer id
     * @param contractLengthX the length of the contract
     * @param budgetX the buget from round 0
     * @param initialInfrastructureCostX the infrastructure cost from round 0
     * @param energyNeededKWX the energy needed from the producers
     * @param producerStrategyX the strategy of the distributor
     */
    public void setClass(final long idX, final long contractLengthX,
                         final long budgetX, final long initialInfrastructureCostX,
                         final long energyNeededKWX, final String producerStrategyX) {
        super.setId(idX);
        contractLength = contractLengthX;
        super.setBudget(budgetX);
        infrastructureCost = initialInfrastructureCostX;
        energyNeededKW = energyNeededKWX;
        producerStrategy = producerStrategyX;
        consumers = new ArrayList<>();
        contractCost = 0;
        super.setIsBankrupt(false);
        producerDistributor = new ArrayList<>();
        needsProducer = true;
    }

    public long getId() {
        return super.getId();
    }

    /**
     * Set the id for the distributor
     * @param id
     */
    public void setId(final long id) {
        super.setId(id);
    }

    public long getContractLength() {
        return contractLength;
    }

    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    public long getBudget() {
        return super.getBudget();
    }

    /**
     * Set the budget for the distributor
     * @param budget
     */
    public void setBudget(final long budget) {
        super.setBudget(budget);
    }

    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public long getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(long productionCost) {
        this.productionCost = productionCost;
    }

    public boolean getIsBankrupt() {
        return super.getIsBankrupt();
    }

    /**
     * Set if the distributor is bankrupt
     * @param bankrupt
     */
    public void setIsBankrupt(final boolean bankrupt) {
        super.setIsBankrupt(bankrupt);
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public long getContractCost() {
        return contractCost;
    }

    public void setContractCost(final long contractCost) {
        this.contractCost = contractCost;
    }

    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Producer> getProducerDistributor() {
        return producerDistributor;
    }

    public void setProducerDistributor(final ArrayList<Producer> producerDistributor) {
        this.producerDistributor = producerDistributor;
    }

    public boolean getNeedsProducer() {
        return needsProducer;
    }

    public void setNeedsProducer(final boolean needsProducer) {
        this.needsProducer = needsProducer;
    }


    /**
     * Add a consumer to the list of contracts
     * @param consumer the consumer who has a new contract with this distributor
     */
    private void addConsumers(final Consumer consumer) {
        consumers.add(consumer);
    }

    /**
     * Calculate the profit of a distributor
     * @return the profit
     */
    private long calculateProfit() {
        return Math.round(Math.floor(Constants.PROFIT * productionCost));
    }

    /**
     * Calculate the price of the contract
     */
     private void contractPrice() {
         if (consumers.size() == 0) {
            contractCost = infrastructureCost + productionCost + calculateProfit();
        } else {
            contractCost = Math.round(Math.floor(infrastructureCost / consumers.size())
            + productionCost + calculateProfit());
        }
    }

    /**
     * Calculate the contract cost for every Distributor
     * @param distributors the list with the distributors
     */
    private static void calculateContractCost(final ArrayList<Distributor> distributors) {
         for (Distributor distributor : distributors) {
             distributor.contractPrice();
         }
    }

    /**
     * Find the distributor with the smallest bill
     * @param distributors all the distributors from de game
     * @return the distributor with the smallest bill
     */
    public static Distributor smallestPrice(final ArrayList<Distributor> distributors) {
        Distributor.calculateContractCost(distributors);
        long smallestId = distributors.get(0).getId();
        long smallestPrice = distributors.get(0).contractCost;
        Distributor smallestDistributor = distributors.get(0);

        // find the id of the distributors with the smallest price
        for (Distributor distributor : distributors) {
            if ((!distributor.getIsBankrupt()) && (distributor.contractCost < smallestPrice)) {
                smallestPrice = distributor.contractCost;
                smallestId = distributor.getId();
            }
        }

        for (Distributor distributor : distributors) {
            if (distributor.getId() == smallestId) {
                smallestDistributor = distributor;
            }
        }

        // set the value of the facture
        smallestDistributor.setContractCost(smallestPrice);
        return  smallestDistributor;
    }

    /**
     * Chose the contract for the consumer
     * @param consumer the consumer
     * @param distributor the distributor with the smallest bill
     */
    public static void chooseContract(final Consumer consumer,
                                      final Distributor distributor) {
        consumer.setFactureValue(distributor.getContractCost());
        consumer.setDistributor(distributor);
        consumer.setContractTime(distributor.getContractLength());
        distributor.addConsumers(consumer);
    }

    /**
     * Calculate the cost for distributor
     * @return the cost
     */
    public long calculateCosts() {
        return getInfrastructureCost() + getProductionCost() * getConsumers().size();
    }

    /**
     * Calculate the cost of the production
     */
    private double calculateProductionCostPerProducer(final Producer producer) {
        return producer.getEnergyPerDistributor() * producer.getPriceKW();
    }

    /**
     * Choose the producer based on the distributor's strategy
     * @param producers list of all producers
     * @param numberOfMonth the current month
     */
    public void chooseProducer(final ArrayList<Producer> producers, final int numberOfMonth) {
        ArrayList<Producer> sortedProducers;
        if (getProducerStrategy().equals(Constants.GREEN)) {
            Contex contex = new Contex(new GreenStrategy());
            sortedProducers = contex.executeStrategy(producers);
        } else if (getProducerStrategy().equals(Constants.PRICE)) {
            Contex contex = new Contex(new PriceStrategy());
            sortedProducers = contex.executeStrategy(producers);
        } else {
            Contex contex = new Contex(new QuantityStrategy());
            sortedProducers = contex.executeStrategy(producers);
        }

        long energy = 0;
        for (Producer producer : sortedProducers) {
            if (!producerDistributor.contains(producer) && !producer.isFull(numberOfMonth)) {
                producerDistributor.add(producer);
                producer.addDistributor(this);
                energy += producer.getEnergyPerDistributor();
            }

            if (energy >= energyNeededKW) {
                needsProducer = false;
                return;
            }
        }
    }

    private void calculateProductionCost() {
        long cost = 0;

        for (Producer producer : producerDistributor) {
            cost += calculateProductionCostPerProducer(producer);
        }

        productionCost = Math.round(Math.floor(cost / Constants.PROD_COST));
    }

    /**
     * Calculate the production cost for every distributor
     * @param distributors the distributors which are in the simulation
     */
    public static void calculateProductionCostForAllDistributor(
            final ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            distributor.calculateProductionCost();
        }
    }

    /**
     * Get a list of the distributors without producer
     * @param distributors the list of all the distributors (with and without producer)
     * @param producers the lsit of all producers
     * @param month the current month
     * @return the list of distributors without producer
     */
    public static ArrayList<Distributor> getDistributorWithNoProducer(
            final ArrayList<Distributor> distributors,
            final ArrayList<Producer> producers,
            final int month) {
        ArrayList<Distributor> distributorWithNoProducer = new ArrayList<>();
        for (Distributor distributor : distributors) {
            if (distributor.getNeedsProducer()) {
                distributor.chooseProducer(producers, month);
                distributorWithNoProducer.add(distributor);
            }
        }

        return distributorWithNoProducer;
    }
}
