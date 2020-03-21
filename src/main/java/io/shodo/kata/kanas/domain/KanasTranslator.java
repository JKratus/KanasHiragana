package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.DomainService;

@DomainService
public class KanasTranslator {
  private final KanaDictionary kanasDictionary;
  private final KanaSyllablesSplitter kanaSyllablesSplitter;

  public KanasTranslator(KanaDictionary kanasDictionary, KanaSyllablesSplitter kanaSyllablesSplitter) {
    this.kanasDictionary = kanasDictionary;
    this.kanaSyllablesSplitter = kanaSyllablesSplitter;
  }

  public Kanas translate(String word) {
    final Syllables syllables = kanaSyllablesSplitter.split(word);
    return Kanas.of(syllables.map(kanasDictionary::correlation));
  }
}
