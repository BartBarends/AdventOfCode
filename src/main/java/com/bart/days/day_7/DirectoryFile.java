package com.bart.days.day_7;

public class DirectoryFile extends DirectoryItem {
    private final String name;
    private final int size;

    public DirectoryFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
