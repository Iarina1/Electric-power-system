package myclasses.factory;

import common.Constants;
import myclasses.instances.Consumer;
import myclasses.instances.Distributor;

public final class PeopleFactory {
    private static PeopleFactory instance = null;

    private PeopleFactory() {
    }

    /**
     *
     * @param type
     * @return
     */
    public static People createPeople(final String type) {
        switch (type) {
            case Constants.CONSUMER -> {
                return new Consumer();
            }
            case Constants.DISTRIBUTOR -> {
                return new Distributor();
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Se creaza o instanta
     * @return
     */
    public static PeopleFactory getInstance() {
        if (instance == null) {
            instance = new PeopleFactory();
        }

        return instance;
    }
}
