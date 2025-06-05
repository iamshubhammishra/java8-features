package functionInterface;

import java.util.function.*;

public class Main {
    public static void main(String[] args){
        Calculator sum =(a,b)->a+b;

        Calculator multiply =(a,b)->a*b;

        System.out.println(sum.operation(2,3));
        System.out.println(multiply.operation(2,3));

        Function<String, Integer> function = (s)->s.length();
        System.out.println(function.apply("shubham"));

        // function chaining
        Function<String, String> toUpper = s -> s.toUpperCase();
        Function<String, String> addExclamation = s -> s + "!";

        Function<String, String> combined = toUpper.andThen(addExclamation);

        System.out.println(combined.apply("hello"));  // Output: HELLO!

        Predicate<Integer> isEven = (number)->number%2==0;
        System.out.println(isEven.test(6));

        // combined two predicate
        Predicate<String> longName = s -> s.length() > 5;
        Predicate<String> startsWithS = s -> s.startsWith("S");

        Predicate<String> combinedPredicate = longName.and(startsWithS);

        System.out.println(combinedPredicate.test("Shubham")); // true
        System.out.println(combinedPredicate.test("Sam"));     // false

        Predicate<String> combinedOr = longName.or(startsWithS);
        System.out.println(combinedOr.test("Sam"));

        // reverse the predicate result
        Predicate<String> notLongName = longName.negate();
        System.out.println(notLongName.test("Sam"));

        Consumer<String> consume = (s)->System.out.println(s);
        consume.accept("Shubham");

        // chain two consumer
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        Consumer<String> printLower = s -> System.out.println(s.toLowerCase());

        Consumer<String> combinedConsumer = printUpper.andThen(printLower);

        combinedConsumer.accept("Shubham");

        Supplier<String> supplier = ()->"shubham";
        System.out.println(supplier.get());

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(10, 20));  // Output: 30

    }

}
