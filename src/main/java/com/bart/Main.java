package com.bart;

import com.bart.days.day_1.Day1;
import com.bart.days.day_2.Day2;
import com.bart.days.day_3.Day3;
import com.bart.days.day_4.Day4;
import com.bart.days.day_5.Day5;
import com.bart.days.day_6.Day6;
import com.bart.days.day_7.Day7;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.log(Level.INFO, "Day 1 - Part 1: {0}", Day1.getResult(1));
        logger.log(Level.INFO, "Day 1 - Part 2: {0}", Day1.getResult(3));
        logger.log(Level.INFO, "Day 2 - Part 1: {0}", Day2.solvePartOne());
        logger.log(Level.INFO, "Day 2 - Part 2: {0}", Day2.solvePartTwo());
        logger.log(Level.INFO, "Day 3 - Part 1: {0}", Day3.solvePartOne());
        logger.log(Level.INFO, "Day 3 - Part 2: {0}", Day3.solvePartTwo());
        logger.log(Level.INFO, "Day 4 - Part 1: {0}", Day4.solvePartOne());
        logger.log(Level.INFO, "Day 4 - Part 2: {0}", Day4.solvePartTwo());
        logger.log(Level.INFO, "Day 5 - Part 1: {0}", Day5.solvePartOne());
        logger.log(Level.INFO, "Day 5 - Part 2: {0}", Day5.solvePartTwo());
        logger.log(Level.INFO, "Day 6 - Part 1: {0}", Day6.solvePartOne());
        logger.log(Level.INFO, "Day 6 - Part 2: {0}", Day6.solvePartTwo());
        logger.log(Level.INFO, "Day 7 - Part 1: {0}", Day7.solvePartOne());
        logger.log(Level.INFO, "Day 7 - Part 2: {0}", Day7.solvePartTwo());
    }
}