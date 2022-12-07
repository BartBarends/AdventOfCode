package com.bart.days.day_7;

import java.util.*;

public class DirectoryFolder extends DirectoryItem {
    private final String name;
    private final List<DirectoryItem> items = new ArrayList<>();

    public DirectoryFolder(String name) {
        this.name = name;
    }

    public int calculateSize() {
        int total = 0;
        for(DirectoryItem item : items) {
            if(item instanceof DirectoryFolder folder) {
                total += folder.calculateSize();
            }
            if(item instanceof DirectoryFile file) {
                total += file.getSize();
            }
        }

        return total;
    }

    public void addItem(DirectoryItem item) {
        items.add(item);
    }

    public Optional<DirectoryFolder> getFolderByName(String name) {
        for(DirectoryItem item : items) {
            if(!(item instanceof DirectoryFolder folder)) continue;

            if(folder.getName().equals(name)) return Optional.of(folder);
        }

        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    public Map<DirectoryFolder, Integer> getFoldersWithSizes() {
        final Map<DirectoryFolder, Integer> toReturn = new HashMap<>();
        for(DirectoryItem item : items) {
            if(!(item instanceof DirectoryFolder folder)) continue;

            toReturn.put(folder, folder.calculateSize());

            toReturn.putAll(folder.getFoldersWithSizes());
        }

        return toReturn;
    }
}
