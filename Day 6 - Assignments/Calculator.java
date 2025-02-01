package com.wiprotraining.Calculator;

public class Calculator {
    public Calculator() {
    }
 
    public int add(int var1, int var2) {
       return var1 + var2;
    }
 
    public int subtract(int var1, int var2) {
       return var1 - var2;
    }
 
    public int multiply(int var1, int var2) {
       return var1 * var2;
    }
 
    public double divide(int var1, int var2) {
       if (var2 == 0) {
          throw new ArithmeticException("Division by zero is not allowed");
       } else {
          return (double)var1 / (double)var2;
       }
    }
 }