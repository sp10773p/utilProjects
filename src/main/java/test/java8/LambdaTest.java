package test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Say{
    void something();
}

class Hello{
    void print(Say say){
        say.something();
    }
}

public class LambdaTest {
    public static void main(String[] args) {
        Hello hello = new Hello();

        hello.print(() -> {
            System.out.println("hollo java");
        });

        System.out.println("========================");

        List<String> list = Arrays.asList("Java", "Oracle", "Scala");

        System.out.println(
                list.stream()
                        .map(s -> s.substring(0, 4))
                        .filter(s -> s.startsWith("J"))
                        .collect(Collectors.toList())
        );

        System.out.println("========================");
        list.stream().forEach(s -> System.out.println(s));

    }
}
