import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var lines = readInput(args[0]);
        char[][] map = new char[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            map[row] = lines.get(row).toCharArray();
        }

        simulate(map, lines.get(0).indexOf("S"));

        System.out.println(analyze(map));
    }

    private static int analyze(char[][] map) {
        int answer = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] == '^') {
                    if (map[row - 1][col] == '|' && map[row][col - 1] == '|' && map[row][col + 1] == '|') {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private static void simulate(char[][] map, int startCol) {
        map[1][startCol] = '|';
        for (int row = 1; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {

                if (map[row][col] == '|' && row < map.length - 1 && map[row + 1][col] == '.') {
                    map[row + 1][col] = '|';
                }

                if (row < map.length - 1 && map[row + 1][col] == '^' && map[row][col] == '|') {
                    map[row + 1][col - 1] = '|';
                    map[row + 1][col + 1] = '|';
                }
            }
        }
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
