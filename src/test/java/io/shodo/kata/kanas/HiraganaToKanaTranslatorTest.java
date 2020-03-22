package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.*;
import io.shodo.kata.kanas.infra.HiraganaSyllabaryToKanas;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HiraganaToKanaTranslatorTest {

  private final HiraganaToKanaReferential hiraganaToKanaReferential = new HiraganaSyllabaryToKanas();
  private final HiraganaSyllabarySplitter hiraganaSyllabarySplitter = new HiraganaSyllabarySplitter(hiraganaToKanaReferential);
  private final Translator hiraganaToKanaTranslator = new HiraganaToKanaTranslator(hiraganaToKanaReferential, hiraganaSyllabarySplitter);

  @Test
  void should_translate_syllable_to_kana() {
    Kanas kanas = hiraganaToKanaTranslator.translate("a");

    assertThat(kanas).isEqualTo(Kanas.of("あ"));
  }

  @Test
  void should_translate_two_syllables_to_kanas() {
    Kanas kanas = hiraganaToKanaTranslator.translate("koni");

    assertThat(kanas).isEqualTo(Kanas.write("こに"));
  }

  @Test
  void should_translate_three_syllables_with_syllable_of_3_chars_to_kanas() {
    Kanas kanas = hiraganaToKanaTranslator.translate("konichi");

    assertThat(kanas).isEqualTo(Kanas.write("こにち"));
  }

  @Test
  void should_translate_complex_word() {
    Kanas kanas = hiraganaToKanaTranslator.translate("konichiwa");

    assertThat(kanas).isEqualTo(Kanas.write("こにちわ"));
  }
}
