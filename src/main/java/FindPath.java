import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class FindPath {

    public static String findPath(String directory){
        List<Path> paths;

        try {
           paths = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile).map(Path::toFile)
                   .sorted(Comparator.comparingLong(File::lastModified))
                   .map(File :: toPath)
                   .toList();
        } catch (IOException e) {
            throw new MyException("Что то не так с входным файлом",e);
        }
      // String path = paths.get(0).toString();
        String path = null;
        for (Path str : paths){
            if (str.toString().contains("MML_Task_Result_power")){
              path = str.toString();
            }
        }

        return path;
    }
}
