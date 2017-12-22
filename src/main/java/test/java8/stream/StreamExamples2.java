package test.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Stream 기초 - Stream vs 예전방식
 */
public class StreamExamples2 {

    public static void main(String[] args) {
        //Stream.of(1, 2, 3, 4, 5).forEach(i -> System.out.print(i + " "));

        // 예전 방식
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6 ,7, 8, 9, 10);
        Integer result = null;
        for (final Integer number : numbers){
            if(number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10){
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Imprerative Result : " + result);

        // Stream
        System.out.println("Functional Result : " +
                numbers.stream()
                        .filter(number -> number > 3 && number < 9)
                        .map(number -> number * 2)
                        .filter(number -> number > 10)
                        .findFirst() // return type = Optional : null일때 Optional.empty를 return
                        .get()
        );

        /*
        outerloop:
            for (int i=0; i < 5; i++) {
                for (int j=0; j < 5; j++) {
                    if (i * j > 6) {
                        System.out.println("Breaking");
                        break outerloop;
                    }
                    System.out.println(i + " " + j);
                }
            }
            System.out.println("Done");
            */
    }

}

