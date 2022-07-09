import java.util.ArrayList;
import java.util.List;

public class Gossiping {
    public String computeDuration(List<String> routes) {
        int firstStopsLength = routes.get(0).trim().length();

        Integer gossipedSize = routes.size() == 2 ? 1 : 3;
        List<Boolean> gossiped = new ArrayList<Boolean>(gossipedSize);
        for (int i = 1; i <= gossipedSize; i++)
            gossiped.add(false);

        for (int i = 1; i < firstStopsLength; i++) {
            if (sameStop(routes.get(0), routes.get(1), i)) {
                gossiped.set(0, true);
            }
            if (routes.size() > 2) {
                if (sameStop(routes.get(1), routes.get(2), i)) {
                    gossiped.set(1, true);
                }
                if (sameStop(routes.get(0), routes.get(2), i)) {
                    gossiped.set(2, true);
                }
            }

            if (!gossiped.contains(false))
                return Integer.toString(i);
        }
        return "never";
    }

    private char getStop(String stops, int stopIndex) {
        return stops.charAt(2 * (stopIndex - 1));
    }

    private boolean sameStop(String firstBusStops, String secondBusStops, int busStop) {
        return getStop(firstBusStops, busStop) == getStop(secondBusStops, busStop);
    }
}
