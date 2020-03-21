package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.ValueType;

import java.util.Objects;

@ValueType
public final class Kana {
  public static final Kana EMPTY = new Kana("");
  private final String value;

  private Kana(String symbol) {
    value = symbol;
  }

  public static Kana of(String symbol) {
    return new Kana(symbol);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Kana kana = (Kana) o;
    return Objects.equals(value, kana.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
