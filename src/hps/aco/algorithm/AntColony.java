package hps.aco.algorithm;

public class AntColony 
{
	public AntColony(int NUM_CITIES, float alpha, float beta, float Q, float evaporationQuotient, float[][] edges, int NUM_ANTS)
	{
		this.NUM_CITIES = NUM_CITIES;
		this.alpha = alpha;
		this.beta = beta;
		this.Q = Q;
		this.evaporationQuotient = evaporationQuotient;
		this.initialPheromone = 1.0f/this.NUM_CITIES;
		this.pheromones = new float[NUM_CITIES][NUM_CITIES];
		this.invertedDistance = new float[NUM_CITIES][NUM_CITIES];
		this.edges = edges;
		this.NUM_ANTS = NUM_ANTS;
		ants = new Ant[NUM_ANTS];
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
		for(int i=0;i<NUM_ANTS;++i)
		{
			ants[i] = new Ant();
		}
		
	}
	private float getPheromone(int i, int j)
	{
		return pheromones[i][j];
	}
	
	private void setPheromone (int i, int j, float pheromone)
	{
		pheromones[i][j] = pheromone;
	}
	private int getNextVertex(int currentVertexIndex, double delta)
	{
		int index = 0;
		//implement this, choosing next vertex is tricky, use heuristics here.
		return index;
	}
	
	/*This is a global update.
	 * Updates will be applied as follows:
	 * T(u,v) = (1-evaporationQuotient)*T(u,v) + delT(u,v)
	 * delT(u,v) = Q/ant.pathLength if ant uses edge (u,v) else 0.
	 * We'll update the pheromone trail for every ant and not just the best ant, to explore as much as possible.
	 * */
	private void updatePheromone(Ant ant)
	{
		for(int i=0;i<NUM_CITIES-1;++i)
		{
			int u = ant.vertices[i];
			int v = ant.vertices[i+1];
			updatePheromoneTrail(ant, u, v);
		}
		int u = ant.vertices[NUM_CITIES-1];
		int v = ant.vertices[0];
		updatePheromoneTrail(ant, u, v);
	}

	private void updatePheromoneTrail(Ant ant, int u, int v)
	{
		float oldPheromone = getPheromone(u,v);
		float updatedValue = (1-evaporationQuotient) * oldPheromone + Q/ant.pathLength;
		setPheromone(u,v,updatedValue);
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
	private float evaporationQuotient = 0.0f;
	private final int NUM_CITIES;
	private final int NUM_ANTS;
	private float pheromones[][];
	private float invertedDistance[][];
	private float edges[][];
	private Ant[] ants;
}

class Ant
{
	int[] vertices;// vertices are added in the order they are visited.
	int pathLength;
}
