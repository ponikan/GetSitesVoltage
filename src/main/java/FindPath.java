import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindPath {

    public static String findPath(String directory){
        List<Path> paths;
        try {
           paths = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new MyException("Что то не так с входным файлом",e);
        }
        String path = null;
        for (Path str : paths){
            if (str.toString().contains("MML_Task_Result_power")){
              path = str.toString();
            }

        }

        return path;
    }
}
