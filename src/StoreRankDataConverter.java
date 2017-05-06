import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sora on 2017-05-06.
 *
 * @author Sora
 */
public class StoreRankDataConverter {

	public static List<StoreRankData> convert(String raw) {
        String head = "应用名称覆盖率 活跃率\n";
        String end = "分享";

        String core = raw.substring(raw.indexOf(head) + head.length(), raw.indexOf(end));
        String[] rankRaw = core.split("\n");

        List<StoreRankData> list = new ArrayList<StoreRankData>();
        for (int i = 0; i < rankRaw.length - 1; i++) {
            Pattern pattern;
            // remove index of apps
            if (i + 1 < 10) {
                pattern = Pattern.compile("([0123456789]?)(.*?)([0123456789.]+)(%)(.*?)(%)");
            }
            else {
                pattern = Pattern.compile("([0123456789]{2})(.*?)([0123456789.]+)(%)(.*?)(%)");

            }
            Matcher matcher = pattern.matcher(rankRaw[i]);
            if (!matcher.matches()) {
				System.out.print(rankRaw[i]);
            }

            String appName = matcher.group(2);
            float coverage = Float.parseFloat(matcher.group(3));
            float active = Float.parseFloat(matcher.group(5));

            StoreRankData data = new StoreRankData(appName, coverage, active);

            list.add(data);
        }

        return list;
    }

}
