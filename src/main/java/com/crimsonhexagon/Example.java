package com.crimsonhexagon;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Example {

    static List<String> compoundLambdaExpression(final Collection<String> strings) {
        return strings.stream().filter(s -> s != null && s.isEmpty()).collect(toList());
    }

    static List<String> multipleLambdaMethodReferences(final Collection<String> strings) {
        return strings.stream().filter(Objects::nonNull).filter(String::isEmpty).collect(toList());
    }

    public static void main(String[] args) {
        final List<String> strings = Stream.generate(() -> randomAscii(0,30)).limit(1_000).collect(toList());
        while (true) {
            compoundLambdaExpression(strings);
            multipleLambdaMethodReferences(strings);
        }
    }
}
