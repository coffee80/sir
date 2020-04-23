package sir;
/**
 * An object of the class Stage represents a day or similar discrete time in the contagion process
 * @author Ferdinando Primerano - 23/04/2020
 */
public class State 
{


	int n;
	int i;
	int ni;
	int day;


	/**
	 * @param n 	global population
	 * @param i 	infective
	 * @param ni	non infective
	 * @param day	day of the contagion
	 */
	public State(int n, int i, int ni, int day)
	{
		super();
		this.n = n;
		this.i = i;
		this.ni = ni;
		this.day = day;
	}
	
	public double newInfective(Progression progression)
	{
		return (i * progression.beta * s() / n) - (progression.gamma * i); 
	}

	public double newNonInfective(Progression progression)
	{
		return (progression.gamma * i); 
	}

	/**
	 * Calculating the following state given a progression.
	 * Could be written as s2 = s1 + prog
	 * @param progression
	 * @return
	 */
	public State next(Progression progression)
	{
		return new State
				(
					n											,
					(int) (i + newInfective(progression))		,
					(int) (ni + newNonInfective(progression))	,
					day++
				);
	}
	
	/**
	 * Calculating the progression that connects a state, given as a parameter, to the current state
	 * Could be written as prog = s2-s1, where s2 = this and s1 = previous
	 * @param previous: the starting state.
	 * @return
	 */
	public Progression difference(State previous)
	{
		//beta and gamma calculated by inverting the formula

		//there was a problem with using double. The multiplication went negative.
		//I was forced to pass through bigdecimal
		double k = (new java.math.BigDecimal(previous.i).multiply(new java.math.BigDecimal(previous.s())).doubleValue())/(double)n;
		return new Progression
				(
					((this.i - previous.i)+(this.ni - previous.ni)) 
					/ 
					k,
					(this.ni - previous.ni) / previous.i	
				);
	}

	/**
	 * s as "susceptible". may be infected
	 * calculated as the difference between the population and the sum of infective and 
	 * non infective
	 * @return
	 */
	public int s()
	{
		return n-i-ni;
	}

	
	
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + i;
		result = prime * result + n;
		result = prime * result + ni;
		return result;
	}


	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (day != other.day)
			return false;
		if (i != other.i)
			return false;
		if (n != other.n)
			return false;
		if (ni != other.ni)
			return false;
		return true;
	}





	@Override
	public String toString() 
	{
		return "State [n=" + n + ", i=" + i + ", ni=" + ni + ", day=" + day + "]";
	}



	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public int getI() {
		return i;
	}


	public void setI(int i) {
		this.i = i;
	}


	public int getNi() {
		return ni;
	}


	public void setNi(int ni) {
		this.ni = ni;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}
	
	
	
	
	
}
