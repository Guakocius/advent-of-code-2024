package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    private Day4() {}

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/day4/inputs4.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rows = lines.size();
        int cols = lines.getFirst().length();
        char[][] grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        String target = "XMAS";
        int targetLength = target.length();
        int count = 0;

        int[][] direction = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0},
                {1, 1},
                {-1, -1},
                {1, -1},
                {-1, 1},
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] d : direction) {
                    int dr = d[0], dc = d[1];
                    if (isWordPresent(grid, row, col, dr, dc, target)) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);

        int xShape = countX(grid);
        System.out.println(xShape);
    }
    private static boolean isWordPresent(char[][] grid, int row, int col, int dr, int dc, String target) {
        int rows = grid.length;
        int cols = grid[0].length;
        int targetLength = target.length();

        for (int i = 0; i < targetLength; i++) {
            int newRow = row + i * dr;
            int newCol = col + i * dc;
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private static int countX(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {

                char topLeft = grid[row - 1][col - 1];
                char center = grid[row][col];
                char bottomRight = grid[row + 1][col + 1];
                char topRight = grid[row - 1][col + 1];
                char bottomLeft = grid[row + 1][col - 1];

                boolean diag1 = isValid(topLeft, center, bottomRight);
                boolean diag2 = isValid(topRight, center, bottomLeft);

                if (diag1 && diag2) {
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean isValid(char a, char b, char c) {

        String comb = "" + a + b + c;
        return comb.equals("MAS") || comb.equals("SAM");
    }
}
