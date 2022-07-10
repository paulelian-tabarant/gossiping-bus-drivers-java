import java.util.ArrayList;
import java.util.List;

public class Gossiping {
    public String computeDuration(List<String> routes) {
        List<Boolean> gossipedRecord = buildGossipedRecord(routes);

        int stopsLength = getStopsLength(routes);
        for (int stopCount = 1; stopCount <= stopsLength; stopCount++) {
            if (sameStop(routes.get(0), routes.get(1), stopCount)) {
                gossipedRecord.set(0, true);
            }
            if (routes.size() > 2) {
                if (sameStop(routes.get(0), routes.get(1), stopCount)) {
                    gossipedRecord.set(0, true);
                }
                if (sameStop(routes.get(1), routes.get(2), stopCount)) {
                    gossipedRecord.set(1, true);
                }
                if (sameStop(routes.get(0), routes.get(2), stopCount)) {
                    gossipedRecord.set(2, true);
                }
            }

            if (everyDriverGossiped(gossipedRecord))
                return Integer.toString(stopCount);
        }
        return "never";
    }

    private boolean everyDriverGossiped(List<Boolean> gossipedRecord) {
        return !gossipedRecord.contains(false);
    }

    private List<Boolean> buildGossipedRecord(List<String> routes) {
        int gossipedSize = routes.size() == 2 ? 1 : 3;
        List<Boolean> gossipedRecord = new ArrayList<Boolean>(gossipedSize);
        for (int i = 1; i <= gossipedSize; i++)
            gossipedRecord.add(false);

        return gossipedRecord;
    }

    private int getStopsLength(List<String> routes) {
        return routes.get(0).length() / 2 + 1;
    }

    private char getStop(String stops, int stopIndex) {
        return stops.charAt(2 * (stopIndex - 1));
    }

    private boolean sameStop(String firstBusStops, String secondBusStops, int busStop) {
        return getStop(firstBusStops, busStop) == getStop(secondBusStops, busStop);
    }
}
