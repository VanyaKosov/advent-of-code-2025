import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);

        List<Long> ranges = new ArrayList<>();
        for (String line : lines) {
            if (line.equals("")) {
                break;
            }

            String[] parts = line.split("-");
            ranges.add(Long.valueOf(parts[0]));
            ranges.add(Long.valueOf(parts[1]));
        }

        long answer = 0;
        List<Long> usedRanges = new ArrayList<>();
        for (int i = 0; i < ranges.size(); i += 2) {
            long start = ranges.get(i);
            long end = ranges.get(i + 1);

            long result = checkRange(usedRanges, start, end);
            answer += result;
        }

        System.out.println(answer);
    }

    private static long checkRange(List<Long> usedRanges, long start, long end) {
        if (start > end)
            return 0;

        for (int j = 0; j < usedRanges.size(); j += 2) {
            long usedStart = usedRanges.get(j);
            long usedEnd = usedRanges.get(j + 1);
            if (start >= usedStart && end <= usedEnd)
                return 0;
            if (start > usedEnd || end < usedStart)
                continue;

            if (usedStart >= start && usedEnd <= end) {
                return checkRange(usedRanges, start, usedStart - 1) + checkRange(usedRanges, usedEnd + 1, end);
            }

            if (start >= usedStart) {
                start = usedEnd + 1;
                continue;
            }

            end = usedStart - 1;
        }

        usedRanges.add(start);
        usedRanges.add(end);
        return end - start + 1; // 3-5 -> 3 IDs
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
