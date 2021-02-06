package myclasses.strategy;

import myclasses.instances.Producer;

import java.util.ArrayList;

public final class PriceStrategy implements Strategy {
    @Override
    public ArrayList<Producer> chooseStrategy(ArrayList<Producer> producers) {
        return new ArrayList<>(Producer.sortPrice(producers));
    }
}
