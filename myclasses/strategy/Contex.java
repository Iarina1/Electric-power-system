package myclasses.strategy;

import myclasses.instances.Producer;

import java.util.ArrayList;

public final class Contex {
    private Strategy strategy;

    public Contex(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Execute the appropiate strategy
     * @param producers the list of the producers
     * @return the list of the sorted producers
     */
    public ArrayList<Producer> executeStrategy(ArrayList<Producer> producers) {
        return strategy.chooseStrategy(producers);
    }
}
