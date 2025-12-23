import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        int answer = 0;

        for (String line : lines) {
            int[] batteries = Arrays.stream(line.split("")).mapToInt(a -> Integer.parseInt(a)).toArray();
            int tens = largestIdx(batteries, 0, batteries.length - 1);
            int ones = largestIdx(batteries, tens + 1, batteries.length);

            answer += batteries[tens] * 10 + batteries[ones];
        }

        System.out.println(answer);
    }

    private static int largestIdx(int[] batteries, int i, int max) {
        int largestIdx = i;
        for (; i < max; i++) {
            if (batteries[i] > batteries[largestIdx])
                largestIdx = i;
        }

        return largestIdx;
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
