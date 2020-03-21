package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.ValueType;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@ValueType
public final class Kanas {
  private final List<Kana> values;

  private Kanas(List<Kana> kanas) {
    this.values = List.copyOf(kanas);
  }

  public static Kanas of(String symbol) {
    return new Kanas(List.of(Kana.of(symbol)));
  }

  protected static Kanas of(List<Kana> kanas) {
    return new Kanas(kanas);
  }

  public static Kanas write(String symbols) {
    List<Character> characters = symbols.chars().mapToObj(c -> (char) c).collect(toList());
    return new Kanas(characters.stream().map(c -> Kana.of(String.valueOf(c))).collect(toList()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Kanas kanas = (Kanas) o;
    return Objects.equals(values, kanas.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }
}
