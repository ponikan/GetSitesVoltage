import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WrightNeIsDisconnected {
    public static void wrightNeIsDisconnected(){
        try(BufferedWriter bufferedWriterNeIsDisconnected = new BufferedWriter(new FileWriter(Constant.FILENAME_NE_DISCONNECTED))){
            Map<String, String> neISDisconnectedMap = GetSiteOffMap.neDisconneted();
            for (Map.Entry<String ,String> entry : neISDisconnectedMap.entrySet()){
                bufferedWriterNeIsDisconnected.write(entry.getKey() + ":" + entry.getValue()+ "\n");
            }

        } catch (IOException e) {
            throw new MyException("что то не то", e);
        }
    }
}
