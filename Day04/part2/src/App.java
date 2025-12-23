import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        char[][] map = new char[lines.get(0).length()][lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }

        int answer = 0;
        while (true) {
            char[][] newMap = new char[map.length][map[0].length];
            int removed = iterate(map, newMap);
            if (removed == 0)
                break;
            map = newMap;
            answer += removed;
        }

        System.out.print(answer);
    }

    private static int iterate(char[][] oldMap, char[][] newMap) {
        int removed = 0;
        for (int row = 0; row < oldMap.length; row++) {
            for (int col = 0; col < oldMap[0].length; col++) {
                if (oldMap[row][col] != '@') {
                    newMap[row][col] = '.';
                    continue;
                }
                if (check(oldMap, row, col)) {
                    newMap[row][col] = '.';
                    removed++;
                } else {
                    newMap[row][col] = '@';
                }
            }
        }

        return removed;
    }

    private static boolean check(char[][] map, int r, int c) {
        int count = 0;
        for (int row = r - 1; row <= r + 1; row++) {
            for (int col = c - 1; col <= c + 1; col++) {
                if (row == r && col == c)
                    continue;
                if (row < 0 || col < 0 || row == map.length || col == map[0].length)
                    continue;
                if (map[row][col] == '@')
                    count++;
            }
        }

        return count < 4;
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
