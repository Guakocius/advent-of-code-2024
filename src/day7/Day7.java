package day7;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    private Day7() {}

    public static void main(String[] args) throws IOException {
        NumberPair<long[], long[][]> read = parse();
        long[] result = read.first;
        long[][] numbers = read.second;

        eval(result, numbers, false);

        eval(result, numbers, true);
    }

    private static NumberPair<long[], long[][]> parse() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day7/inputs7.txt"));
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();

        long[] results = new long[lines.size()];
        long[][] numbers = new long[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String[] p = lines.get(i).split(": ");
            results[i] = Long.parseLong(p[0]);
            numbers[i] = Arrays.stream(p[1].split(" ")).mapToLong(Long::parseLong).toArray();
        }
        return new NumberPair<>(results, numbers);
    }
    private static long[] operations(long[] n, boolean validOperation) {
        List<Long> result = new ArrayList<>();
        result.add(n[0]);

        for (int i = 1; i < n.length; i++) {
            List<Long> l = new ArrayList<>();
            for (long num : result) {
                l.add(num + n[i]);
                l.add(num * n[i]);
                if (validOperation) {
                    l.add(Long.parseLong(num + "" + n[i]));
                }
            }
            result = l;
        }
        return result.stream().mapToLong(Long::longValue).toArray();
    }

    private static void eval(long[] results, long[][] numbers, boolean validOperation) {
        long result = 0;
        for(int i = 0; i < results.length; i++) {
            long num = results[i];
            long[] numArray = numbers[i];

            if (contains(operations(numArray, validOperation), num)) {
                result += num;
            }
        }
        System.out.println(result);
    }
    private static boolean contains(long[] numbers, long number) {
        for (long n : numbers) {
            if (n == number) {
                return true;
            }
        }
        return false;
    }
}