package com.javacreed.examples.oop.part3;

import com.javacreed.examples.oop.part3.tax.BasicTaxCalculator;

public class Example3 {
  public static void main(final String[] args) {
    final Item medicine = new Item("Imported Medicine", "8.40", new BasicTaxCalculator("0.03"));
    System.out.println(medicine);
  }
}
