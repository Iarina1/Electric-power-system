package myclasses.observer;

import myclasses.instances.Distributor;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class ODistributor implements Observer {
    private ArrayList<Distributor> distributor;

    @Override
    public void update(Observable o, Object arg) {
        distributor = (ArrayList<Distributor>) arg;
        getUpdateDistributor(distributor);
    }

    private void getUpdateDistributor(ArrayList<Distributor> distributorX) {
        Distributor.calculateProductionCostForAllDistributor(distributorX);
    }
}
