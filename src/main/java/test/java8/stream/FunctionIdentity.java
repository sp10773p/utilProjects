package test.java8.stream;
/**
 * Function Identity 의 사용
 * - 주어진 값을 그대로 리턴 받고 싶을때 null을 사용하지 않고 자기 자신을 리턴
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionIdentity {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println(
                map(numbers, i -> i * 2)
        );

        System.out.println(
                map(numbers, Function.identity())
        );
    }

    private static <T, R> List<R> map(List<T> list , Function<T, R> mapper){
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(mapper.apply(t));
        }

        return result;
    }
}
