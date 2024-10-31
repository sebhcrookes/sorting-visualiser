package com.sebhcrookes.sorting.algorithms;

import com.sebhcrookes.sorting.SortArray;
import com.sebhcrookes.sorting.Settings;

public class BubbleSort implements ISortingAlgorithm {

    @Override
    public void runSort(SortArray array) {
        int len = array.size();

        for(int i = 0; i < len - 1; i++) {
            // Move the leftmost value towards the end of the array
            for(int j = 0; j < len - i - 1; j++) {
                // If we meet a bigger value, start moving that instead
                if(array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, Settings.MILLISECOND_DELAY);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
