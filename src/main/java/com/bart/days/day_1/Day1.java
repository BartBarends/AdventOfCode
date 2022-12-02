package com.bart.days.day_1;

import java.io.*;
import java.util.*;

public class Day1 {
    /**
     * Get the result of the calory problem of day 1
     * @param amount Amount of elves to take into account
     * @return Total amount of calories for the elves
     */
    public static int getResult(int amount) {
        final File inputFile = new File(Day1.class.getResource("/day_one_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;
        final List<Integer> amounts = new ArrayList<>();

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                if(line.isEmpty()) {
                    amounts.add(current);
                    current = 0;
                    continue;
                }

                current += Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            Collections.sort(amounts);
            Collections.reverse(amounts);
            return amounts.stream().limit(amount).mapToInt(x -> x).sum();
    }
}
