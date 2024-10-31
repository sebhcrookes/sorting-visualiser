package com.sebhcrookes.sorting.algorithms;

import com.sebhcrookes.sorting.SortArray;
import com.sebhcrookes.sorting.Settings;

public class QuickSort implements ISortingAlgorithm {

    public int findPivot(SortArray array, int lowIndex, int highIndex) {
        // Moves all values smaller than the pivot to the left-hand side, and all values greater to the right
        int pivotValue = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivotValue) {
                i++;
                array.swap(i, j, Settings.MILLISECOND_DELAY);
            }
        }

        array.swap(i + 1, highIndex, Settings.MILLISECOND_DELAY * 50);
        return i + 1; // The position of the pivot now that everything has moved
    }

    public void quickSort(SortArray array, int lowIndex, int highIndex) {
        if(lowIndex < highIndex) {
            int pivotPoint = findPivot(array, lowIndex, highIndex);

            // Divide and conquer - do the same to the LHS and the RHS
            quickSort(array, lowIndex, pivotPoint - 1);
            quickSort(array, pivotPoint + 1, highIndex);
        }
    }

    @Override
    public void runSort(SortArray array) {
        quickSort(array, 0, array.size() - 1);
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
}
