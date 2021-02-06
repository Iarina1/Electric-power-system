package fileoutput;

import java.util.ArrayList;

public final class Output {
    private ArrayList<OutputConsumers> consumers;
    private ArrayList<OutputDistributors> distributors;
    private ArrayList<OutputProducers> energyProducers;


    public Output(final ArrayList<OutputConsumers> consumers,
                   final ArrayList<OutputDistributors> distributors,
                  final ArrayList<OutputProducers> energyProducers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public ArrayList<OutputConsumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<OutputConsumers> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<OutputDistributors> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<OutputDistributors> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<OutputProducers> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(final ArrayList<OutputProducers> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
