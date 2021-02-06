package myclasses.strategy;

import myclasses.instances.Producer;

import java.util.ArrayList;

public final class GreenStrategy implements Strategy {
    @Override
    public ArrayList<Producer> chooseStrategy(final ArrayList<Producer> producers) {
        return new ArrayList<>(Producer.sortGreen(producers));
    }
}
