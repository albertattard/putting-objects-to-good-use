/*
 * #%L
 * Putting Objects to Good Use
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.oop.part3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.jcip.annotations.NotThreadSafe;

import com.javacreed.examples.oop.part3.tax.ChainedTaxCalculator;
import com.javacreed.examples.oop.part3.tax.EcoTaxCalculator;
import com.javacreed.examples.oop.part3.tax.ImportTaxCalculator;
import com.javacreed.examples.oop.part3.tax.SalesTaxCalculator;
import com.javacreed.examples.oop.part3.tax.TaxCalculator;

@NotThreadSafe
public class ItemBuilder {

  private final String name;
  private final BigDecimal originalPrice;
  private final List<TaxCalculator> taxCalculators = new ArrayList<>();

  public ItemBuilder(final String name, final BigDecimal originalPrice) {
    this.name = name;
    this.originalPrice = originalPrice;
  }

  public ItemBuilder(final String name, final String originalPrice) throws NumberFormatException {
    this(name, new BigDecimal(originalPrice));
  }

  public ItemBuilder addAllTaxCalculators() {
    addSalesTaxCalculator();
    addImportTaxCalculator();
    addEcoTaxCalculator();
    return this;
  }

  public ItemBuilder addEcoTaxCalculator() {
    return addTaxCalculator(new EcoTaxCalculator());
  }

  public ItemBuilder addImportTaxCalculator() {
    return addTaxCalculator(new ImportTaxCalculator());
  }

  public ItemBuilder addSalesTaxCalculator() {
    return addTaxCalculator(new SalesTaxCalculator());
  }

  public ItemBuilder addTaxCalculator(final TaxCalculator taxCalculator) {
    this.taxCalculators.add(taxCalculator);
    return this;
  }

  public Item build() {
    return new Item(name, originalPrice, new ChainedTaxCalculator(taxCalculators));
  }
}
