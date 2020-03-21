package io.shodo.kata.kanas.domain;

public interface KanaDictionary {
  Kana correlation(Syllable syllable);

  Syllables syllablesWithLength(int length);
}
