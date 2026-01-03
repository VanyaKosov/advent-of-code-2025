import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// 29257429033 low
public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        long[][] map = new long[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            char[] chars = lines.get(row).toCharArray();
            for (int col = 0; col < chars.length; col++) {
                if (chars[col] == '.' || chars[col] == 'S')
                    continue;
                if (chars[col] == '^')
                    map[row][col] = -1;
            }
        }

        simulate(map, lines.get(0).indexOf("S"));

        System.out.println(analyze(map));
    }

    private static long analyze(long[][] map) {
        long answer = 0;
        int row = map.length - 1;
        for (int col = 0; col < map[0].length; col++) {
            answer += map[row][col];
        }

        return answer;

    }

    private static void simulate(long[][] map, int startCol) {
        map[1][startCol] = 1;
        for (int row = 1; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {

                if (map[row][col] > 0 && row < map.length - 1 && map[row + 1][col] >= 0) {
                    map[row + 1][col] += map[row][col];
                }

                if (row < map.length - 1 && map[row + 1][col] == -1 && map[row][col] > 0) {
                    map[row + 1][col - 1] += map[row][col];
                    map[row + 1][col + 1] += map[row][col];
                }
            }
        }
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
