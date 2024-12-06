package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {
    private Day6() {}

    private static final Pattern regex = Pattern.compile("\\^|<|v|>");

    public static void main(String[] args) throws IOException {

        var file = parse("src/day6/inputs6.txt");
        part1((String[]) file[0], (Integer) file[1], (Integer) file[2], (Character) file[3]);
        part2((String[]) file[0], (Integer) file[1], (Integer) file[2], (Character) file[3]);
    }

    public static Object[] parse(String filename) throws IOException {
     BufferedReader br = new BufferedReader(new FileReader(filename)) ;
     List<String> linesList = new ArrayList<>();
     String line;
     while ((line = br.readLine()) != null) {
         linesList.add(line);
     }
     String[] lines = linesList.toArray(new String[0]);

     int x = 0, y = 0;
     char dir = ' ';
     for (int i = 0; i < lines.length; i++) {
         Matcher matcher = regex.matcher(lines[i]);
         while(matcher.find()) {
             dir = matcher.group().charAt(0);
             x = matcher.start();
             y = i;
         }
     }
     return new Object[]{lines, x, y, dir};
    }

    private static void part1(String[] lines, int x, int y, char dir) {
        System.out.println(move(lines, x, y, dir)[0]);

    }
    private static void part2(String[] lines, int x, int y, char dir) {
        Object[] r = move(lines, x, y, dir);
        String[] path = (String[]) r[1];

        int sum = 0;
        for (String line : path) {
            for(int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == 'X') {
                    char [] newInput = line.toCharArray();
                   newInput[i] = '#';
                   String newLine = new String(newInput);
                   String[] updatedPath = path.clone();
                   updatedPath[Arrays.asList(path).indexOf(line)] = newLine;

                    if (move(updatedPath, x, y, dir)[0].equals(-1)) {
                        sum += 1;
                    }
                }
            }
        }
        System.out.println(sum);
    }
    private static Object[] move(String[] lines, int x, int y, char dir) {
        Map<String, Integer> map = new HashMap<>();
        int height = lines.length, length = lines[0].length();
        while (x >= 0 && x < length && y >= 0 && y < height) {
            String pos = x + "," + y;
            map.put(pos, map.getOrDefault(pos, 0) + 1);
            if (map.get(pos) == 5) {
                return new Object[]{-1, lines};
            }
            if (lines[y].charAt(x) != '#') {
                lines[y] = lines[y].substring(0, x) + 'X' + lines[y].substring(x + 1);
                switch (dir) {
                    case '>' -> x += 1;
                    case '^' -> y -= 1;
                    case 'v' -> y += 1;
                    case '<' -> x -= 1;
                }
            } else {
                switch (dir) {
                    case '>':
                        x -= 1;
                        dir = 'v';
                        break;
                    case '^':
                        y += 1;
                        dir = '>';
                        break;
                    case 'v':
                        y -= 1;
                        dir = '<';
                        break;
                    case '<':
                        x += 1;
                        dir = '^';
                        break;
                }
            }
        }
        int count = 0;
        for (String row : lines) {
            count += (int) row.chars().filter(c -> c == 'X').count();
        }
        return new Object[]{count, lines};
    }
}