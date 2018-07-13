package com.challenge.findsum;

import java.util.Arrays;

public class FindSumFwqImpl implements FindSum {

    private boolean findRec(int[] ints, int x, int index) {
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
            if (sum == x) {
                return true;
            }
        }
        index++;
        if (index >= ints.length) {
            return false;
        }
        int[] ints2 = Arrays.copyOf(ints, ints.length);
        ints2[index] = 0;
        return findRec(ints2, x, index);
    }

    public boolean find(int[] ints, int x) {
        if (ints.length <= 1) {
            return false;
        }
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            for (int j = i; j < ints.length; j++) {
                int[] ints2 = Arrays.copyOfRange(ints, i, ints.length);
                if (j < ints2.length) {
                    ints2[j] = 0;
                }
                boolean b = findRec(ints2, x, i);
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }

}
