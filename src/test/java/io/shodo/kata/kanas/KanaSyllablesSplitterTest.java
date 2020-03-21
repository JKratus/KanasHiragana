package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.KanaDictionary;
import io.shodo.kata.kanas.domain.KanaSyllablesSplitter;
import io.shodo.kata.kanas.domain.Syllables;
import io.shodo.kata.kanas.infra.KanasHiraganaSyllabary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KanaSyllablesSplitterTest {
  private final KanaDictionary kanaDictionary = new KanasHiraganaSyllabary();
  private final KanaSyllablesSplitter kanaSyllableSplitter = new KanaSyllablesSplitter(kanaDictionary);

  @Test
  void should_not_split_one_character() {
    Syllables syllables = kanaSyllableSplitter.split("a");

    Syllables expected = SyllablesTestDSL.given_syllables_with("a").build();
    assertThat(syllables).isEqualTo(expected);
  }

  @Test
  void should_detect_syllable_of_two_characters() {
    Syllables syllables = kanaSyllableSplitter.split("koni");

    Syllables expected = SyllablesTestDSL.given_syllables_with("ko").and("ni").build();
    assertThat(syllables).isEqualTo(expected);
  }

  @Test
  void should_detect_syllable_of_three_characters() {
    Syllables syllables = kanaSyllableSplitter.split("konichi");

    Syllables expected = SyllablesTestDSL.given_syllables_with("ko").and("ni").and("chi").build();
    assertThat(syllables).isEqualTo(expected);
  }

  @Test
  void should_detect_syllable_of_three_characters_plus_two_characters() {
    Syllables syllables = kanaSyllableSplitter.split("konichiwa");

    Syllables expected = SyllablesTestDSL.given_syllables_with("ko").and("ni").and("chi").and("wa").build();
    assertThat(syllables).isEqualTo(expected);
  }
}
