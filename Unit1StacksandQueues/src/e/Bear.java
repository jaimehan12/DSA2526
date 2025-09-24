package e;

public class Bear {
	String name;
	double weight;
	int species;
	//hi
	/**
	 * Constructor
	 * @param name
	 * @param weight
	 * @param species
	 */
	public Bear(String name, double weight, int species) {
		super();
		this.name = name;
		this.weight = weight;
		this.species = species;
	}

	@Override
	public String toString() {
		return "Bear [name=" + name + ", weight=" + weight + ", species=" + species + "]";
	}
	
	

}

