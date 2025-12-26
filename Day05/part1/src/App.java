import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);

        List<Long> ranges = new ArrayList<>();
        int i = 0;
        for (String line : lines) {
            i++;
            if (line.equals("")) {
                break;
            }

            String[] parts = line.split("-");
            ranges.add(Long.valueOf(parts[0]));
            ranges.add(Long.valueOf(parts[1]));
        }

        long answer = 0;
        for (; i < lines.size(); i++) {
            long id = Long.parseLong(lines.get(i));
            if (verify(id, ranges))
                answer++;
        }

        System.out.println(answer);
    }

    private static boolean verify(long id, List<Long> ranges) {
        for (int i = 0; i < ranges.size(); i += 2) {
            long from = ranges.get(i);
            long to = ranges.get(i + 1);
            if (id >= from && id <= to) {
                return true;
            }
        }

        return false;
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
