package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.KanaDictionary;
import io.shodo.kata.kanas.domain.KanaSyllablesSplitter;
import io.shodo.kata.kanas.domain.KanaTranslator;
import io.shodo.kata.kanas.domain.Kanas;
import io.shodo.kata.kanas.infra.KanasHiraganaSyllabary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KanaTranslatorTest {

  private final KanaDictionary kanaDictionary = new KanasHiraganaSyllabary();
  private final KanaSyllablesSplitter kanaSyllablesSplitter = new KanaSyllablesSplitter(kanaDictionary);
  private final KanaTranslator kanaTranslator = new KanaTranslator(kanaDictionary, kanaSyllablesSplitter);

  @Test
  void should_translate_syllable_to_kana() {
    Kanas kanas = kanaTranslator.translate("a");

    assertThat(kanas).isEqualTo(Kanas.of("あ"));
  }

  @Test
  void should_translate_two_syllables_to_kanas() {
    Kanas kanas = kanaTranslator.translate("koni");

    assertThat(kanas).isEqualTo(Kanas.write("こに"));
  }

  @Test
  void should_translate_three_syllables_with_syllable_of_3_chars_to_kanas() {
    Kanas kanas = kanaTranslator.translate("konichi");

    assertThat(kanas).isEqualTo(Kanas.write("こにち"));
  }

  @Test
  void should_translate_complex_word() {
    Kanas kanas = kanaTranslator.translate("konichiwa");

    assertThat(kanas).isEqualTo(Kanas.write("こにちわ"));
  }
}
