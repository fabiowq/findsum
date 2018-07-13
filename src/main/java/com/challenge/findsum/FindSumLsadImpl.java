package com.challenge.findsum;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Primary
@Component
public class FindSumLsadImpl implements FindSum {

  public boolean find(int[] A, int x) {

    if (A.length <= 1) return false;
    for (int i = 0; i < A.length; i++) {
      int soma = A[i];
      for (int j = i + 1; j < A.length; j++)
        if (verificaArr(soma, j, A, x)) return true;
    }

    return false;
  }

  boolean verificaArr(int somatorio, int pos, int[] A, int x) {

    for (int j = pos; j < A.length; j++) {
      somatorio += A[j];
      if (somatorio == x) return true;
      if (somatorio > x) return false;
      for (int k = j + 1; k < A.length; k++)
        if (verificaArr(somatorio, k, A, x)) return true;
    }

    return false;
  }
}