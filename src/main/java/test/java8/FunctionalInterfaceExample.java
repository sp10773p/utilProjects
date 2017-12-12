package java8;
/**
 * Function - apply ���ڸ� �޾� ó���� ����
 * Consumer - accept ���ڸ� �޾� ó��
 * Predicate - test ���ڸ� �޾� ó���� boolean�� ����
 * Supplier - get ����ó�� ����
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {
    public void run4FuntaionalTest (){

        System.out.println("===================== Function ===============================");
        final Function<String, Integer> toInt = value -> Integer.parseInt(value);

        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = t -> t;

        System.out.println(identity.apply(900));

        System.out.println("===================== Consumer ===============================");
        final Consumer<String> print = value -> System.out.println(value);
        final Consumer<String> greetings = value -> System.out.println("Hello " + value);

        print.accept("Hello");
        greetings.accept("World");

        System.out.println("==================== Predicate ================================");
        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));


        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        Predicate<Integer> isLessThan3 = i -> i < 3;

        System.out.println(filter(numbers, isPositive));
        System.out.println(filter(numbers, isLessThan3));

        System.out.println("==================== Supplier ================================");

        long start = System.currentTimeMillis();

        printIfValueIndex(0, getVeryExpensiveValue());
        printIfValueIndex(-1, getVeryExpensiveValue());

        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 + " seconds.");

        printIfValueIndex(0, () -> getVeryExpensiveValue());
        printIfValueIndex(-1, () -> getVeryExpensiveValue());

    }

    private static String getVeryExpensiveValue(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Kevin";
    }

    private static void printIfValueIndex(int number, String value){
        if(number >= 0){
            System.out.println("The value is " + value);
        }else{
            System.out.println("Valid");
        }
    }
    private static void printIfValueIndex(int number, Supplier<String> valueSupplier){
        if(number >= 0){
            System.out.println("The value is " + valueSupplier.get());
        }else{
            System.out.println("Valid");
        }
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter){
        List<T> result = new ArrayList<>();

        for(T input : list){
            if(filter.test(input)){
                result.add(input);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        println(1L, "Seong", "test@email.com",
                (id, name, email) -> "User info: ID=" + id + ", name=" + name + ", email=" + email);

        println(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));

        BigDecimalToCurrency bigDecimalToCurrency = value -> "$" + value.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("12.00")));

        final InvalidFuncaitonalInterface anonymousClass = new InvalidFuncaitonalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };

        System.out.println("anonymousClass : " + anonymousClass.mkString(12.00));

        // ���ٸ� �����ϴ� ��� ( @FuncionalInterface�� �޼��忡 ���׸��� ����ϴ� ���� ���ٸ� ����Ҽ� ���� )
        //final InvalidFuncaitonalInterface invalidFuncaitonalInterface = value -> value.toString(); //Target is generic
        //System.out.println("InvalidFuncaitonalInterface : " + invalidFuncaitonalInterface.mkString(12.00));
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function){
        System.out.println(function.apply(t1, t2, t3));
    }
}

@FunctionalInterface
interface Function3<T1, T2, T3, R>{
    R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency{
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFuncaitonalInterface {
    <T> String mkString(T value);
}

