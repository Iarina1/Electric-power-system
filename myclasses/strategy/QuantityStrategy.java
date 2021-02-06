package myclasses.strategy;

import myclasses.instances.Producer;

import java.util.ArrayList;

public final class QuantityStrategy implements Strategy {
    @Override
    public ArrayList<Producer> chooseStrategy(ArrayList<Producer> producers) {
        return new ArrayList<>(Producer.sortQuantity(producers));
    }
}
