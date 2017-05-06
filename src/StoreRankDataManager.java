import java.awt.Event;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Marshaller;

import utils.CsvUtils;

/**
 * Created by Sora on 2017-05-06.
 *
 * @author Sora
 */
public class StoreRankDataManager {

	public List<StoreRankData> calculate() {

		List<StoreRankData> calculatedList = new ArrayList<StoreRankData>();

		Map<String, List<StoreWeightData>> map = new HashMap<String, List<StoreWeightData>>();
		for (int i = 0; i < StoreRankRawData.raw.length; i++) {
			List<StoreRankData> list = StoreRankDataConverter.convert(StoreRankRawData.raw[i]);

			for (StoreRankData data : list) {
				StoreWeightData weightData = new StoreWeightData(StoreRankRawData.weights[i], data.getCoverage(), data.getActive());
				if (!map.containsKey(data.getAppName())) {
					map.put(data.getAppName(), new ArrayList<StoreWeightData>());
				}
				map.get(data.getAppName()).add(weightData);
			}
		}

		DecimalFormat df   =new   java.text.DecimalFormat("#.00");

		// calculate average value
		for (String appName : map.keySet()) {
			int sumWeight = 0;
			float sumCoverage = 0, sumActive = 0;
			float avgCoverage, avgActive;
			for (StoreWeightData storeWeightData : map.get(appName)) {
				sumWeight += storeWeightData.getWeight();
				sumCoverage += storeWeightData.getCoverage() * storeWeightData.getWeight();
				sumActive += storeWeightData.getActive() * storeWeightData.getWeight();
			}
			avgCoverage = Float.parseFloat(df.format(sumCoverage  / ((float)sumWeight)));
			avgActive = Float.parseFloat(df.format(sumActive  / ((float)sumWeight)));

			StoreRankData data = new StoreRankData(appName, avgCoverage, avgActive);
			calculatedList.add(data);
		}

		return calculatedList;
	}

	public List<String> convertToCsv(List<StoreRankData> dataList) {
		List<String> csvList = new ArrayList<String>();
		for (StoreRankData data : dataList) {
			csvList.add(data.toCsv());
		}
		return csvList;
	}

	public void export(List<String> dataList) {
		String header = "Name,Coverage,Active";
		dataList.add(0, header);
		boolean isSuccess= CsvUtils.exportCsv(new File("C:/Research/data.csv"), dataList);
		System.out.println(isSuccess);
	}

	public void run() {
		List<StoreRankData> objectList = calculate();
		List<String> csvList = convertToCsv(objectList);
		export(csvList);
	}

}
