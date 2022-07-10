import java.util.List;

public class Gossiping {
    public String computeDuration(List<String> routes) {
        GossipedRecord gossipedRecord = new GossipedRecord(routes.size());
        Routes routesObj = new Routes(routes);

        int stopsLength = routesObj.getStopsLength();

        for (int stopCount = 1; stopCount <= stopsLength; stopCount++) {
            if (routesObj.sameStop(0, 1, stopCount)) {
                gossipedRecord.setDriversMet(0, 1);
            }
            if (routes.size() > 2) {
                if (routesObj.sameStop(0, 1, stopCount)) {
                    gossipedRecord.update(0, 1, 2);
                }
                if (routesObj.sameStop(1, 2, stopCount)) {
                    gossipedRecord.update(1, 2, 0);
                }
                if (routesObj.sameStop(0, 2, stopCount)) {
                    gossipedRecord.update(0, 2, 1);
                }
            }

            if (gossipedRecord.isComplete()) return Integer.toString(stopCount);
        }
        return "never";
    }
}
