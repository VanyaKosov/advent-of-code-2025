import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var input = readInput(args[0]).get(0);
        long[] ranges = Arrays.stream(input.split(",|-")).mapToLong(a -> Long.parseLong(a)).toArray();
        long answer = 0;
        for (int i = 0; i < ranges.length; i += 2) {
            for (long num = ranges[i]; num <= ranges[i + 1]; num++) {
                String number = num + "";
                if (number.length() % 2 != 0)
                    continue;
                int mid = number.length() / 2;
                if (number.substring(0, mid).equals(number.substring(mid))) {
                    answer += num;
                }
            }
        }

        System.out.println(answer);
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
