import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import fileinput.Input;
import fileinput.InputConsumers;
import fileinput.InputDistributors;
import fileinput.InputProducers;
import fileoutput.Output;
import myclasses.instances.Consumer;
import myclasses.instances.Distributor;
import myclasses.factory.PeopleFactory;
import myclasses.instances.Producer;
import myclasses.Simulation;

import java.io.File;
import java.util.ArrayList;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        // read from json
        File fileInput = new File(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(fileInput, Input.class);

//      -------------------------------------------- input ----------------------------------------
        // the list with consumers
        ArrayList<Consumer> consumers = new ArrayList<>();
        for (InputConsumers inputConsumers : input.getInitialData().getConsumers()) {
            Consumer consumer = (Consumer) PeopleFactory.getInstance()
                    .createPeople(Constants.CONSUMER);
            consumer.setClass(inputConsumers.getId(),
                    inputConsumers.getInitialBudget(),
                    inputConsumers.getMonthlyIncome());
            consumers.add(consumer);
        }

        // the list with distributors
        ArrayList<Distributor> distributors = new ArrayList<>();
        for (InputDistributors inputDistributors : input.getInitialData().getDistributors()) {
            Distributor distributor = (Distributor) PeopleFactory.getInstance()
                    .createPeople(Constants.DISTRIBUTOR);
            distributor.setClass(inputDistributors.getId(),
                    inputDistributors.getContractLength(),
                    inputDistributors.getInitialBudget(),
                    inputDistributors.getInitialInfrastructureCost(),
                    inputDistributors.getEnergyNeededKW(), inputDistributors.getProducerStrategy());
            distributors.add(distributor);
        }

        // the list with producers
        ArrayList<Producer> producers = new ArrayList<>();
        for (InputProducers inputProducers : input.getInitialData().getProducers()) {
            Producer producer = new Producer(inputProducers.getId(), inputProducers.getEnergyType(),
                    inputProducers.getMaxDistributors(), inputProducers.getPriceKW(),
                    inputProducers.getEnergyPerDistributor());
            producers.add(producer);
        }

//      -------------------------------------- implementation ----------------------------------

        Output output = Simulation.startSimulation(consumers, distributors, producers, input);

//      ------------------------------------------ output ---------------------------------------
        // create json for output
        File outFile = new File(args[1]);
        objectMapper.writeValue(outFile, output);
    }
}
