package myclasses.strategy;

import myclasses.instances.Producer;

import java.util.ArrayList;

/**
 * The interface for strategies
 */
public interface Strategy {
    /**
     * Find the list of the producers based on the searching strategy
     * @param producers the list of the producers
     * @return a sorted list of producers
     */
    ArrayList<Producer> chooseStrategy(ArrayList<Producer> producers);
}
