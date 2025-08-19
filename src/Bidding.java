import java.util.*;

public class Bidding {
    private final Map<IAgent, Integer> bids = new HashMap<>();
    private final List<IAgent> activeAgents = new ArrayList<>();
    private final Set<IAgent> hasBid = new HashSet<>();

    public Bidding(List<IAgent> agents) {
        this.activeAgents.addAll(agents);
        agents.forEach(a -> bids.put(a, 0));
    }

    public void run() {
        System.out.println("=== Bidding Start ===");
        int round = 1;

        do {
            if (activeAgents.isEmpty()) break;

            System.out.println("\n--- Round " + round++ + " ---");
            printBids();
            executeRound();
        } while (!allAtHighest());

        System.out.println("\n=== Bidding End ===");
        printBids();
        System.out.println("Winners: " + winnersAsString());
    }

    private void executeRound() {
        Iterator<IAgent> iterator = activeAgents.iterator();
        while (iterator.hasNext()) {
            IAgent agent = iterator.next();

            if (agentAtHighest(agent) && hasBid.contains(agent)) {
                logStay(agent);
                continue;
            }

            if (attemptBid(agent)) {
                logRaise(agent);
                hasBid.add(agent);
            } else {
                logWithdraw(agent);
                withdrawAgent(agent, iterator);
            }
        }
    }

    private boolean attemptBid(IAgent agent) {
        int newBid = bids.get(agent) + agent.getBidIncrease();
        if (newBid < getHighestBid()) {
            return false;
        }
        bids.put(agent, newBid);
        return true;
    }

    private void withdrawAgent(IAgent agent, Iterator<IAgent> it) {
        it.remove();
        bids.remove(agent);
    }

    private boolean agentAtHighest(IAgent agent) {
        return Objects.equals(bids.get(agent), getHighestBid());
    }

    private int getHighestBid() {
        return bids.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    private boolean allAtHighest() {
        int highest = getHighestBid();
        return activeAgents.stream().allMatch(a -> bids.get(a) == highest);
    }

    private void printBids() {
        System.out.print("Current bids: ");
        activeAgents.forEach(a -> System.out.print("Agent " + a.id() + "=" + bids.get(a) + "  "));
        System.out.println();
    }

    private void logStay(IAgent agent) {
        System.out.println("Agent " + agent.id() + " stays at " + bids.get(agent));
    }

    private void logRaise(IAgent agent) {
        System.out.println("Agent " + agent.id() + " raises to " + bids.get(agent));
    }

    private void logWithdraw(IAgent agent) {
        System.out.println("Agent " + agent.id() + " withdraws.");
    }

    private String winnersAsString() {
        return activeAgents.stream()
                .map(a -> "Agent " + a.id())
                .reduce((a, b) -> a + " " + b)
                .orElse("None");
    }
}
