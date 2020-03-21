package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.ValueType;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@ValueType
public final class Syllables {
  private final List<Syllable> values;

  private Syllables(List<Syllable> syllables) {
    this.values = List.copyOf(syllables);
  }

  public static Syllables of(List<Syllable> syllables) {
    return new Syllables(syllables);
  }

  protected List<TranslatableSyllable> isContainingIn(String word) {
    return values.stream()
            .map(syllable -> syllable.isContaining(word))
            .filter(TranslatableSyllable::notNone)
            .collect(toList());
  }

  public <R> List<R> map(Function<Syllable, R> mapper) {
    return this.values.stream().map(mapper).collect(toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Syllables syllables = (Syllables) o;
    return Objects.equals(values, syllables.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }
}
