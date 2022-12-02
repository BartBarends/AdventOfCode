package com.bart.days.day_2;

import com.bart.days.day_1.Day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    /**
     * Get the result of the Rock Paper Scissors problem of day 2
     * @return Total amount of points
     */
    public static int solvePartOne() {
        final File inputFile = new File(Day1.class.getResource("/day_two_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final String[] split = line.split(" ");
                final RockPaperScissors opponent = getType(split[0]);
                final RockPaperScissors yours = getType(split[1]);

                final int gamePoints = getGamePoints(opponent, yours);
                final int score = yours.getPoints();

                current += gamePoints + score;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    public static int solvePartTwo() {
        final File inputFile = new File(Day1.class.getResource("/day_two_challenge_one.txt").getPath());

        if(!inputFile.exists()) {
            return 0;
        }

        int current = 0;

        try(final BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                final String[] split = line.split(" ");
                final RockPaperScissors opponent = getType(split[0]);

                final WinLoseDraw action = getWinLoseDrawType(split[1]);

                final RockPaperScissors yours;

                if(action.equals(WinLoseDraw.WIN)) yours = getLosesTo(opponent);
                else if(action.equals(WinLoseDraw.LOSE)) yours = getWinsFrom(opponent);
                else yours = opponent;

                final int gamePoints = action.getPoints();

                final int score = yours.getPoints();

                current += gamePoints + score;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return current;
    }

    private static int getGamePoints(RockPaperScissors opponent, RockPaperScissors yours) {
        if(opponent.equals(yours)) return WinLoseDraw.DRAW.getPoints();
        if(getWinsFrom(yours).equals(opponent)) return WinLoseDraw.WIN.getPoints();

        return WinLoseDraw.LOSE.getPoints();
    }

    private static RockPaperScissors getType(String input) {
        if(input.equals("A") || input.equals("X")) return RockPaperScissors.ROCK;
        if(input.equals("B") || input.equals("Y")) return RockPaperScissors.PAPER;
        if(input.equals("C") || input.equals("Z")) return RockPaperScissors.SCISSORS;

        return null;
    }

    private static WinLoseDraw getWinLoseDrawType(String input) {
        return switch (input) {
            case "X" -> WinLoseDraw.LOSE;
            case "Y" -> WinLoseDraw.DRAW;
            case "Z" -> WinLoseDraw.WIN;
            default -> null;
        };
    }

    private static RockPaperScissors getWinsFrom(RockPaperScissors input) {
        return switch (input) {
            case ROCK -> RockPaperScissors.SCISSORS;
            case PAPER -> RockPaperScissors.ROCK;
            case SCISSORS -> RockPaperScissors.PAPER;
        };
    }

    private static RockPaperScissors getLosesTo(RockPaperScissors input) {
        return switch (input) {
            case ROCK -> RockPaperScissors.PAPER;
            case PAPER -> RockPaperScissors.SCISSORS;
            case SCISSORS -> RockPaperScissors.ROCK;
        };
    }
}
