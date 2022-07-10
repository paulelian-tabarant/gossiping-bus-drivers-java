import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    @DisplayName("When one bus encounters the two others and re-encounters one of them, it should return the index when he did the last encounter")
    void whenOneBusEncountersTheTwoOthersAndReEncountersOneOfThemItShouldReturnTheIndexWhenHeDidThisLastEncounter() {
        List<String> routes = List.of(
                "3 1 2 3 3",
                "3 2 3 1 3",
                "4 2 3 4 5"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("5", duration);
    }

    @Test
    @DisplayName("When five buses have all gossiped after 7 days, it should return 7")
    void whenFiveBusesHaveAllGossipedAfter7DaysItShouldReturn7() {
        List<String> routes = List.of(
                "3 1 2 3 3 3 5",
                "3 2 4 1 3 5 5",
                "4 2 7 8 5 3 4",
                "6 4 2 6 6 7 1",
                "8 8 3 8 8 2 1"
        );
        String duration = gossiping.computeDuration(routes);

        assertEquals("7", duration);
    }
}
