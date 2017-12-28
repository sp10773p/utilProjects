package test.java8.stream;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Steam을 이용한 병렬 프로그램
 * - os의 processor 갯수로 병렬 처리
 */
public class StreamExamples5Parallel {
    public static void main(String[] args) {

        // Sing Core Processor
        final int[] sum = { 0 };
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);
        System.out.println(sum[0] + " - stream sum (with side-effect)");

        // Multi Core Processor
        final int[] sum2 = { 0 };
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println(sum2[0] + " - parallel sum (with side-effect)");

        System.out.println(
            IntStream.range(0, 100).sum()
                + " - stream sum (without side-effect)"
        );

        System.out.println(
            IntStream.range(0, 100).parallel().sum()
                + " - parallel sum (without side-effect)"
        );

        System.out.println("\n=========================================");
        System.out.println("Stream : ");
        final long curr = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));

        System.out.println(System.currentTimeMillis() - curr);

        System.out.println("\n=========================================");
        System.out.println("Parallel Stream : ");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start2);

        // 사용하는 코어의 갯수 지정하기 (코어개수 = 0 : 1개 , 1 : 2개 ..... )
        System.out.println("\n=========================================");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3");
        System.out.println("Parallel Stream with parallelism 4: ");
        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start3);

        System.out.println("\n=========================================");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "0");
        System.out.println("Parallel Stream with parallelism 2: ");
        final long start4 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start4);
    }
}
