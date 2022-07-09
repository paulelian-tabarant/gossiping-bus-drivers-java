import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GossipingTest {
    private final Gossiping gossiping = new Gossiping();

    // Two buses that encounter, or not

    @Test
    void gossiped() {
        List<String> routes = List.of(
                "2 1",
                "2 3"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("1", duration);
    }

    @Test
    void neverGossiped() {
        List<String> routes = List.of(
                "2 1",
                "4 3"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("never", duration);
    }

    @Test
    void gossiped5() {
        List<String> routes = List.of(
                "1 3 2 5 4",
                "2 4 3 7 4"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("5", duration);
    }

    // Test with three buses
    @Test
    void threeNeverGossiped() {
        List<String> routes = List.of(
                "2 1",
                "4 3",
                "1 4"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("never", duration);
    }

    @Test
    void whenEveryBusEncountersTheOtherOnesInAStopTheyAllHadTimeToGossip() {
        List<String> routes = List.of(
                "2 1 3",
                "4 2 3",
                "4 1 5"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("3", duration);
    }

    /*
    @Test
    void firstTest() {
        List<String> routes = List.of(
                "1 2 3",
                "2 3 4"
        );
        Gossiping gossiping = new Gossiping();
        String duration = gossiping.computeDuration(routes);

        assertEquals("never", duration);
    }
*/
}
