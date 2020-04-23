package sir;

/**
 * A ProgressionProposal is a tool to guess the progression from state to state starting
 * from a collection of states
 * @return
 */
public interface ProgressionProposer 
{
	Progression propose(State[] states, double thres);

}
