
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WrightAllWorkingSitesFile {
    public static void wrightAllWorkingSitesFile(){
        try(BufferedWriter bufferedWriterAllWorkingSites = new BufferedWriter(new FileWriter(Constant.FILENAME_OUT))){
           Map<String, Cabinet> voltageMap = GetVoltageMap.getVoltageMap();
            for (Map.Entry<String, Cabinet> entry : voltageMap.entrySet()){
                bufferedWriterAllWorkingSites.write(entry.getKey() + " : " + entry.getValue()+ "\n");
            }
        } catch (IOException e) {
            throw new MyException("что то не то", e);
        }
    }
}
