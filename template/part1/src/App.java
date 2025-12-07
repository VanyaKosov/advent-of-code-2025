import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        System.out.println(lines);
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
