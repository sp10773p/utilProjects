package test.java8.stream;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 무한값 생성
 */
public class StreamExamples1 {

    public static void main(String[] args) {
        // 1 ~ 10 까지 출력
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));

        // Integer MAX_SIZE 만큼 출력
        IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));

        // 무한 출력
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.print(i + " "));

    }

}

