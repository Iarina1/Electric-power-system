package myclasses;

import common.Constants;
import fileinput.Input;
import fileinput.InputConsumers;
import fileinput.MonthlyUpdatesDistributor;
import fileinput.MonthlyUpdatesProducer;
import myclasses.factory.PeopleFactory;
import myclasses.instances.Consumer;
import myclasses.instances.Distributor;
import myclasses.instances.Producer;
import myclasses.observer.ODistributor;
import myclasses.observer.OProducer;

import java.util.ArrayList;

public final class MonthlyUpdates {

    private MonthlyUpdates() {
    }

    /**
     * Update the consumers and the distributors
     * @param consumers list of all consumers
     * @param distributors list of all consumers
     * @param input the changes
     * @param month the current month
     */
    public static void getMonthlyUpdatesConsumerDistributor(
            final ArrayList<Consumer> consumers, final ArrayList<Distributor> distributors,
            final Input input, final int month) {

        for (InputConsumers inputConsumer : input.getMonthlyUpdates().get(month)
                .getNewConsumers()) {
            PeopleFactory factory = PeopleFactory.getInstance();
            Consumer consumer = (Consumer) factory.createPeople(Constants.CONSUMER);
            consumer.setClass(inputConsumer.getId(),
                    inputConsumer.getInitialBudget(),
                    inputConsumer.getMonthlyIncome());
            consumers.add(consumer);
        }

        for (MonthlyUpdatesDistributor inputDistributor : input.getMonthlyUpdates().get(month)
                .getDistributorChanges()) {
            distributors.get((int) inputDistributor.getId())
                    .setInfrastructureCost(inputDistributor.getInfrastructureCost());
        }
    }

    /**
     * Update the producer
     * @param producers list of all the producers
     * @param input the changes
     * @param month the current month
     */
    public static void getMonthlyUpdatesProducers(final ArrayList<Producer> producers,
                                                  final Input input, final int month,
                                                  final ArrayList<Distributor> distributors) {
        for (MonthlyUpdatesProducer inputProducer : input.getMonthlyUpdates().get(month)
                .getProducerChanges()) {
            producers.get((int) inputProducer.getId())
                    .setEnergyPerDistributor(inputProducer.getEnergyPerDistributor());

            for (Distributor distributor : producers.get((int) inputProducer.getId())
                    .getDistributorProducer()) {
                distributor.setProducerDistributor(new ArrayList<>());
                distributor.setNeedsProducer(true);
            }

            producers.get((int) inputProducer.getId()).setDistributorProducer(new ArrayList<>());
        }

        ArrayList<Distributor> distributorWithNoProducer = Distributor.getDistributorWithNoProducer(
                distributors, producers, month + 1);

        OProducer observable = new OProducer();
        ODistributor observer = new ODistributor();

        observable.addObserver(observer);
        observable.updateDistributor(distributorWithNoProducer);
    }
}
