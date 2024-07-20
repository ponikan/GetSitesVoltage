import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WrightMistakes {
    public static void wrightAllWorkingSitesFile(){
        try(BufferedWriter bufferedWriterMistakeSites = new BufferedWriter(new FileWriter(Constant.FILENAME_POWER_MISTAKES))){
            Map<String, Cabinet> mistakesMap = GetMistakesMap.mistakesMap();
            for (Map.Entry<String, Cabinet> entry : mistakesMap.entrySet()){
                bufferedWriterMistakeSites.write(entry.getKey() + " : " + entry.getValue()+ "\n");
            }
        } catch (IOException e) {
            throw new MyException("что то не то", e);
        }
    }
}
