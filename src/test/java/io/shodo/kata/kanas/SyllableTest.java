package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.Syllable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SyllableTest {

  @Test
  void should_compare_two_syllables() {
    Syllable chi = Syllable.of("chi");
    Syllable wa = Syllable.of("wa");

    int result = chi.compareTo(wa);

    assertThat(result).isEqualTo(-1);
  }
}
