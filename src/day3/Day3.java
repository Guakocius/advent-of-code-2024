package day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) throws IOException {
        new Puzzle().solve(false);
        new Puzzle().solve(true);
    }
}

class Puzzle {
    void solve(boolean withConditionals) throws IOException {
        try (var input = Objects.requireNonNull(getClass().getResourceAsStream("inputs3.txt"))) {
            var reader = new BufferedReader(new InputStreamReader(input));
            Stream<String> bits = withConditionals ? wrap(reader.lines()) : reader.lines();
            System.out.println(sum(bits));
        }
    }

    long sum(Stream<String> bits) {
        var pattern = Pattern.compile("\\((\\d{1,3}),(\\d{1,3})\\).*");
        return bits
                .flatMap(s -> Arrays.stream(s.split("mul")))
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .mapToLong(m -> Long.parseLong(m.group(1)) * Long.parseLong(m.group(2)))
                .sum();
    }

    @SuppressWarnings("DataFlowIssue")
    Stream<String> wrap(Stream<String> lines) {
        return Arrays.stream(lines
                        .collect(Collectors.joining())
                        .split("do\\(\\)"))
                .map(s -> s.replaceFirst("don't\\(\\).*", ""));
    }
}
