package myclasses;

import fileinput.Input;
import fileoutput.Output;
import myclasses.instances.Consumer;
import myclasses.instances.Distributor;
import myclasses.instances.Producer;

import java.util.ArrayList;

public final class Simulation {
    private Simulation() {
    }

    /**
     * Implement the task
     * @param consumers the list of the consumers
     * @param distributors the list of the distributors
     * @param producers the list of the producers
     * @param input the input file
     * @return
     */
    public static Output startSimulation(final ArrayList<Consumer> consumers,
                                       final ArrayList<Distributor> distributors,
                                       final ArrayList<Producer> producers,
                                       final Input input) {
        // firt month

        // assign producer to every distributor
        for (Distributor distributor : distributors) {
            distributor.chooseProducer(producers, 0);
        }

        // calculate the production cost for every distributor
        Distributor.calculateProductionCostForAllDistributor(distributors);


        for (int i = 0; i <= input.getNumberOfTurns(); i++) {

            // apply the monthly changes
            if (i != 0) {
                MonthlyUpdates.getMonthlyUpdatesConsumerDistributor(consumers, distributors,
                        input, i - 1);
            }

            // find the distributor with the smallest price
            Distributor smallestDistributor = Distributor.smallestPrice(distributors);

            // remove the consumers whose contract is over from the distributor list
            for (Consumer consumer : consumers) {
                if ((consumer.getDistributor() != null) && (consumer.getContractTime() == 0)) {
                    consumer.getDistributor().getConsumers().remove(consumer);
                }
            }

            // assign a contract to every available consumer
            for (Consumer consumer : consumers) {
                if (((consumer.getContractTime() == 0) && (!consumer.getIsBankrupt()))) {
                    Distributor.chooseContract(consumer, smallestDistributor);
                }
            }

            //   calculate the cost for every distributor
            for (Distributor distributor : distributors) {
                if (!distributor.getIsBankrupt()) {
                    distributor.setBudget(distributor.getBudget()
                            - distributor.calculateCosts());
                }
            }

            // calculate de buget for every consumer and distributor
            for (Consumer consumer : consumers) {
                consumer.calculateBuget();
                consumer.setContractTime(consumer.getContractTime() - 1);
            }

            // remove the consumers who are bankrupt
            for (Consumer consumer : consumers) {
                if (consumer.getIsBankrupt()) {
                    consumer.getDistributor().getConsumers().remove(consumer);
                    consumer.setContractTime(0);
                }
            }

            // remove the distributor who are bankrupt
            for (Distributor distributor : distributors) {
                if (distributor.getBudget() < 0) {
                    distributor.getConsumers().clear();
                    distributor.setIsBankrupt(true);
                }
            }

            // monthly updates for the output
            if (i != 0) {
                Producer.updateMonthlyStats(i - 1, producers);
            }
            if (i == input.getNumberOfTurns()) {
                Producer.updateMonthlyStats(i, producers);
            }

            // actualizare producatori
            if (i != 0) {
                MonthlyUpdates.getMonthlyUpdatesProducers(producers, input, i - 1, distributors);
            }
        }

//      ------------------------------------------ output ---------------------------------------
        return GenerateOutput.generateOutput(consumers, distributors, producers, input);
    }
}
