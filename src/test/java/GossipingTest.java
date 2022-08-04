import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GossipingTest {
    private final Gossiping gossiping = new Gossiping();

    @Nested
    class TwoBuses {
        @Test
        @DisplayName("when two buses with two stops meet at the first stop, it should return 1")
        void twoBusesWithTwoStopsMeetAtTheFirstStop() {
            List<String> routes = List.of(
                    "2 1",
                    "2 3"
            );
            String duration = gossiping.computeDuration(routes);

            assertEquals("1", duration);
        }

        @Test
        @DisplayName("when two buses have different numbers at each stop, it should return 'never'")
        void neverGossiped() {
            List<String> routes = List.of(
                    "2 1",
                    "4 3"
            );
            String duration = gossiping.computeDuration(routes);

            assertEquals("never", duration);
        }

        @Test
        @DisplayName("when two buses have the same stop number for the first time at the stop number 5, it should return 5")
        void gossiped5() {
            List<String> routes = List.of(
                    "1 3 2 5 4",
                    "2 4 3 7 4"
            );
            String duration = gossiping.computeDuration(routes);

            assertEquals("5", duration);
        }
    }

    @Nested
    class ThreeBuses {
        @Test
        @DisplayName("when three buses have different numbers at each stop, it should return 'never'")
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
        @DisplayName("when one bus encounters another at stop 1, encounters the last one at stop 2 and the other two meet at stop 3, it should return 3")
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
        @DisplayName("when one bus encounters the two others at stops 1 et 3 and re-encounters one of them at stop 5, it should return 5")
        void whenOneBusEncountersTheTwoOthersAndReEncountersOneOfThemItShouldReturnTheIndexWhenHeDidThisLastEncounter() {
            List<String> routes = List.of(
                    "3 1 2 3 3",
                    "3 2 3 1 3",
                    "4 2 3 4 5"
            );
            String duration = gossiping.computeDuration(routes);

            assertEquals("5", duration);
        }
    }

    @Nested
    class NBuses {
        @Test
        @DisplayName("when every one of the five buses know the gossips of the four others after 7 days, it should return 7")
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
}
