package com.crimsonhexagon;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomStringUtils.randomAscii;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Fork(2)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 2, time = 1)
public class StreamBenchmark {

    @State(Scope.Benchmark)
    public static class SingleValue {
        final List<String> values = singletonList(randomAscii(0, 30));
    }

    @State(Scope.Benchmark)
    public static class SmallCollection {
        final Random rand = new Random();

        final List<String> values = Stream.generate(() ->
            rand.nextInt() % 5 == 0
                ? null
                : randomAscii(0, 30)).limit(1000).collect(toList());
    }

    @State(Scope.Benchmark)
    public static class MediumCollection {
        final Random rand = new Random();

        final List<String> values = Stream.generate(() ->
            rand.nextInt() % 5 == 0
                ? null
                : randomAscii(0, 30)).limit(10_000).collect(toList());
    }

    @State(Scope.Benchmark)
    public static class LargeCollection {
        final Random rand = new Random();

        final List<String> values = Stream.generate(() ->
            rand.nextInt() % 5 == 0
                ? null
                : randomAscii(0, 30)).limit(100_000).collect(toList());
    }

    /* Single */

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterCompoundExpressionSingle(SingleValue single) {
        return single.values.stream().filter(v -> v != null && v.isEmpty()).collect(toList());
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterStreamOperationsSingle(SingleValue single) {
        return single.values.stream().filter(Objects::nonNull).filter(String::isEmpty).collect(toList());
    }

    /* Small */

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterCompoundExpressionSmall(SmallCollection small) {
        return small.values.stream().filter(v -> v != null && v.isEmpty()).collect(toList());
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterStreamOperationsSmall(SmallCollection small) {
        return small.values.stream().filter(Objects::nonNull).filter(String::isEmpty).collect(toList());
    }

    /* Medium */

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterCompoundExpressionMedium(MediumCollection medium) {
        return medium.values.stream().filter(v -> v != null && v.isEmpty()).collect(toList());
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterStreamOperationsMedium(MediumCollection medium) {
        return medium.values.stream().filter(Objects::nonNull).filter(String::isEmpty).collect(toList());
    }

    /* Large */

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterCompoundExpressionLarge(LargeCollection large) {
        return large.values.stream().filter(v -> v != null && v.isEmpty()).collect(toList());
    }

    @Benchmark
    @BenchmarkMode({Mode.All})
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    public List<String> filterStreamOperationsLarge(LargeCollection large) {
        return large.values.stream().filter(Objects::nonNull).filter(String::isEmpty).collect(toList());
    }
}
