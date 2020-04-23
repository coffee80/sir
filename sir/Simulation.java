package sir;

/**
 * a SIR simulation. Starts with a state and a progression, then calculates a number of following days
 * @author Ferdinando Primerano, 23/04/2020
 *
 */
public class Simulation 
{
	State start;
	Progression progression;
	
	/**
	 * first way - fixed progression, fixed start
	 * @param start
	 * @param progression
	 */
	public Simulation(State start, Progression progression)
	{
		this.start = start;
		this.progression = progression;
	}

	/**
	 * second way. The progression has to be passed as a parameter upon calling "run"
	 * @param start
	 */
	public Simulation(State start)
	{
		this.start = start;
	}

	/**
	 * first strategy - fixed progression
	 * @param days
	 * @return
	 */
	State[] run(int days)
	{
		//reducing the first case to the second
		return this.run(days, this.progression);
	}
	
	/**
	 * secondo strategy. Using an external progression
	 * @param days
	 * @param progression
	 * @return
	 */
	State[] run(int days, Progression progression)
	{
		//elegant, to a point
		State[] res = new State[days+1];
		res[0] = start;
		for(int k=1;k<days;k++) res[k] = res[k-1].next(progression);
		return res;
	}
	
	
}
