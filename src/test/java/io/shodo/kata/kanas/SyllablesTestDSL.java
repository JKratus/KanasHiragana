package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.Syllable;
import io.shodo.kata.kanas.domain.Syllables;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SyllablesTestDSL {
  private List<String> syllables;

  public SyllablesTestDSL(List<String> syllables) {
    this.syllables = new ArrayList<>(syllables);
  }

  protected static SyllablesTestDSL given_syllables_with(String syllable) {
    return new SyllablesTestDSL(List.of(syllable));
  }

  protected SyllablesTestDSL and(String syllable) {
    this.syllables.add(syllable);
    return this;
  }

  protected Syllables build() {
    return Syllables.of(syllables.stream().map(Syllable::of).collect(toList()));
  }
}
