package com.sebhcrookes.sorting;

import java.util.Random;

public class SortArray {

    private ArrayPanel parent;

    private int[] array;
    private int[] barColours;

    public SortArray(ArrayPanel parent, int size) {
        this.parent = parent;

        array = new int[size];
        barColours = new int[size];

        // Create the array of values of each value from 0 to size
        for(int i = 0; i < size; i++) {
            array[i] = i;
            barColours[i] = 0;
        }

        shuffle();
    }

    public void shuffle() {
        Random random = new Random();

        // Randomly shuffles the array
        for(int i = 0; i < array.length; i++) {
            int swapWithIndex = random.nextInt(array.length);
            swap(i, swapWithIndex, Settings.MILLISECOND_DELAY);
        }

        resetColours();

        parent.repaint();
    }

    public void resetColours() {
        for(int i = 0; i < array.length; i++) {
            barColours[i] = 0;
        }
        parent.repaint();
    }

    public void swap(int firstIndex, int secondIndex, long millisecondDelay) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

        // Flash the colour to red of the two bars being swapped
        barColours[firstIndex] = 75;
        barColours[secondIndex] = 75;
        parent.repaint();

        sleepFor(millisecondDelay);
    }

    public void updateSingle(int index, int value, long millisecondDelay) {
        array[index] = value;

        // Flash the colour of the bar being updated
        barColours[index] = 75;
        parent.repaint();

        sleepFor(millisecondDelay);
    }

    public void check() {
        for(int i = 0; i < array.length; i++) {
            updateSingle(i, array[i], 5);
        }
    }

    public static void sleepFor(long milliseconds) {
        long nanoseconds = (long)((double)milliseconds / 1.5 * 1000000); // / 1.5 makes the sleep period shorter (temporary change to speed up algorithms)

        long timeElapsed;
        final long startTime = System.nanoTime();
        do {
            timeElapsed = System.nanoTime() - startTime;
        } while(timeElapsed < nanoseconds);
    }

    public int size() {
        return array.length;
    }

    public int getValue(int i) {
        return array[i];
    }

    public int getColour(int i) {
        return barColours[i];
    }

    public void setColour(int i, int val) {
        barColours[i] = val;
    }
}
