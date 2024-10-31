package com.sebhcrookes.sorting.algorithms;

import com.sebhcrookes.sorting.SortArray;
import com.sebhcrookes.sorting.Settings;

public class SelectionSort implements ISortingAlgorithm {

    @Override
    public void runSort(SortArray array) {
        for(int i = 0; i < array.size() - 1; i++) {
            int min = i;

            // Look for the next lowest value
            for(int j = i + 1; j < array.size(); j++) {
                if(array.getValue(j) < array.getValue(min)) {
                    min = j;
                }
            }

            // Move the next lowest value to the current position
            array.swap(i, min, Settings.MILLISECOND_DELAY * 10);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
