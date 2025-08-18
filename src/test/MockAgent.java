package test;

import main.IAgent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class MockAgent implements IAgent {
    private final int id;
    private final Queue<Integer> bidPlan; // planned raises
    private final Random rand = new Random();

    public MockAgent(int id, Integer... bidIncrements) {
        this.id = id;
        this.bidPlan = new LinkedList<>(Arrays.asList(bidIncrements));
    }

    @Override
    public int getBidIncrease() {
        // If plan empty, sometimes withdraw (0) or small raise
        if (bidPlan.isEmpty()) {
            return rand.nextBoolean() ? 0 : 5;
        }
        return bidPlan.poll();
    }

    @Override
    public int id() {
        return id;
    }
}