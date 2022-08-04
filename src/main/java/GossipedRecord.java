public class GossipedRecord {
    private final boolean[][] gossipedRecord;

    private final int gossipedSize;

    public GossipedRecord(int gossipedSize) {
        this.gossipedSize = gossipedSize;

        gossipedRecord = new boolean[gossipedSize][gossipedSize];

        for (int i = 1; i <= gossipedSize; i++) {
            for (int j = 1; j <= gossipedSize; j++) {
                if (i == j) {
                    setDriversGossiped(i - 1, j - 1);
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
        setDriversGossiped(firstDriver, secondDriver);
        setDriversGossiped(secondDriver, firstDriver);

        for (int otherDriver = 0; otherDriver < gossipedSize; otherDriver++) {
            if (gossipedRecord[firstDriver][otherDriver]) {
                setDriversGossiped(secondDriver, otherDriver);
            }
            if (gossipedRecord[secondDriver][otherDriver]) {
                setDriversGossiped(firstDriver, otherDriver);
            }
        }
    }

    private void setDriversGossiped(int secondDriver, int otherDriverTmp) {
        gossipedRecord[secondDriver][otherDriverTmp] = true;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < gossipedSize; i++) {
            for (int j = 0; j < gossipedSize; j++) {
                output += (gossipedRecord[i][j] ? 1 : 0) + "  ";
            }
            output += '\n';
        }
        return output;
    }
}
