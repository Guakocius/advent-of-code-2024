package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day5 {
    private Day5() {
    }

    public static void main(String[] args) throws IOException {
        String filename = "src/day5/inputs5.txt";
        var result = parse(filename);
        var rules = result[0];
        var updates = result[1];

        part1((Map<Integer, List<Integer>>) rules, (List<List<Integer>>) updates);
        part2((Map<Integer, List<Integer>>) rules, (List<List<Integer>>) updates);

    }

    public static Object[] parse(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        }
        Map<Integer, List<Integer>> rules = new HashMap<>();
        List<List<Integer>> updates = new ArrayList<>();

        List<int[]> tuples = new ArrayList<>();
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

    public static boolean isValid(Map<Integer, List<Integer>> rules, List<Integer> update) {
        for (int i = 1; i < update.size(); i++) {
            int current = update.get(i);
            int previous = update.get(i - 1);
            if (rules.containsKey(current) && rules.get(current).contains(previous)) {
                return false;
            }
        }
        return true;
    }

    public static List<Integer> sort(Map<Integer, List<Integer>> rules, List<Integer> update) {
        List<Integer> sorted = new ArrayList<>(update);
        sorted.sort(Comparator.comparingInt(x -> {
            List<Integer> rule = rules.getOrDefault(x, Collections.emptyList());
            return rule.stream().mapToInt(y -> Collections.frequency(update, y)).sum();
        }));
        return sorted;
    }
    public static void part1(Map<Integer, List<Integer>> rules, List<List<Integer>> updates) {
        int sum = updates.stream()
                .filter(update -> isValid(rules, update))
                .mapToInt(update -> update.get(update.size() / 2))
                .sum();
        System.out.println(sum);
    }
    public static void part2(Map<Integer, List<Integer>> rules, List<List<Integer>> updates) {
        int sum = updates.stream()
                .filter(update -> !isValid(rules, update))
                .mapToInt(update -> sort(rules, update).get(update.size() / 2))
                .sum();
        System.out.println(sum);
    }
}


