import java.util.Arrays;
import java.util.List;

public class BiddingTest {
    public static void main(String[] args) {
        List<IAgent> agents = Arrays.asList(
                new MockAgent(1, 10, 10, 0), // agent 1 raises twice then stops
                new MockAgent(2, 15, 0),     // agent 2 raises once then stops
                new MockAgent(3, 5, 5, 5, 0) // agent 3 raises three times then stops
        );

        Bidding bidding = new Bidding(agents);
        bidding.run();
    }
}



