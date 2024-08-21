import java.util.Map;
import java.util.TreeMap;

public class GetMistakesMap {
    public static Map<String, Cabinet> mistakesMap() {

        Map<String, Cabinet> mistakesMap = new TreeMap<>();
        Map<String, Cabinet> pointsMap = GetVoltageMap.getVoltageMap();

        for (Map.Entry<String, Cabinet> entry : pointsMap.entrySet()) {

            if (entry.getValue().getPoint1() != null && entry.getValue().getPoint2() != null) {
              if (entry.getValue().getPoint1() instanceof Double && entry.getValue().getPoint2() instanceof Double){
                  Double voltage1 = (Double) entry.getValue().getPoint1();
                  Double voltage2 = (Double) entry.getValue().getPoint2();
                  if (voltage1 < Constant.TRESHOLD_DOWN_POWER_VOLTAGE
                          || voltage1 > Constant.TRESHOLD_UP_POWER_VOLTAGE_MISTAKE || voltage2 < Constant.TRESHOLD_DOWN_POWER_VOLTAGE ||
                          voltage2 > Constant.TRESHOLD_UP_POWER_VOLTAGE_MISTAKE){
                      mistakesMap.put(entry.getKey(), entry.getValue());
                  }
              }else if (entry.getValue().getPoint1() instanceof String && entry.getValue().getPoint2() instanceof String) {
                    System.out.println("NotOK!!! нет измерений");
                        mistakesMap.put(entry.getKey(), entry.getValue());
                } else if (entry.getValue().getPoint1() instanceof String && entry.getValue().getPoint2() instanceof Double) {
                    System.out.println("NotOK!!! нет измерений на 1-й стойке");
                    mistakesMap.put(entry.getKey(), entry.getValue());
                }else if (entry.getValue().getPoint1() instanceof Double && entry.getValue().getPoint2() instanceof String) {

                  System.out.println("NotOK!!! нет измерений на 2-й стойке");
                    mistakesMap.put(entry.getKey(), entry.getValue());
                }
            }else if (entry.getValue().getPoint1() != null && entry.getValue().getPoint2() == null) {
                if (entry.getValue().getPoint1() instanceof String) {
                        mistakesMap.put(entry.getKey(), entry.getValue());
                }else if (entry.getValue().getPoint1() instanceof Double){
                    Double voltage1 = (Double) entry.getValue().getPoint1();
                    if (voltage1 < Constant.TRESHOLD_DOWN_POWER_VOLTAGE || voltage1 > Constant.TRESHOLD_UP_POWER_VOLTAGE_MISTAKE){
                        mistakesMap.put(entry.getKey(), entry.getValue());
                    }
                }

            }
        }
        return mistakesMap;
    }
}
