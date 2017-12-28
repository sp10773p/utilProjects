package test.java8.stream;

import com.sun.org.apache.xpath.internal.SourceTree;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples4 {
    public static void main(String[] args) {
        final List<Product> products =
                Arrays.asList(
                        new Product(1L, "A", new BigDecimal("100.50")),
                        new Product(2L, "B", new BigDecimal("23.00")),
                        new Product(3L, "C", new BigDecimal("31.45")),
                        new Product(4L, "D", new BigDecimal("80.20")),
                        new Product(5L, "E", new BigDecimal("7.50"))
                );

        System.out.println("Products.price >= 30 : " +
            products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .collect(toList())
        );

        System.out.println("\n=========================================\n");
        System.out.println("Products.price >= 30 (with joining): " +
            products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(product -> product.toString())
                .collect(joining("\n"))
        );

        System.out.println("\n=========================================\n");
        // 합계
        System.out.println("IntStream sum : " +
            IntStream.of(1, 2, 3, 4, 5)
                     .sum()
        );

        System.out.println("\n=========================================\n");
        // 가격의 합계
        System.out.println("Total Price : " +
            products.stream()
                    .map(product -> product.getPrice())
                    .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n=========================================\n");
        System.out.println("Total Price of Procucts.price >= 30: " +
            products.stream()
                    .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                    .map(product -> product.getPrice())
                    .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n=========================================\n");
        System.out.println("# of Procucts.price >= 30: " +
            products.stream()
                    .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                    .count()
        );


        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem item3 = new OrderedItem(3L, products.get(4), 10);

        final Order order = new Order(1L, Arrays.asList(item1, item2, item3));

        System.out.println("\n=========================================\n");
        System.out.println("order.totalPrice(): " + order.totalPrice());

    }
}


/**
 * lombok 사용법
 * - Lombok Plugin 설치
 * - pom.xml에 dependancy 추가
 * - setting에서 compiler -> annotation processor -> enable annotation processing 활성화
 */
@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getTotalPricce(){
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

@AllArgsConstructor
@Data
class Order {
    private Long id;
    private List<OrderedItem> items;

    public BigDecimal totalPrice(){
        return items.stream()
                .map(item -> item.getTotalPricce())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
    }

}