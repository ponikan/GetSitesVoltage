
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WrightPowerOffFile {
    public static void wrightAllWorkingSitesFile(){
        try(BufferedWriter bufferedWriterAllWorkingSites = new BufferedWriter(new FileWriter(Constant.FILENAME_POWER_OFF))){
           Map<String, Cabinet> voltageMap = GetPowerOffMap.powerOffMap();
            for (Map.Entry<String, Cabinet> entry : voltageMap.entrySet()){
                bufferedWriterAllWorkingSites.write(entry.getKey() + " : " + entry.getValue()+ "\n");
            }
        } catch (IOException e) {
            throw new MyException("что то не то", e);
        }
    }
}
