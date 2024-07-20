
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// получение спиcка всех строк MML файла для дальнейшей обработки:

public class ReadAllStringsFromMMLFile {
    public static List<String> getAllStrings(){
        List<String> allStrings = new ArrayList<>(); // список для всех строк из исходного файла
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Constant.FILENAME_IN))){
            while (bufferedReader.ready()){
                allStrings.add(bufferedReader.readLine());
            }
        }catch (Exception e){
            throw new MyException("что то не то",e);
        }
        return allStrings;
    }
}
