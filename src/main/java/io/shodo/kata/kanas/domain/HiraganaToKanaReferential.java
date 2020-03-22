package io.shodo.kata.kanas.domain;

public interface HiraganaToKanaReferential {
  Kana correlation(Syllable syllable);

  Syllables syllablesWithLength(int length);
}
