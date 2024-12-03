package day2;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Day2 {
    private Day2() {}

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/inputs2.txt"));
            int countSafe1 = 0;
            int countSafe2 = 0;
            String line;

            while ((line = br.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                boolean safe = true;
                boolean inc = false;
                boolean dec = false;

                for (int i = 0; i < numbers.length - 1; i++) {

                    int num1 = Integer.parseInt(numbers[i]);
                    int num2 = Integer.parseInt(numbers[i + 1]);
                    int diff = Math.abs(num1 - num2);
                    boolean greater = (num1 > num2);
                    boolean less = (num1 < num2);

                    if (greater && diff >= 1 && diff <= 3) {
                        dec = true;
                    } else if (less && diff >= 1 && diff <= 3) {
                        inc = true;
                    } else {
                        safe = false;
                        break;
                    }
                    if (inc && dec) {
                        safe = false;
                        break;
                    }
                }
                if (safe) {
                    countSafe1++;
                    countSafe2++;
                } else {
                    boolean isSafe = false;
                    for(int i = 0; i < numbers.length; i++) {
                        String[] newNumbers = new String[numbers.length - 1];
                        System.arraycopy(numbers, 0, newNumbers, 0, i);
                        System.arraycopy(numbers, i + 1, newNumbers, i, numbers.length - i - 1);

                        boolean newSafe = true;
                        boolean newInc = false;
                        boolean newDec = false;

                        for(int j = 0; j < newNumbers.length - 1; j++) {
                            int num1 = Integer.parseInt(newNumbers[j]);
                            int num2 = Integer.parseInt(newNumbers[j + 1]);
                            int diff = Math.abs(num1 - num2);
                            boolean greater = (num1 > num2);
                            boolean less = (num1 < num2);

                            if (greater && diff >= 1 && diff <= 3) {
                                newDec = true;
                            } else if (less && diff >= 1 && diff <= 3) {
                                newInc = true;
                            } else {
                                newSafe = false;
                                break;
                            }
                            if (newInc && newDec) {
                                newSafe = false;
                                break;
                            }
                        }
                        if (newSafe) {
                            isSafe = true;
                            break;
                        }
                    }
                    if (isSafe) {
                        countSafe2++;
                    }
                }
            }
            System.out.println("Without removal: " + countSafe1);
            System.out.println("With removal: " + countSafe2);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}