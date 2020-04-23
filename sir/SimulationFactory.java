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
	public static Simulation make(State[] states)
	{
		return make(states, AverageProgressionProposer.getInstance());
	}

	public static Simulation make(State[] states, ProgressionProposer proposer)
	{
		return new Simulation(states[states.length-1], proposer.propose(states));
	}

	
}
