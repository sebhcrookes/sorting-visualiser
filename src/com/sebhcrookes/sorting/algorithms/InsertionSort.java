package com.sebhcrookes.sorting.algorithms;

import com.sebhcrookes.sorting.SortArray;
import com.sebhcrookes.sorting.Settings;

public class InsertionSort implements ISortingAlgorithm {

    @Override
    public void runSort(SortArray array) {
        for(int i = 0; i < array.size(); i++) {
            // Get the current value
            int key = array.getValue(i);
            int j = i - 1;

            // Work backwards until it finds its position (in size order)
            while(j >= 0 && array.getValue(j) > key) {
                array.updateSingle(j + 1, array.getValue(j), Settings.MILLISECOND_DELAY);
                j--;
            }

            // Move it to the position we just found
            array.updateSingle(j + 1, key, Settings.MILLISECOND_DELAY);
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
