package day6;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    private Day6() {}

    public static void main(String[] args) {

        HashMap<Point, Character> grid = new HashMap<>();
        String filename = "src/day6/inputs6.txt";


    }

    public static Object[] parse(String filename) throws IOException {
        java.util.List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        Map<Integer, java.util.List<Integer>> rules = new HashMap<>();
        java.util.List<java.util.List<Integer>> updates = new ArrayList<>();

        java.util.List<int[]> tuples = new ArrayList<>();
        for (String line : lines) {
            if (line.matches("^\\d+\\|\\d+$")) {
                String[] parts = line.split("\\|");
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                tuples.add(new int[]{x, y});
            }
        }
        for (int[] tuple : tuples) {
            int x = tuple[0];
            int y = tuple[1];
            rules.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
        }
        for (String line : lines) {
            if (line.matches("^[\\d+,]*$")) {
                List<Integer> update = new ArrayList<>();
                String[] parts = line.split(",");
                for (String part : parts) {
                    update.add(Integer.parseInt(part.trim()));
                }
                updates.add(update);
            }
        }
        return new Object[]{rules, updates};
    }
}
