package com.bart.days.day_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 {
    public static int solvePartOne() {
        final List<List<Integer>> grid = getGrid();

        int result = 0;

        for(int y = 0; y < grid.size(); y++) {
            final List<Integer> line = grid.get(y);
            //x is 0, meaning this is the top row
            if(y == 0) {
                result += line.size();
                continue;
            }

            //x is the size of the grid, meaning this is the bottom row
            if(y == grid.size() - 1) {
                result += line.size();
                continue;
            }

            for(int x = 0; x < line.size(); x++) {
                //y is 0, meaning this is the left
                if(x == 0) {
                    result += 1;
                    continue;
                }

                //y is the size of the line, meaning this is the right row
                if(x == line.size() -1) {
                    result += 1;
                    continue;
                }

                int currentTree = line.get(x);
                if(
                        !isBiggerTreeAbove(x, y, currentTree, grid) ||
                        !isBiggerBelow(x, y, currentTree, grid) ||
                        !isBiggerTreeLeft(x, y, currentTree, grid) ||
                        !isBiggerTreeRight(x, y, currentTree, grid)) {
                    result++;
                }
            }
        }

        return result;
    }

    private static boolean isBiggerTreeLeft(int x, int y, int currentTree, List<List<Integer>> grid) {
        final List<Integer> line = grid.get(y);

        for(int xIndex = 0; xIndex < x; xIndex++) {
            if(line.get(xIndex) >= currentTree) return true;
        }

        return false;
    }

    private static boolean isBiggerTreeRight(int x, int y, int currentTree, List<List<Integer>> grid) {
        final List<Integer> line = grid.get(y);

        for(int xIndex = line.size() - 1; x < xIndex; xIndex--) {
            if(line.get(xIndex) >= currentTree) return true;
        }

        return false;
    }

    private static boolean isBiggerTreeAbove(int x, int y, int currentTree, List<List<Integer>> grid) {
        for(int yIndex = 0; yIndex < y; yIndex++) {
            int tree = grid.get(yIndex).get(x);
            if(tree >= currentTree) return true;
        }

        return false;
    }

    private static boolean isBiggerBelow(int x, int y, int currentTree, List<List<Integer>> grid) {
        for(int yIndex = grid.size() - 1; y < yIndex; yIndex--) {
            if(grid.get(yIndex).get(x) >= currentTree) return true;
        }

        return false;
    }

    private static List<List<Integer>> getGrid() {
        final File inputFile = new File(Day8.class.getResource("/day_eight_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return new ArrayList<>();
        }

        final List<List<Integer>> grid = new ArrayList<>();

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final List<Integer> gridLine = new ArrayList<>(Arrays.stream(line.split("")).map(Integer::parseInt).toList());

                grid.add(gridLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return grid;
    }

    public static int solvePartTwo() {
        final List<List<Integer>> grid = getGrid();
        int toReturn = 0;
        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.get(0).size(); col++) {
                int score =
                        judgeSide(row, col, 1, 0, grid) *
                        judgeSide(row, col, -1, 0, grid) *
                        judgeSide(row, col, 0,-1, grid) *
                        judgeSide(row, col,0,1, grid);
                if (score > toReturn) {
                    toReturn = score;
                }
            }
        }

        return toReturn;
    }


    private static int judgeSide(int startY, int startX, int xMod, int yMod, List<List<Integer>> grid) {
        int y = startY + yMod;
        int x = startX + xMod;

        if(y == 0 && yMod == -1) return 0;
        if(x == 0 && xMod == -1) return 0;
        if(y == grid.size() && xMod == 1) return 0;
        if(x == grid.get(0).size() && yMod == -1) return 0;

        int result = 0;

        while (y >= 0 && y <= grid.size() - 1 && x >= 0 && x <= grid.get(0).size() - 1) {
            result++;
            if (grid.get(y).get(x) >= grid.get(startY).get(startX)) {
                break;
            }

            y += yMod;
            x += xMod;
        }

        return result;
    }
}
