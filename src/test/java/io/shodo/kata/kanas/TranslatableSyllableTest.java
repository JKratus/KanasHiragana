package io.shodo.kata.kanas;

import io.shodo.kata.kanas.domain.Syllable;
import io.shodo.kata.kanas.domain.TranslatableSyllable;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TranslatableSyllableTest {

  @Test
  void should_compare_two_translatable_syllable() {
    List<TranslatableSyllable> syllables = List.of(
            TranslatableSyllable.of(2, Syllable.of("ni")),
            TranslatableSyllable.of(0, Syllable.of("ko"))
    );

    List<TranslatableSyllable> sorted = syllables.stream().sorted().collect(Collectors.toList());

    assertThat(sorted).isEqualTo(List.of(
            TranslatableSyllable.of(0, Syllable.of("ko")),
            TranslatableSyllable.of(2, Syllable.of("ni"))
    ));
  }

  @Test
  void should_compare_two_translatable_syllable_with_same_position() {
    List<TranslatableSyllable> syllables = List.of(
            TranslatableSyllable.of(4, Syllable.of("wa")),
            TranslatableSyllable.of(4, Syllable.of("chi"))
    );

    List<TranslatableSyllable> sorted = syllables.stream().sorted().collect(Collectors.toList());

    assertThat(sorted).isEqualTo(List.of(
            TranslatableSyllable.of(4, Syllable.of("chi")),
            TranslatableSyllable.of(4, Syllable.of("wa"))
    ));
  }
}
