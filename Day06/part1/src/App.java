import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        var info = new ArrayList<List<String>>();
        for (String line : lines) {
            info.add(Arrays.stream(line.split(" ")).filter(a -> !a.equals("")).toList());
        }

        long answer = 0;
        int colLength = info.size();
        for (int col = 0; col < info.get(0).size(); col++) {
            long colAnswer = 0;
            char operation = info.get(colLength - 1).get(col).charAt(0);
            if (operation == '*')
                colAnswer = 1;
            for (int row = 0; row < colLength - 1; row++) {
                long val = Long.parseLong(info.get(row).get(col));
                if (operation == '+') {
                    colAnswer += val;
                } else {
                    colAnswer *= val;
                }
            }

            answer += colAnswer;
        }

        System.out.println(answer);
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
