package io.shodo.kata.kanas.infra;

import io.shodo.kata.kanas.annotations.Provider;
import io.shodo.kata.kanas.domain.HiraganaToKanaReferential;
import io.shodo.kata.kanas.domain.Kana;
import io.shodo.kata.kanas.domain.Syllable;
import io.shodo.kata.kanas.domain.Syllables;

import java.util.Map;

import static java.util.stream.Collectors.toList;

@Provider
public class HiraganaSyllabaryToKanas implements HiraganaToKanaReferential {
  private final Map<Syllable, Kana> dictionary;

  public HiraganaSyllabaryToKanas() {
    this.dictionary = Map.ofEntries(
            Map.entry(Syllable.of("a"), Kana.of("あ")),
            Map.entry(Syllable.of("i"), Kana.of("い")),
            Map.entry(Syllable.of("u"), Kana.of("う")),
            Map.entry(Syllable.of("e"), Kana.of("え")),
            Map.entry(Syllable.of("o"), Kana.of("お")),
            Map.entry(Syllable.of("ka"), Kana.of("か")),
            Map.entry(Syllable.of("ki"), Kana.of("き")),
            Map.entry(Syllable.of("ku"), Kana.of("く")),
            Map.entry(Syllable.of("ke"), Kana.of("け")),
            Map.entry(Syllable.of("ko"), Kana.of("こ")),
            Map.entry(Syllable.of("sa"), Kana.of("さ")),
            Map.entry(Syllable.of("shi"), Kana.of("し")),
            Map.entry(Syllable.of("su"), Kana.of("す")),
            Map.entry(Syllable.of("se"), Kana.of("せ")),
            Map.entry(Syllable.of("so"), Kana.of("そ")),
            Map.entry(Syllable.of("ta"), Kana.of("た")),
            Map.entry(Syllable.of("chi"), Kana.of("ち")),
            Map.entry(Syllable.of("tsu"), Kana.of("つ")),
            Map.entry(Syllable.of("te"), Kana.of("て")),
            Map.entry(Syllable.of("to"), Kana.of("と")),
            Map.entry(Syllable.of("na"), Kana.of("な")),
            Map.entry(Syllable.of("ni"), Kana.of("に")),
            Map.entry(Syllable.of("nu"), Kana.of("ぬ")),
            Map.entry(Syllable.of("ne"), Kana.of("ね")),
            Map.entry(Syllable.of("no"), Kana.of("の")),
            Map.entry(Syllable.of("ha"), Kana.of("は")),
            Map.entry(Syllable.of("hi"), Kana.of("ひ")),
            Map.entry(Syllable.of("fu"), Kana.of("ふ")),
            Map.entry(Syllable.of("he"), Kana.of("へ")),
            Map.entry(Syllable.of("ho"), Kana.of("ほ")),
            Map.entry(Syllable.of("ma"), Kana.of("ま")),
            Map.entry(Syllable.of("mi"), Kana.of("み")),
            Map.entry(Syllable.of("mu"), Kana.of("む")),
            Map.entry(Syllable.of("me"), Kana.of("め")),
            Map.entry(Syllable.of("mo"), Kana.of("も")),
            Map.entry(Syllable.of("ya"), Kana.of("や")),
            Map.entry(Syllable.of("yu"), Kana.of("ゆ")),
            Map.entry(Syllable.of("yo"), Kana.of("よ")),
            Map.entry(Syllable.of("ra"), Kana.of("ら")),
            Map.entry(Syllable.of("ri"), Kana.of("り")),
            Map.entry(Syllable.of("ru"), Kana.of("る")),
            Map.entry(Syllable.of("re"), Kana.of("れ")),
            Map.entry(Syllable.of("ro"), Kana.of("ろ")),
            Map.entry(Syllable.of("wa"), Kana.of("わ")),
            Map.entry(Syllable.of("wi"), Kana.of("ゐ")),
            Map.entry(Syllable.of("we"), Kana.of("ゑ")),
            Map.entry(Syllable.of("wo"), Kana.of("を")),
            Map.entry(Syllable.of("n"), Kana.of("ん"))
    );
  }

  @Override
  public Kana correlation(Syllable syllable) {
    return dictionary.getOrDefault(syllable, Kana.EMPTY);
  }

  @Override
  public Syllables syllablesWithLength(int length) {
    return Syllables.of(dictionary.keySet().stream().filter(syllable -> syllable.isLength(length)).collect(toList()));
  }
}
