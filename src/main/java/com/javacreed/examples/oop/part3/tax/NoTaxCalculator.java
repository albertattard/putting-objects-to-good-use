package com.javacreed.examples.oop.part3.tax;

import java.math.BigDecimal;

public class NoTaxCalculator implements TaxCalculator {

  // Uncomment to convert to singleton.
  // public static final TaxCalculator INSTANCE = new NoTaxCalculator();
  //
  // private NoTaxCalculator() {
  // }

  @Override
  public BigDecimal calculateTax(final BigDecimal value) {
    return BigDecimal.ZERO;
  }

}
