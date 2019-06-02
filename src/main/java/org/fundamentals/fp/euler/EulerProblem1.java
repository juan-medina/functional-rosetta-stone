package org.fundamentals.fp.euler;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

/**
 * https://projecteuler.net/problem=1
 *
 * Scenario 10:
 *
 * Given
 * When
 * If we list all the natural numbers below 10
 * that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 *
 * Then
 * The sum of these multiples is 23.
 *
 * Scenario 1000:
 *
 * Given
 * When
 * Then
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 */
@Slf4j
public class EulerProblem1 {

    public long javaSolution(int limit) {

        long sum = 0;

        for(int counter = 1; counter < limit; counter++) {
            if ((counter % 3 ==0) || (counter % 5 ==0)) {
                //System.out.println(counter);
                sum += counter;
            }
        }

        return sum;
    }

    public long javaStreamSolution(int limit) {

        Consumer<Integer> print = System.out::println;
        Predicate<Integer> isMultiple3 = number -> number % 3 == 0;
        Predicate<Integer> isMultiple5 = number -> number % 5 == 0;

        return IntStream.range(1, limit).boxed()
                .filter(isMultiple3.or(isMultiple5))
                //.peek(print)
                .reduce(0, Integer::sum);
    }

    public long javaStreamSolution2(int limit) {

        Consumer<Integer> print = System.out::println;
        Predicate<Integer> isMultiple3 = number -> number % 3 == 0;
        Predicate<Integer> isMultiple5 = number -> number % 5 == 0;

        return IntStream.range(1, limit).boxed()
                .filter(isMultiple3.or(isMultiple5))
                //.peek(print)
                .collect(Collectors.summingLong(Integer::intValue));
    }

    public Mono<Long> reactorSolution(int limit) {

        Predicate<Integer> isMultiple3 = number -> number % 3 == 0;
        Predicate<Integer> isMultiple5 = number -> number % 5 == 0;

        return MathFlux.sumLong(Flux.range(0, limit)
                    .filter(isMultiple3.or(isMultiple5))
                    //.log()
                );
    }

}
