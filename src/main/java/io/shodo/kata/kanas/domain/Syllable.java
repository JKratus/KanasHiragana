package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.ValueType;

import java.util.Objects;
import java.util.function.Function;

@ValueType
public final class Syllable implements Comparable<Syllable> {
  private final String value;

  private Syllable(String symbol) {
    value = symbol;
  }

  public static Syllable of(String symbol) {
    return new Syllable(symbol);
  }

  public boolean isLength(int syllableLength) {
    return value.length() == syllableLength;
  }

  protected TranslatableSyllable isContaining(String word) {
    if (word.contains(value))
      return TranslatableSyllable.of(word.indexOf(value), Syllable.of(value));
    return TranslatableSyllable.NONE;
  }

  public <R> R map(Function<String, R> mapper) {
    return mapper.apply(this.value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Syllable syllable = (Syllable) o;
    return Objects.equals(value, syllable.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int compareTo(Syllable o) {
    return -Integer.compare(this.value.length(), o.value.length());
  }
}
