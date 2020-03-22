package io.shodo.kata.kanas.domain;

import io.shodo.kata.kanas.annotations.DomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Collections.addAll;
import static java.util.stream.Collectors.toList;

@DomainService
public class HiraganaSyllabarySplitter {
  private final Syllables syllablesOfOneCharacter;
  private final Syllables syllablesOfTwoCharacters;
  private final Syllables syllablesOfThreeCharacters;

  public HiraganaSyllabarySplitter(HiraganaToKanaReferential hiraganaToKanaReferential) {
    syllablesOfOneCharacter = hiraganaToKanaReferential.syllablesWithLength(1);
    syllablesOfTwoCharacters = hiraganaToKanaReferential.syllablesWithLength(2);
    syllablesOfThreeCharacters = hiraganaToKanaReferential.syllablesWithLength(3);
  }

  public Syllables split(String word) {

    List<TranslatableSyllable> translatableSyllablesOfTheeCharacters = syllablesOfThreeCharacters.isContainingIn(word);
    word = removeSyllablesOf(word, translatableSyllablesOfTheeCharacters);
    List<TranslatableSyllable> translatableSyllablesOfTwoCharacters = syllablesOfTwoCharacters.isContainingIn(word);
    word = removeSyllablesOf(word, translatableSyllablesOfTwoCharacters);
    List<TranslatableSyllable> translatableSyllablesOfOneCharacter = syllablesOfOneCharacter.isContainingIn(word);

    List<Syllable> orderedSyllables = concat(translatableSyllablesOfOneCharacter, translatableSyllablesOfTwoCharacters, translatableSyllablesOfTheeCharacters).stream()
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

  private String removeSyllablesOf(String word, List<TranslatableSyllable> translatableSyllables) {
    return translatableSyllables.stream()
            .map(TranslatableSyllable::getSyllable)
            .map(syllable -> syllable.map(Function.identity()))
            .reduce(word, (finalWord, syllable) -> finalWord.replace(syllable, ""));
  }
}
