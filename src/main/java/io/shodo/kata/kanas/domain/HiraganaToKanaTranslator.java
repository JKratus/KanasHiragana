package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.DomainService;

@DomainService
public class HiraganaToKanaTranslator implements Translator {
  private final HiraganaToKanaReferential hiraganaToKanaReferential;
  private final HiraganaSyllabarySplitter hiraganaSyllabarySplitter;

  public HiraganaToKanaTranslator(HiraganaToKanaReferential hiraganaToKanaReferential, HiraganaSyllabarySplitter hiraganaSyllabarySplitter) {
    this.hiraganaToKanaReferential = hiraganaToKanaReferential;
    this.hiraganaSyllabarySplitter = hiraganaSyllabarySplitter;
  }

  @Override
  public Kanas translate(String word) {
    final Syllables syllables = hiraganaSyllabarySplitter.split(word);
    return Kanas.of(syllables.map(hiraganaToKanaReferential::correlation));
  }
}
