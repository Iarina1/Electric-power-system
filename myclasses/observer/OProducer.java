package myclasses.observer;

import myclasses.instances.Distributor;

import java.util.ArrayList;
import java.util.Observable;

public final class OProducer extends Observable {
    private ArrayList<Distributor> distributors;

    /**
     * Update the distributors
     * @param distributorWithNoProducer the list of distributors without producers
     */
    public void updateDistributor(ArrayList<Distributor> distributorWithNoProducer) {
        distributors = new ArrayList<>(distributorWithNoProducer);
        setChanged();
        notifyObservers(distributorWithNoProducer);
    }
}
