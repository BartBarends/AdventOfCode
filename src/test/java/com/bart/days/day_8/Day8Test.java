package com.bart.days.day_8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day8Test {
    @Test
    void partOneSolves() {
        Assertions.assertEquals(21, Day8.solvePartOne());
    }

    @Test
    void partTwoSolves() {
        Assertions.assertEquals(8, Day8.solvePartTwo());
    }
}