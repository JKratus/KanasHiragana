package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.DomainService;

@DomainService
public class KanaTranslator {
  private final KanaDictionary kanaDictionary;
  private final KanaSyllablesSplitter kanaSyllablesSplitter;

  public KanaTranslator(KanaDictionary kanaDictionary, KanaSyllablesSplitter kanaSyllablesSplitter) {
    this.kanaDictionary = kanaDictionary;
    this.kanaSyllablesSplitter = kanaSyllablesSplitter;
  }

  public Kanas translate(String word) {
    final Syllables syllables = kanaSyllablesSplitter.split(word);
    return Kanas.of(syllables.map(kanaDictionary::correlation));
  }
}
