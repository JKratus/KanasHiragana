package io.shodo.kata.kanas;

import io.shodo.kata.kanas.annotations.ValueType;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.List;
import java.util.stream.Stream;

public class EqualsAndHashcodeTest {

  private static Stream<Arguments> provideAllValueTypes() {
    List<Class<?>> domainValueTypes = ReflectionUtils.findAllClassesInPackage(
            "io.shodo.kata.kanas",
            ClassFilter.of(aClass -> aClass.isAnnotationPresent(ValueType.class)));
    return domainValueTypes.stream().map(Arguments::of);
  }

  @ParameterizedTest
  @MethodSource("provideAllValueTypes")
  public void equalsHashCodeContracts(Class<?> classname) {
    EqualsVerifier.forClass(classname).verify();
  }
}
