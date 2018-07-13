package com.challenge.findsum;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FindSumLsadImpl implements FindSum {

  public boolean find(int[] A, int x) {
    if (A.length <= 1) return false;
    Arrays.sort(A);
    for (int i = 0; i < A.length; i++ ) {
      int soma = A[i];
      for (int j = i + 1; j < A.length; j++)
        if ( verificaArr(soma, j, A, x) ) return true;
    }

    return false;
  }

  boolean verificaArr(int somatorio, int pos, int[] A, int x) {
    for (int j = pos; j < A.length; j++) {
      somatorio += A[j];
      if (somatorio == x) return true;
      if (somatorio > x) return false;
      if (verificaArr(somatorio, j+1, A, x)) return true;
    }

    return false;
  }
}