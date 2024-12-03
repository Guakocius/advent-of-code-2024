package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    private Day1() {}

    public static void main(String[] args) {
        try {
            Scanner fileInput = new Scanner(new File("src/inputs1.txt"));

            List<Integer> inputList1 = new ArrayList<>();
            List<Integer> inputList2 = new ArrayList<>();


            while(fileInput.hasNextLine()) {{
                    int data1 = fileInput.nextInt();
                    int data2 = fileInput.nextInt();
                    inputList1.add(data1);
                    inputList2.add(data2);
                    if(fileInput.hasNextLine()) {
                        fileInput.nextLine();
                    }
                }
            }
            fileInput.close();
            inputList1.sort(Integer::compareTo);
            inputList2.sort(Integer::compareTo);
            int addDiff = 0;

            for(int i = 0; i < inputList1.size(); i++) {
                int diff = Math.abs(inputList1.get(i) - inputList2.get(i));
                addDiff += diff;
                }

            Map<Integer, Integer> simil = new HashMap<>();
            for(int num : inputList2) {
                simil.put(num, simil.getOrDefault(num, 0) + 1);
            }
            int sum = 0;
            for(int num : inputList1) {
                int freq = simil.getOrDefault(num, 0);
                int prod = num * freq;
                sum += prod;
            }
            System.out.println(sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }


    }
}
