/**
 * Created by Sora on 2017-05-06.
 *
 * @author Sora
 */
public class StoreWeightData {

	private int weight;

	private float coverage;

	private float active;

	public StoreWeightData(int weight, float coverage, float active) {
		this.weight = weight;
		this.coverage = coverage;
		this.active = active;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public float getCoverage() {
		return coverage;
	}

	public void setCoverage(float coverage) {
		this.coverage = coverage;
	}

	public float getActive() {
		return active;
	}

	public void setActive(float active) {
		this.active = active;
	}
}
