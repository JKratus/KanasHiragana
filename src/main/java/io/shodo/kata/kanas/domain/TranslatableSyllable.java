package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.ValueType;

import java.util.Objects;

@ValueType
public final class TranslatableSyllable implements Comparable<TranslatableSyllable> {
  protected static final TranslatableSyllable NONE = new TranslatableSyllable(0, Syllable.of(""));
  private final Integer positionInWord;
  private final Syllable syllable;

  private TranslatableSyllable(Integer positionInWord, Syllable syllable) {
    this.positionInWord = positionInWord;
    this.syllable = syllable;
  }

  public static TranslatableSyllable of(int positionInWord, Syllable syllable) {
    return new TranslatableSyllable(positionInWord, syllable);
  }

  protected boolean notNone() {
    return this != NONE;
  }

  protected Syllable getSyllable() {
    return syllable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final TranslatableSyllable that = (TranslatableSyllable) o;
    return Objects.equals(positionInWord, that.positionInWord) &&
            Objects.equals(getSyllable(), that.getSyllable());
  }

  @Override
  public int hashCode() {
    return Objects.hash(positionInWord, getSyllable());
  }

  @Override
  public int compareTo(TranslatableSyllable t) {
    int result = this.positionInWord.compareTo(t.positionInWord);
    if (result == 0) {
      return this.getSyllable().compareTo(t.getSyllable());
    }
    return result;
  }
}
