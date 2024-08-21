import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GetSiteOffMap {

    public static Map<String, String > neDisconneted(){

        Map<String,String> neDisconnectedMap = new TreeMap<>();
            List<String> disconnectedSitesList = new ArrayList<>();
            List<String> allStrings = ReadAllStringsFromMMLFile.getAllStrings();
            for (int i = 0; i < allStrings.size(); i++) {
                if (allStrings.get(i).startsWith("NE : ") && allStrings.get(i+1).contains("Report : Ne is not connected")){
                    disconnectedSitesList.add(allStrings.get(i).substring(5));
                }
            }
        for (int i = 0; i < disconnectedSitesList.size(); i++) {
            neDisconnectedMap.put(disconnectedSitesList.get(i), "NE is Disconnected");
        }
            return neDisconnectedMap;
    }
}
