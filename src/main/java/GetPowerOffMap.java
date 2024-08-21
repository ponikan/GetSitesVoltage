import java.util.Map;
import java.util.TreeMap;

public class GetPowerOffMap {

    public static Map<String, Cabinet> powerOffMap(){

       Map<String,Cabinet> powerOffMap = new TreeMap<>();
        Map<String, Cabinet> pointsMap = GetVoltageMap.getVoltageMap();

        for (Map.Entry<String,Cabinet> entry : pointsMap.entrySet()){




            if(entry.getValue().getPoint1() !=null && entry.getValue().getPoint2() !=null){
                Class classPoint1 = entry.getValue().getPoint1().getClass();
                Class classPoint2 = entry.getValue().getPoint2().getClass();

                if (classPoint1.isNestmateOf(Double.class) && classPoint2.isNestmateOf(Double.class) ){
                    Double voltage1 = (Double) entry.getValue().getPoint1();
                    Double voltage2 = (Double) entry.getValue().getPoint2();
                    if(voltage1 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage1 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE
                            || voltage2 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage2 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (classPoint1.isNestmateOf(String.class) && classPoint2.isNestmateOf(Double.class)){
                    Double voltage2 = (Double) entry.getValue().getPoint2();
                    if (voltage2 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage2 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE){
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (classPoint1.isNestmateOf(Double.class) && classPoint2.isNestmateOf(Double.class)) {
                    Double voltage1 = (Double) entry.getValue().getPoint1();
                    if (voltage1 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage1 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE){
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            } else if (entry.getValue().getPoint1() !=null && entry.getValue().getPoint2() == null){
                Class classPoint1 = entry.getValue().getPoint1().getClass();
                if (classPoint1.isNestmateOf(Double.class)){
                    Double voltage1 = (Double) entry.getValue().getPoint1();
                    if(voltage1 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage1 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            else if (entry.getValue().getPoint1() == null && entry.getValue().getPoint2() !=null){
                Class classPoint2 = entry.getValue().getPoint2().getClass();
                if (classPoint2.isNestmateOf(Double.class)){
                    Double voltage2 = (Double) entry.getValue().getPoint2();
                    if(voltage2 < Constant.TRESHOLD_UP_POWER_VOLTAGE && voltage2 > Constant.TRESHOLD_DOWN_POWER_VOLTAGE){
                        System.out.println("OK!!!");
                        powerOffMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        return powerOffMap;
    }
}
