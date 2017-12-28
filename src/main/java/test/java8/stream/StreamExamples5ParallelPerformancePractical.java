package test.java8.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples5ParallelPerformancePractical {
    private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);

    private static final List<Product> products;

    static{
        final int length = 8_000_000;
        /**
        ArrayList : 초기에 length가 10인 array를 내부적으로 생성해서
                  add 메서드 사용시 용량 확인을 해서 다 찰때 마다 1.5배를 한다.
                  즉, array.length = 100,000 인데, 이게 꽉차면 그후에 element 1개만
                  더 추가를 해도 ArrayList의 내부크기는
                  100,000 * 1.5 = 150,000 (즉, array length : 100,000 => 150,000)
         */
        //final List<Product> list = new ArrayList<>();
        // 변경
        final List<Product> list = new ArrayList<>(length);
        for (int i=1; i<=length; i++){
            list.add((new Product((long)i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)]))));
        }
        products = Collections.unmodifiableList(list);


    }

    private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate){
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products){
            if (predicate.test(product)){
                sum = sum.add(product.getPrice());
            }
        }

        return sum;
    }

    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate){
        return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imperativeTest(BigDecimal targetPrice){
        System.out.println("==================================================");
        System.out.println("\nImperative Sum \n------------------------------------------------");

        final long start = System.currentTimeMillis();
        System.out.println("Sum : " +
            imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );

        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==================================================");
    }

    private static void streamTest(BigDecimal targetPrice) {
        System.out.println("==================================================");
        System.out.println("\nStream Sum \n------------------------------------------------");

        final long start = System.currentTimeMillis();
        System.out.println("Sum : " +
                streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );

        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==================================================");
    }


    private static void parallelStreamTest(BigDecimal targetPrice) {
        System.out.println("==================================================");
        System.out.println("\nParallelStream Sum \n------------------------------------------------");

        final long start = System.currentTimeMillis();
        System.out.println("Sum : " +
                streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );

        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==================================================");
    }


    public static void main(String[] args) {
        final BigDecimal targetPrice = new BigDecimal("40");

        streamTest(targetPrice);
        parallelStreamTest(targetPrice);
        imperativeTest(targetPrice);

        System.out.println("\nIgnore Tests Above \n==========================================\n");
        System.out.println("Start!");
        for (int i = 0; i < 5; i++){
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];
            streamTest(price);
            parallelStreamTest(price);
            imperativeTest(price);

        }
    }



}