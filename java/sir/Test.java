package sir;

public class Test {

	public static void main(String[] args) 
	{
		State[] states = new State[]
		{
			new State(60000000,8514,1635,1),
		    new State(60000000,10590,1872,2),
		    new State(60000000,12839,2274,3),
		    new State(60000000,14955,2705,4),
		    new State(60000000,17750,3407,5),
		    new State(60000000,20603,4144,6),
		    new State(60000000,23073,4907,7),
		    new State(60000000,26062,5444,8),
		    new State(60000000,28710,7003,9),
		    new State(60000000,33190,7845,10),
		    new State(60000000,37860,9161,11),
		    new State(60000000,42681,10897,12),
		    new State(60000000,46638,12500,13),
		    new State(60000000,50418,13509,14),
		    new State(60000000,54030,15146,15),
		    new State(60000000,57521,16865,16),
		    new State(60000000,62013,18526,17),
		    new State(60000000,66414,20084,18),
		    new State(60000000,70065,22407,19),
		    new State(60000000,73880,23809,20),
		    new State(60000000,75528,26211,21),
		    new State(60000000,77635,28157,22),
		    new State(60000000,80572,30002,23),
		    new State(60000000,83049,32193,24),
		    new State(60000000,85388,34439,25),
		    new State(60000000,88274,36358,26),
		    new State(60000000,91246,37702,27),
		    new State(60000000,93187,39360,28),
		    new State(60000000,94067,41519,29),
		    new State(60000000,95262,44160,30),
		    new State(60000000,96877,46749,31),
		    new State(60000000,98273,49304,32),
		    new State(60000000,100269,52002,33),
		    new State(60000000,102253,54110,34),
		    new State(60000000,103616,55900,35),
		    new State(60000000,104291,58197,36),
		    new State(60000000,105418,59737,37),
		    new State(60000000,106607,62334,38),
		    new State(60000000,106962,65472,39),
		    new State(60000000,107771,68154,40),
		    new State(60000000,108257,70715,41),
		    new State(60000000,108237,72991,42),
		    new State(60000000,107709,76248,43),
		    new State(60000000,107699,79628,44)
		};
		
		Simulation simulation = SimulationFactory.make(states);
		
		System.out.println(simulation.run(2)[1]);
		
		
	}

}
