import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GetMistakesMap {
    public static Map<String, Cabinet> mistakesMap() {

        Map<String, Cabinet> mistakesMap = new TreeMap<>();
        Map<String, Cabinet> pointsMap = GetVoltageMap.getVoltageMap();

        for (Map.Entry<String, Cabinet> entry : pointsMap.entrySet()) {

            // Class classPoint1 = entry.getValue().getPoint1().getClass();
            //  Class classPoint2 = entry.getValue().getPoint2().getClass();
            if (entry.getValue().getPoint1() != null && entry.getValue().getPoint2() != null) {
                if (entry.getValue().getPoint1() instanceof String && entry.getValue().getPoint2() instanceof String) {
                    String string1 = (String) entry.getValue().getPoint1();
                    String string2 = (String) entry.getValue().getPoint2();
                    System.out.println("NotOK!!! нет измерений");
                        mistakesMap.put(entry.getKey(), entry.getValue());
                } else if (entry.getValue().getPoint1() instanceof String && entry.getValue().getPoint2() instanceof Double) {
                    String string1 = (String) entry.getValue().getPoint1();
                    Double volt1 = (Double) entry.getValue().getPoint2();
                    System.out.println("NotOK!!! нет измерений на 1-й стойке");
                    mistakesMap.put(entry.getKey(), entry.getValue());
                }else if (entry.getValue().getPoint1() instanceof Double && entry.getValue().getPoint2() instanceof String) {
                    Double volt1 = (Double) entry.getValue().getPoint1();
                    String string2 = (String) entry.getValue().getPoint2();
                    System.out.println("NotOK!!! нет измерений на 2-й стойке");
                    mistakesMap.put(entry.getKey(), entry.getValue());
                }

            }else if (entry.getValue().getPoint1() != null && entry.getValue().getPoint2() == null) {
                if (entry.getValue().getPoint1() instanceof String) {
                    String string1 = (String) entry.getValue().getPoint1();
                        mistakesMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return mistakesMap;
    }
}
