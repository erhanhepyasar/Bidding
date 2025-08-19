import java.util.Arrays;
import java.util.List;

public class BiddingTest {
    public static void main(String[] args) {
        List<IAgent> agents = Arrays.asList(
                new MockAgent(1, 10, 10, 0),
                new MockAgent(2, 15, 0),
                new MockAgent(3, 5, 5, 5, 0)
        );

        Bidding bidding = new Bidding(agents);
        bidding.run();
    }
}



