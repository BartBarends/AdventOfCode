package com.bart.days.day_2;

public enum WinLoseDraw {
    WIN(6),
    LOSE(0),
    DRAW(3);

    private final int points;

    WinLoseDraw(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
