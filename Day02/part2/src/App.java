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
                for (int len = 1; len <= number.length() / 2; len++) {
                    String segment = number.substring(0, len);
                    String[] segments = number.split(segment);
                    if (segments.length == 0) {
                        answer += num;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
