// This interface has all the agent methods you'll need to do this exercise.
// You should not implement the interface.
public interface IAgent {
    // This will return the amount by which the agent wants to increase its bid by
    // (i.e. how much they want to add onto their bid as it stands so far):
    // You should only call this method once per bid. Calling it more than once for a bid
    // may result in errors.
    int getBidIncrease();

    // a unique id you can use to identify this agent, if necessary:
    int id();
}