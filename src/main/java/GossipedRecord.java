public class GossipedRecord {
    private final boolean[][] gossipedRecord;

    private final int gossipedSize;

    public GossipedRecord(int gossipedSize) {
        this.gossipedSize = gossipedSize;

        gossipedRecord = new boolean[gossipedSize][gossipedSize];

        for (int i = 1; i <= gossipedSize; i++) {
            for (int j = 1; j <= gossipedSize; j++) {
                if (i == j) {
                    gossipedRecord[i - 1][j - 1] = true;
                    continue;
                }
                gossipedRecord[i - 1][j - 1] = false;
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < gossipedSize; i++)
            for (int j = 0; j < gossipedSize; j++)
                if (!gossipedRecord[i][j]) return false;

        return true;
    }

    public void setDriversMet(int firstDriver, int secondDriver) {
        gossipedRecord[firstDriver][secondDriver] = true;
        gossipedRecord[secondDriver][firstDriver] = true;
    }

    public void update(int firstDriver, int secondDriver, int otherDriver) {
        setDriversMet(firstDriver, secondDriver);

        if (gossipedRecord[firstDriver][otherDriver]) {
            gossipedRecord[secondDriver][otherDriver] = true;
        }
        if (gossipedRecord[secondDriver][otherDriver]) {
            gossipedRecord[firstDriver][otherDriver] = true;
        }
    }
}
