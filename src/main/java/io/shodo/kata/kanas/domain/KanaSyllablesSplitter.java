package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.DomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.addAll;
import static java.util.stream.Collectors.toList;

@DomainService
public class KanaSyllablesSplitter {
  private final Syllables syllablesOfOneCharacters;
  private final Syllables syllablesOfTwoCharacters;
  private final Syllables syllablesOfThreeCharacters;

  public KanaSyllablesSplitter(KanaDictionary kanaDictionary) {
    syllablesOfOneCharacters = kanaDictionary.syllablesWithLength(1);
    syllablesOfTwoCharacters = kanaDictionary.syllablesWithLength(2);
    syllablesOfThreeCharacters = kanaDictionary.syllablesWithLength(3);
  }

  public Syllables split(String word) {

    List<TranslatableSyllable> translatableSyllables3 = syllablesOfThreeCharacters.isContainingIn(word);
    word = removeSyllables(translatableSyllables3, word);
    List<TranslatableSyllable> translatableSyllables2 = syllablesOfTwoCharacters.isContainingIn(word);
    word = removeSyllables(translatableSyllables2, word);
    List<TranslatableSyllable> translatableSyllables1 = syllablesOfOneCharacters.isContainingIn(word);

    List<Syllable> orderedSyllables = concat(translatableSyllables1, translatableSyllables2, translatableSyllables3).stream()
            .sorted()
            .map(TranslatableSyllable::getSyllable)
            .collect(toList());

    return Syllables.of(orderedSyllables);
  }

  private List<TranslatableSyllable> concat(List<TranslatableSyllable> oneCharacters, List<TranslatableSyllable> twoCharacters, List<TranslatableSyllable> threeCharacters) {
    List<TranslatableSyllable> finalSyllables = new ArrayList<>();
    addAll(finalSyllables, oneCharacters.toArray(new TranslatableSyllable[0]));
    addAll(finalSyllables, twoCharacters.toArray(new TranslatableSyllable[0]));
    addAll(finalSyllables, threeCharacters.toArray(new TranslatableSyllable[0]));
    return finalSyllables;
  }

  private String removeSyllables(List<TranslatableSyllable> translatableSyllables, String word) {
    return translatableSyllables.stream()
            .map(TranslatableSyllable::getSyllable)
            .map(syllable -> syllable.map(Function.identity()))
            .reduce(word, (finalWord, syllable) -> finalWord.replace(syllable, ""));
  }
}
