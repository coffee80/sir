package sir;

/**
 * A very rough estimate. Halves the sample, calculates the average. To be improved.
 * @author Ferdinando Primerano, 23/04/2020
 *
 */
public class AverageProgressionProposer implements ProgressionProposer
{

	private static AverageProgressionProposer instance = new AverageProgressionProposer();
	
	private AverageProgressionProposer() {}
	
	public static AverageProgressionProposer getInstance() {return instance;}
	
	@Override
	public Progression propose(State[] states, double thres) 
	{
		int vthres = (int) (states.length * thres);
		Progression[] progressions = new Progression[states.length-vthres];
		//stub progression
		Progression res = new Progression(0,0);
		
		int k =0;
		for(int i=vthres;i<states.length;i++)
		{
			progressions[k] = states[i].difference(states[i-1]);
			k++;
		}
			
		//I have the progressions. Let's average them
		double sum = 0;
		for(int i=0;i<progressions.length;i++)
			sum+=progressions[i].beta;
		res.beta = sum / ((double)progressions.length);
		
		sum = 0;
		for(int i=0;i<progressions.length;i++)
			sum+=progressions[i].gamma;
		res.gamma = sum / ((double)progressions.length);
			
		return res;
	}
	
	
	
}
