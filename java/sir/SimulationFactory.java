package sir;

/**
 * Creates a Simulation starting from the data provided.
 * 
 * @author FP80
 *
 */
public abstract class SimulationFactory 
{

	//Will use the default progression proposer
	public static Simulation make(State[] states, double thres)
	{
		return make(states, AverageProgressionProposer.getInstance(), thres);
	}

	public static Simulation make(State[] states, ProgressionProposer proposer, double thres)
	{
		return new Simulation(states[states.length-1], proposer.propose(states, thres));
	}

	
}
