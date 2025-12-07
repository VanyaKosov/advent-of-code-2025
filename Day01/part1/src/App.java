import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        int pos = 50;
        int count = 0;
        for (String line : lines) {
            char dir = line.charAt(0);
            int move = 1;
            if (dir == 'L') {
                move = -1;
            }
            move *= Integer.parseInt(line.substring(1));

            pos += move;
            if (pos > 99 || pos < 0) {
                pos -= 100 * (pos / 100);
            }

            if (pos == 0) {
                count++;
            }
        }

        System.out.print(count);
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
