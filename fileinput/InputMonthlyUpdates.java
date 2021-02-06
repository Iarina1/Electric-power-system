package fileinput;

import java.util.ArrayList;

public final class InputMonthlyUpdates {
    private ArrayList<InputConsumers> newConsumers;
    private ArrayList<MonthlyUpdatesDistributor> distributorChanges;
    private ArrayList<MonthlyUpdatesProducer> producerChanges;


    public ArrayList<InputConsumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<InputConsumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<MonthlyUpdatesDistributor> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final ArrayList<MonthlyUpdatesDistributor>
                                              distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public ArrayList<MonthlyUpdatesProducer> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(final ArrayList<MonthlyUpdatesProducer> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
