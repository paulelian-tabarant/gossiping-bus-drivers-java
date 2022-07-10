import java.util.List;

public class Gossiping {
    public String computeDuration(List<String> routes) {
        GossipedRecord gossipedRecord = new GossipedRecord(routes.size());

        int stopsLength = getStopsLength(routes);
        for (int stopCount = 1; stopCount <= stopsLength; stopCount++) {
            if (sameStop(routes, 0, 1, stopCount)) {
                gossipedRecord.setDriversMet(0, 1);
            }
            if (routes.size() > 2) {
                if (sameStop(routes, 0, 1, stopCount)) {
                    gossipedRecord.update(0, 1, 2);
                }
                if (sameStop(routes, 1, 2, stopCount)) {
                    gossipedRecord.update(1, 2, 0);
                }
                if (sameStop(routes, 0, 2, stopCount)) {
                    gossipedRecord.update(0, 2, 1);
                }
            }

            if (gossipedRecord.isComplete())
                return Integer.toString(stopCount);
        }
        return "never";
    }

    private int getStopsLength(List<String> routes) {
        return routes.get(0).length() / 2 + 1;
    }

    private char getStop(String stops, int stopIndex) {
        return stops.charAt(2 * (stopIndex - 1));
    }

    private boolean sameStop(List<String> routes, int firstDriver, int secondDriver, int busStop) {
        String firstBusStops = routes.get(firstDriver), secondBusStops = routes.get(secondDriver);

        return getStop(firstBusStops, busStop) == getStop(secondBusStops, busStop);
    }
}
