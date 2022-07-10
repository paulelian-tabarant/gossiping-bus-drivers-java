import java.util.List;

public class Routes {
    private final List<String> routes;
    public Routes(List<String> routes) {
        this.routes = routes;
    }

    public int getStopsLength() {
        return routes.get(0).length() / 2 + 1;
    }

    private char getStop(String stops, int stopIndex) {
        return stops.charAt(2 * (stopIndex - 1));
    }

    public boolean sameStop(int firstDriver, int secondDriver, int busStop) {
        String firstBusStops = routes.get(firstDriver), secondBusStops = routes.get(secondDriver);

        return getStop(firstBusStops, busStop) == getStop(secondBusStops, busStop);
    }
}
