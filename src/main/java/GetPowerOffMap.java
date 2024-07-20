import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GetPowerOffMap {

    public static Map<String, Cabinet> powerOffMap(){

       Map<String,Cabinet> powerOffMap = new HashMap<>();
        Map<String, Cabinet> pointsMap = GetVoltageMap.getVoltageMap();

        for (Map.Entry<String,Cabinet> entry : pointsMap.entrySet()){

            if(entry.getValue().getPoint1() !=null && entry.getValue().getPoint2() !=null){
                Class classPoint1 = entry.getValue().getPoint1().getClass();
                Class classPoint2 = entry.getValue().getPoint2().getClass();
                if (classPoint1.isNestmateOf(Double.class) && classPoint2.isNestmateOf(Double.class)){
                    if((Double) entry.getValue().getPoint1() < Constant.TRESHOLD_POWER_VOLTAGE || (Double) entry.getValue().getPoint2() < Constant.TRESHOLD_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (classPoint1.isNestmateOf(String.class) && classPoint2.isNestmateOf(Double.class)){
                    if ((Double) entry.getValue().getPoint2() < Constant.TRESHOLD_POWER_VOLTAGE){
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (classPoint1.isNestmateOf(Double.class) && classPoint2.isNestmateOf(Double.class)) {
                    if ((Double) entry.getValue().getPoint1() < Constant.TRESHOLD_POWER_VOLTAGE){
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            } else if (entry.getValue().getPoint1() !=null && entry.getValue().getPoint2() == null){
                if (entry.getValue().getPoint1().getClass().isNestmateOf(Double.class)){
                    if((Double) entry.getValue().getPoint1() < Constant.TRESHOLD_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            else if (entry.getValue().getPoint1() == null && entry.getValue().getPoint2() !=null){
                if (entry.getValue().getPoint2().getClass().isNestmateOf(Double.class)){
                    if((Double) entry.getValue().getPoint2() < Constant.TRESHOLD_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        return powerOffMap;
    }
}
