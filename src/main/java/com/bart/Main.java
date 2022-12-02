package com.bart;

import com.bart.days.day_1.Day1;
import com.bart.days.day_2.Day2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.log(Level.INFO, "Day 1 - Part 1: {0}", Day1.getResult(1));
        logger.log(Level.INFO, "Day 1 - Part 2: {0}", Day1.getResult(3));
        logger.log(Level.INFO, "Day 2 - Part 1: {0}", Day2.solvePartOne());
        logger.log(Level.INFO, "Day 2 - Part 2: {0}", Day2.solvePartTwo());
    }
}