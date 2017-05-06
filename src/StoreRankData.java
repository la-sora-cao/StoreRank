/**
 * Created by Sora on 2017-05-06.
 *
 * @author Sora
 */
public class StoreRankData {

    private String appName;

    private float coverage;

    private float active;

    public StoreRankData(String appName, float coverage, float active) {
        this.appName = appName;
        this.coverage = coverage;
        this.active = active;
    }

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public String toCsv() {
    	return appName + "," + coverage + "," + active;
	}
}
