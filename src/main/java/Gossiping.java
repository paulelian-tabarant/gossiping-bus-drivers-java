import java.util.List;

public class Gossiping {
    public String computeDuration(List<String> routesInput) {
        GossipedRecord gossipedRecord = new GossipedRecord(routesInput.size());
        Routes routes = new Routes(routesInput);

        int stopsLength = routes.getStopsLength();

        for (int stopCount = 1; stopCount <= stopsLength; stopCount++) {
            for (int driver = 0; driver < routesInput.size(); driver++) {
                for (int otherDriver = 0; otherDriver < routesInput.size(); otherDriver++) {
                    if (driver == otherDriver) continue;

                    if (routes.sameStop(driver, otherDriver, stopCount)) {
                        for (int otherDriver2 = 0; otherDriver2 < routesInput.size(); otherDriver2++)
                            gossipedRecord.update(driver, otherDriver, otherDriver2);
                    }
                }
            }

            System.out.println(">> Stop number " + stopCount + " :");
            System.out.println(gossipedRecord);

            if (gossipedRecord.isComplete()) return Integer.toString(stopCount);
        }
        return "never";
    }
}
