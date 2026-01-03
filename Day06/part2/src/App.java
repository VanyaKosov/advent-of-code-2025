import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        var inputLines = readInput(args[0]);
        var lines = new char[inputLines.size()][inputLines.get(0).length()];
        for (int i = 0; i < inputLines.size(); i++) {
            lines[i] = inputLines.get(i).toCharArray();
        }

        long answer = 0;
        boolean newPart = true;
        long partAnswer = 0;
        char operation = lines[lines.length - 1][0];
        for (int col = 0; col < lines[0].length; col++) {
            if (newPart) {
                newPart = false;
                operation = lines[lines.length - 1][col];
                if (operation == '*') {
                    partAnswer = 1;
                } else {
                    partAnswer = 0;
                }
            }

            long num = readCol(lines, col);
            if (num < 0) {
                newPart = true;
                answer += partAnswer;

                continue;
            }

            if (operation == '*') {
                partAnswer *= num;
            } else {
                partAnswer += num;
            }

            if (col == lines[0].length - 1) {
                answer += partAnswer;
            }
        }

        System.out.println(answer);
    }

    private static long readCol(char[][] lines, int col) {
        boolean empty = true;
        long result = 0;
        for (int row = 0; row < lines.length - 1; row++) {
            char val = lines[row][col];
            if (val == ' ') {
                continue;
            }
            empty = false;

            if (row > 0) {
                result *= 10;
            }

            result += lines[row][col] - '0';
        }

        return empty ? -1 : result;
    }

    private static List<String> readInput(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename));
    }
}
