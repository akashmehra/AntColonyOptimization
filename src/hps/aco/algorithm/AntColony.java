package hps.aco.algorithm;

public class AntColony 
{
	public AntColony(int NUM_CITIES, float alpha, float beta, float Q, float[][] edges)
	{
		this.NUM_CITIES = NUM_CITIES;
		this.alpha = alpha;
		this.beta = beta;
		this.Q = Q;
		this.initialPheromone = 1.0f/this.NUM_CITIES;
		this.pheromones = new float[NUM_CITIES][NUM_CITIES];
		this.invertedDistance = new float[NUM_CITIES][NUM_CITIES];
		this.edges = edges;
		init();
	}
	
	private void init()
	{
		for(int i=0;i<NUM_CITIES;++i)
		{
			for(int j=0;j<NUM_CITIES;++j)
			{
				pheromones[i][j] = 1.0f/this.NUM_CITIES;
				invertedDistance[i][j] = 1.0f/edges[i][j];
			}
		}
	}
	private float getPheromone(int i, int j)
	{
		return pheromones[i][j];
	}
	private int getNextVertex(int currentVertexIndex, double delta)
	{
		int index = 0;
		return index;
	}
	private double calculatePheromones()
	{
		float pheromones = 0.0f;
		return pheromones;
	}
	
	/*The probability that an ant k chooses the edge (u,v) is expressed as:
	 * p[k](u,v) = T(u,v)^alpha * n(u,v)^beta/sum_over_all_unvisited_v(T(u,v)^alpha * n(u,v)^beta)
	 * 
	 * */
	private double calculateProbability(int i, int j, float sum)
	{
		double pheromone = (double) getPheromone(i, j);
		double a = (double) getAlpha();
		double desirability = getDesirability(i,j);
		double b = (double) getBeta();
		return (Math.pow(pheromone, a) * Math.pow(desirability,b))/sum;
	}
	
	private double getDesirability(int i, int j) 
	{
		return invertedDistance[i][j];
	}

	// Getters and Setters.
	public float getInitialPheromone() {
		return initialPheromone;
	}
	public void setInitialPheromone(float initialPheromone) {
		this.initialPheromone = initialPheromone;
	}
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public float getBeta() {
		return beta;
	}
	public void setBeta(float beta) {
		this.beta = beta;
	}
	public float getQ() {
		return Q;
	}
	public void setQ(float q) {
		Q = q;
	}
	
	// Private Members.
	private float initialPheromone = 0.0f;
	private float alpha = 0.0f;
	private float beta = 0.0f;
	private float Q = 0.0f;
	private final int NUM_CITIES;
	private float pheromones[][];
	private float invertedDistance[][];
	private float edges[][];

}
