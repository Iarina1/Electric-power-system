package myclasses;

import fileinput.Input;
import fileoutput.Output;
import fileoutput.OutputConsumers;
import fileoutput.OutputContracts;
import fileoutput.OutputDistributors;
import fileoutput.OutputMonthlyStats;
import fileoutput.OutputProducers;
import myclasses.instances.Consumer;
import myclasses.instances.Distributor;
import myclasses.instances.Producer;

import java.util.ArrayList;

public final class GenerateOutput {
    private GenerateOutput() {
    }

    /**
     * Generate the output
     * @param consumers the list of the consumers
     * @param distributors the list of the distributors
     * @param producers the list of the producers
     * @param input the input file
     * @return the output file
     */
    public static Output generateOutput(final ArrayList<Consumer> consumers,
                                        final ArrayList<Distributor> distributors,
                                        final ArrayList<Producer> producers,
                                        final Input input) {
        // the list of consumers
        ArrayList<OutputConsumers> outputConsumers = new ArrayList<>();
        for (Consumer consumer : consumers) {
            OutputConsumers outputConsumer = new OutputConsumers(consumer.getId(),
                    consumer.getIsBankrupt(), consumer.getBudget());
            outputConsumers.add(outputConsumer);
        }

        // the list of distributors
        ArrayList<OutputDistributors> outputDistributors = new ArrayList<>();
        for (Distributor distributor : distributors) {
            ArrayList<OutputContracts> outputContracts = new ArrayList<>();
            for (Consumer consumer : distributor.getConsumers()) {
                OutputContracts outputContract = new OutputContracts(consumer.getId(),
                        consumer.getFactureValue(), consumer.getContractTime());
                outputContracts.add(outputContract);
            }
            OutputDistributors outputDistributor = new OutputDistributors(distributor.getId(),
                    distributor.getBudget(), distributor.getIsBankrupt(),
                    outputContracts, distributor.getEnergyNeededKW(),
                    distributor.getContractCost(), distributor.getProducerStrategy());
            outputDistributors.add(outputDistributor);
        }

        // the list of producers
        ArrayList<OutputProducers> outputProducers = new ArrayList<>();
        for (Producer producer : producers) {
            ArrayList<OutputMonthlyStats> outputMonthlyStats = new ArrayList<>();
            for (int i = 0; i < input.getNumberOfTurns(); i++) {
                OutputMonthlyStats outputMonthlyStat = new OutputMonthlyStats(
                        i + 1, producer.getMonthlyStats().get((long) i + 1));
                outputMonthlyStats.add(outputMonthlyStat);
            }
            OutputProducers outputProducer = new OutputProducers(producer.getId(),
                    producer.getMaxDistributors(), producer.getPriceKW(),
                    producer.getEnergyType(), producer.getEnergyPerDistributor(),
                    outputMonthlyStats);
            outputProducers.add(outputProducer);
        }

        return new Output(outputConsumers, outputDistributors, outputProducers);
    }
}
