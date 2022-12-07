package com.bart.days.day_7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {
    public static int solvePartOne() {
        final Map<DirectoryFolder, Integer> folderSizes = getFoldersAndSizes();
        if (folderSizes == null) return 0;

        int toReturn = 0;

        for(Map.Entry<DirectoryFolder, Integer> folderSize : folderSizes.entrySet()) {
            if(folderSize.getValue() > 100000) continue;

            toReturn += folderSize.getValue();
        }

        return toReturn;
    }

    private static Map<DirectoryFolder, Integer> getFoldersAndSizes() {
        final File input = new File(Day7.class.getResource("/day_seven_challenge_one.txt").getPath());

        final DirectoryFolder outmostFolder = new DirectoryFolder("/");
        final Stack<DirectoryFolder> currentFolders = new Stack<>();

        currentFolders.push(outmostFolder);

        if(!input.exists()) {
            return null;
        }

        try(final BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            boolean isInLs = false;
            while((line = reader.readLine()) != null) {
                if(line.equals("$ cd /")) continue;

                if(line.equals("$ ls")) {
                    isInLs = true;
                    continue;
                }

                if(line.equals("$ cd ..")) {
                    currentFolders.pop();
                    isInLs = false;
                    continue;
                }

                //User is cding into a deeper folder
                if(line.startsWith("$ cd ")) {
                    isInLs = false;

                    final Optional<DirectoryFolder> folder = currentFolders.peek().getFolderByName(line.replace("$ cd ", ""));

                    if(folder.isEmpty()) continue;

                    currentFolders.push(folder.get());
                    continue;
                }

                if(!isInLs) continue;

                if(line.startsWith("dir ")) {
                    currentFolders.peek().addItem(new DirectoryFolder(line.replace("dir ", "")));
                    continue;
                }

                //Line is file with size
                final String[] split = line.split(" ");

                final int size = Integer.parseInt(split[0]);
                final String name = split[1];

                currentFolders.peek().addItem(new DirectoryFile(name, size));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final Map<DirectoryFolder, Integer> folderSizes = outmostFolder.getFoldersWithSizes();

        folderSizes.put(outmostFolder, outmostFolder.calculateSize());

        return folderSizes;
    }

    public static int solvePartTwo() {
        final int totalSpace = 70000000;
        final int requiredSpace = 30000000;

        final Map<DirectoryFolder, Integer> folderSizes = getFoldersAndSizes();

        final DirectoryFolder outmostFolder = folderSizes.keySet().stream().filter(x -> x.getName().equals("/")).findFirst().get();

        final int inUseSpace = folderSizes.get(outmostFolder);

        final int unused = totalSpace - inUseSpace;
        final int toDelete = requiredSpace - unused;

        final Map<DirectoryFolder, Integer> possibleDeletions = new HashMap<>();

        for(Map.Entry<DirectoryFolder, Integer> folder : folderSizes.entrySet()) {
            if(folder.getValue() < toDelete) continue;

            possibleDeletions.put(folder.getKey(), folder.getValue());
        }

        Map.Entry<DirectoryFolder, Integer> currentSmallest = null;

        for(Map.Entry<DirectoryFolder, Integer> possible : possibleDeletions.entrySet()) {
            if(currentSmallest == null) {
                currentSmallest = possible;
                continue;
            }

            if(currentSmallest.getValue() > possible.getValue()) {
                currentSmallest = possible;
            }
        }

        return currentSmallest.getValue();
    }
}
