package com.bart.days.day_4;

import com.bart.days.day_1.Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    public static int solvePartOne() {
        final File inputFile = new File(Day4.class.getResource("/day_four_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final String[] split = line.split(",");
                final int[] sideOne = Arrays.stream(split[0].split("-")).mapToInt(Integer::parseInt).toArray();
                final int[] sideTwo = Arrays.stream(split[1].split("-")).mapToInt(Integer::parseInt).toArray();

                final List<Integer> rangeOne = IntStream.range(sideOne[0], sideOne[1] + 1).boxed().toList();
                final List<Integer> rangeTwo = IntStream.range(sideTwo[0], sideTwo[1] + 1).boxed().toList();

                if(fullyContains(rangeOne, rangeTwo)) {
                    current++;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    public static int solvePartTwo() {
        final File inputFile = new File(Day4.class.getResource("/day_four_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final String[] split = line.split(",");
                final int[] sideOne = Arrays.stream(split[0].split("-")).mapToInt(Integer::parseInt).toArray();
                final int[] sideTwo = Arrays.stream(split[1].split("-")).mapToInt(Integer::parseInt).toArray();

                final List<Integer> rangeOne = IntStream.range(sideOne[0], sideOne[1] + 1).boxed().toList();
                final List<Integer> rangeTwo = IntStream.range(sideTwo[0], sideTwo[1] + 1).boxed().toList();

                if(overlaps(rangeOne, rangeTwo)) {
                    current++;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    private static boolean fullyContains(List<Integer> sideOne, List<Integer> sideTwo) {
        if(sideOne.size() > sideTwo.size()) return sideOne.containsAll(sideTwo);

        return sideTwo.containsAll(sideOne);
    }

    private static boolean overlaps(List<Integer> sideOne, List<Integer> sideTwo) {
        if(sideOne.size() > sideTwo.size()) return sideOne.stream().anyMatch(sideTwo::contains);

        return sideTwo.stream().anyMatch(sideOne::contains);
    }
}
