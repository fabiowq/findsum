package com.challenge.findsum;

import java.util.Arrays;

public class FindSumFwqImpl implements FindSum {

    private boolean findRec(int v, int index, int[] array, int x) {
        int sum = v;
        for (int j = index; j < array.length; j++) {
            sum += array[j];
            if (sum == x) {
                return true;
            }
            if (sum > x) {
                return false;
            }
            for (int k = j + 1; k < array.length; k++) {
                if (findRec(sum, k, array, x)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(int[] array, int x) {
        if (array.length <= 1) {
            return false;
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (findRec(array[i], j, array, x)) return true;
            }
        }
        return false;
    }

}
