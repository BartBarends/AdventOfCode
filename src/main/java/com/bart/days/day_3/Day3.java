package com.bart.days.day_3;

import com.bart.days.day_1.Day1;
import com.bart.days.day_2.RockPaperScissors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    private static String allLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int solvePartOne() {
        final File inputFile = new File(Day1.class.getResource("/day_three_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final int length = line.length();
                final int half = length / 2;

                if(half == 0) return 0;

                final char[] one = line.substring(0, half).toCharArray();
                final char[] two = line.substring(half).toCharArray();

                current += getValueOfDuplicates(one, two);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    public static int solvePartTwo() {
        final File inputFile = new File(Day3.class.getResource("/day_three_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;
        final List<String> group = new ArrayList<>();

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                group.add(line);

                if(group.size() != 3) continue;

                //We now have a group of 3
                int toAdd = getValueOfCommon(group);
                current += toAdd;

                group.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    private static int getValueOfCommon(List<String> group) {
        for(char char1 : group.get(0).toCharArray()) {
            for(char char2 : group.get(1).toCharArray()) {
                for(char char3 : group.get(2).toCharArray()) {
                    if(char1 == char2 && char1 == char3) return allLetters.indexOf(char1) + 1;
                }
            }
        }

        return 0;
    }

    private static int getValueOfDuplicates(char[] one, char[] two) {
        for (char char1 : one) {
            for(char char2 : two) {
                if(char1 == char2) {
                    final int index = allLetters.indexOf(char1) + 1;
                    return index;
                }
            }
        }
        return 0;
    }
}
