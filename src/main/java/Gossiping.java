import java.util.List;

public class Gossiping {
    private Routes routes;
    private GossipedRecord gossipedRecord;

    public String computeDuration(List<String> routesInput) {
        this.routes = new Routes(routesInput);
        this.gossipedRecord = new GossipedRecord(routes.size());

        int stopsLength = routes.getStopsLength();

        for (int stopCount = 1; stopCount <= stopsLength; stopCount++) {
            for (int driver = 0; driver < routesInput.size(); driver++) {
                checkIfDriverMeetsOtherDriversAtStop(stopCount, driver);
            }

            System.out.println(">> Stop number " + stopCount + " :");
            System.out.println(gossipedRecord);

            if (gossipedRecord.isComplete()) return Integer.toString(stopCount);
        }

        return "never";
    }

    private void checkIfDriverMeetsOtherDriversAtStop(int stopCount, int driver) {
        for (int otherDriver = 0; otherDriver < routes.size(); otherDriver++) {
            if (driver == otherDriver) continue;

            if (routes.sameStop(driver, otherDriver, stopCount)) {
                gossipedRecord.setDriversMet(driver, otherDriver);
            }
        }
    }
}
