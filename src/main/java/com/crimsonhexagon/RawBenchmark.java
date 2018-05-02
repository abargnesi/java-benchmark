package com.crimsonhexagon;

import static java.lang.System.nanoTime;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;

import org.apache.commons.lang3.mutable.MutableLong;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class RawBenchmark {

    public static void main(String[] args) {
        final SmallCollection scol = new SmallCollection();

        int result = 0;

        // warm-up
        for (int i = 0; i < 10_000_000; i++) {
            result += scol.values.stream().filter(v -> v != null && v.isEmpty()).count();
//            result += scol.values.stream().filter(Objects::nonNull).filter(String::isEmpty).count();
        }

        result = 0;
        long runs = 1_000_000;
        long time = 0;

        // run
        for (int i = 0; i < runs; i++) {
            long nanoStart = nanoTime();
            result += Stream.of("hi").filter(v -> v != null && v.isEmpty()).count();
//            result += Stream.of("hi").filter(Objects::nonNull).filter(String::isEmpty).count();
            time += (nanoTime() - nanoStart);
        }

        System.out.println((float) time / runs);
    }

    public static class SmallCollection {
        final Random rand = new Random();

        final List<String> values = Stream.generate(() ->
            rand.nextInt() % 5 == 0
                ? null
                : randomAscii(20, 30)).limit(1000).collect(toList());
    }
}
