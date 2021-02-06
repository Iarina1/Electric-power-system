package fileinput;

import java.util.ArrayList;

public final class InputInitialData {
    private ArrayList<InputConsumers> consumers;
    private ArrayList<InputDistributors> distributors;
    private ArrayList<InputProducers> producers;


    public ArrayList<InputConsumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<InputConsumers> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<InputDistributors> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<InputDistributors> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<InputProducers> getProducers() {
        return producers;
    }

    public void setProducers(final ArrayList<InputProducers> producers) {
        this.producers = producers;
    }
}
