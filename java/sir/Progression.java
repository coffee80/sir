package sir;

/**
 *  variation between states. Codified as a stand alone object for ease of use. It's made of just two numbers, beta and gamma
 * @author Ferdinando Primerano
 *
 */
public class Progression 
{
	double beta, gamma;
	
	public Progression(double beta, double gamma)
	{
		this.beta 	= beta;
		this.gamma 	= gamma; 
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	@Override
	public String toString() 
	{
		return "Progression [beta=" + beta + ", gamma=" + gamma + "]";
	}
	
	
	

}
