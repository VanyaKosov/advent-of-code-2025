import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        long answer = 0;

        for (String line : lines) {
            int[] batteries = Arrays.stream(line.split("")).mapToInt(a -> Integer.parseInt(a)).toArray();

            long num = 0;
            int prevIdx = -1;
            for (int i = 0; i < 12; i++) {
                int idx = largestIdx(batteries, prevIdx + 1, batteries.length - 11 + i);
                prevIdx = idx;
                num += batteries[idx] * pow(10, 11 - i);
            }

            answer += num;
        }

        System.out.println(answer);
    }

    private static int largestIdx(int[] batteries, int i, int max) { // i - inclusive, max - exclusive
        int largestIdx = i;
        for (; i < max; i++) {
            if (batteries[i] > batteries[largestIdx])
                largestIdx = i;
        }

        return largestIdx;
    }

    private static long pow(int base, int pow) {
        if (pow == 0) {
            return 1;
        }

        long result = base;
        for (int i = 1; i < pow; i++) {
            result *= base;
        }

        return result;
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
