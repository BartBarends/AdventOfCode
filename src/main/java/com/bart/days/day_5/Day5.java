package com.bart.days.day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day5 {

    private static final Pattern pattern = Pattern.compile("move (\\d*) from (\\d*) to (\\d*)");

    public static String solvePartOne() {
        final Map<Integer, Stack<Character>> stacks = buildStacks();

        final File stepsFile = new File(Day5.class.getResource("/day_5/steps.txt").getPath());

        if(!stepsFile.exists()) {
            return "";
        }

        try(final BufferedReader reader = new BufferedReader(new FileReader(stepsFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final Matcher matcher = pattern.matcher(line);

                matcher.find();

                final int amountToMove = Integer.parseInt(matcher.group(1));
                final int from = Integer.parseInt(matcher.group(2));
                final int to = Integer.parseInt(matcher.group(3));

                for (int i = 1; i <= amountToMove; i++) {
                    final char old = stacks.get(from).pop();
                    stacks.get(to).push(old);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final StringBuilder result = new StringBuilder();

        for(Stack<Character> stack : stacks.values()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    private static Map<Integer, Stack<Character>> buildStacks() {
        final File stacksFile = new File(Day5.class.getResource("/day_5/initial_stacks.txt").getPath());

        final Map<Integer, Stack<Character>> toReturn = new HashMap<>();

        if(!stacksFile.exists()) {
            return toReturn;
        }

        int index = 1;

        try(final BufferedReader reader = new BufferedReader(new FileReader(stacksFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final Stack<Character> stack = new Stack<>();
                for (int i = 0; i < line.length(); i++) {
                    stack.push(line.charAt(i));
                }

                toReturn.put(index, stack);

                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return toReturn;
    }

    public static String solvePartTwo() {
        final Map<Integer, Stack<Character>> stacks = buildStacks();

        final File stepsFile = new File(Day5.class.getResource("/day_5/steps.txt").getPath());

        if(!stepsFile.exists()) {
            return "";
        }

        try(final BufferedReader reader = new BufferedReader(new FileReader(stepsFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final Matcher matcher = pattern.matcher(line);

                matcher.find();

                final int amountToMove = Integer.parseInt(matcher.group(1));
                final int from = Integer.parseInt(matcher.group(2));
                final int to = Integer.parseInt(matcher.group(3));

                final List<Character> toMove = new ArrayList<>();

                for (int i = 1; i <= amountToMove; i++) {
                    final char old = stacks.get(from).pop();
                    toMove.add(old);
                }

                Collections.reverse(toMove);

                for(Character character : toMove) {
                    stacks.get(to).push(character);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final StringBuilder result = new StringBuilder();

        for(Stack<Character> stack : stacks.values()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
