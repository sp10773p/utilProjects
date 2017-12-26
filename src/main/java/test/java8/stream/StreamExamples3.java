package test.java8.stream;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class StreamExamples3 {

    public static void main(String[] args) {
        System.out.println("collect(toList()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toList())  // [#6, #6, #10, #10]
        );

        System.out.println("collect(toSet())  : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(toSet())   // [#10, #6]
        );

        System.out.println("collect(joining(\", \"))  : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", ")) // #6, #6, #10, #10
        );

        System.out.println("collect(joining([\", \"]))  : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(joining(", ", "[", "]")) // [#6, #6, #10, #10]
        );

        System.out.println("distinct().collect(joining([\", \"]))  : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct() // 중복제거
                        .collect(joining(", ", "[", "]")) // [#6, #10]
        );

        System.out.println("distinct().collect(toList())  : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct() // 중복제거
                        .collect(toList()) // [#6, #10]
        );

        /**
         * // Integer.of 는 IntegerCache를 가지고 비교 하기 때문에 int i == 3 을 비교 하는것과 같다 IntegerCache의 high 는 127 이므로 127 이상의 값을 비교 할때는 equals 를 사용 해야 한다.
         */
        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127)    // auto boxing (int to Integer)-> Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5)
                        .filter(i -> i == integer127) // Integer.of 는 IntegerCache를 가지고 비교 하기 때문에 int i == 3 을 비교 하는것과 같다 IntegerCache의 high 는 127 이므로 127 이상의 값을 비교 할때는 equals 를 사용 해야 한다.
                        .findFirst() // Optional[127]
        );

        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i == integer128)
                        .findFirst()    // Optional.empty
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i.equals(integer128))
                        .findFirst()    // Optional[128]
        );

        System.out.println("filter(i -> i > 3).count() = " +
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > 3)
                        .count()
        );
    }
}
