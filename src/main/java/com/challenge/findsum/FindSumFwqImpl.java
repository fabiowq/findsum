package com.challenge.findsum;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FindSumFwqImpl implements FindSum {

    private boolean findRec(int[] ints, int x, int index) {
        int sum = Arrays.stream(ints).sum();
        if (sum == x) {
            return true;
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
        for (int i = 0; i < ints.length; i++) {
            int a = ints[i];
            boolean b = findRec(Arrays.copyOfRange(ints, i, ints.length), x, i);
            if (b) {
                return true;
            }
        }
        return false;
    }

}
