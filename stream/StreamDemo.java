package stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        //Find first even number than square it and return sum
        getSumOfSquareOfEvenNumber();
        toUppercase();
        firstElementStartWithA();
        firstElementsCountStartWithA();
        groupStringByLength();
        partitionListByEvenAndOdd();
        countCharOccurence();
        getSecondHighestNumber();
        printDuplicateList();
        flattenList();
        topThreeHighestSalaryPerDepartment();
        fistNonRepeatingChar();
    }

    private static void getSumOfSquareOfEvenNumber() {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(n -> n * n)
                .reduce(0, (a, b) -> a + b);
//                        .reduce(0,Integer::sum);
        System.out.println("Sum: " + sum);
    }

    private static void toUppercase() {
        var words = List.of("Hello", "hi", "hey");
        var uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Uppercase words :" + uppercaseWords);
    }

    private static void firstElementStartWithA() {
        var data = Arrays.asList("alex", "bob", "alice");
        Optional<String> a = data.stream()
                .filter(s -> s.startsWith("a"))
                .findFirst();
        System.out.println("First word start with 'a': " + a);
    }

    private static void firstElementsCountStartWithA() {
        var data = Arrays.asList("alex", "bob", "alice");
        var count = data.stream()
                .filter(s -> s.startsWith("a"))
                .count();
        System.out.println("Word count start with 'a': " + count);
    }

    private static void groupStringByLength() {
        var data = Arrays.asList("hi", "hello", "world", "ok");
        Map<Integer, List<String>> grouped = data.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped: " + grouped);
    }

    private static void partitionListByEvenAndOdd() {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> collect = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Result: " + collect);
    }

    private static void countCharOccurence() {
        var input = "stream";
        Map<Character, Long> freq = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Frequency of char is: " + freq);
    }

    private static void getSecondHighestNumber() {
        var nums = Arrays.asList(5, 3, 9, 1, 8);
        var number = nums.stream()
                .sorted((a, b) -> b - a)
                .limit(2)
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println("Second highest number is: " + number);
    }

    private static void printDuplicateList() {
        var numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 1);
        var set = new HashSet<>();
        var duplicates = numbers.stream()
                .filter(n -> !set.add(n))
                .collect(Collectors.toSet());
        System.out.println("Duplicates numbers: " + duplicates);
    }

    private static void flattenList() {
        var inputs = Arrays.asList(
                Arrays.asList(1, 2), Arrays.asList(3, 4));
        var result = inputs.stream().flatMap(n -> n.stream()).toList();
        System.out.println("Results: " + result);
    }

    private static void topThreeHighestSalaryPerDepartment() {
        var employees = List.of(
                new Employee("Alice", "HR", 5000),
                new Employee("Bob", "HR", 6000),
                new Employee("Carol", "HR", 5500),
                new Employee("Dave", "HR", 5200),
                new Employee("Eve", "IT", 7000),
                new Employee("Frank", "IT", 7200),
                new Employee("Grace", "IT", 7100),
                new Employee("Heidi", "IT", 7300),
                new Employee("Ivan", "Finance", 6400),
                new Employee("Judy", "Finance", 6300)
        );

        Map<String, List<Employee>> results = employees.stream()
                .collect(
                        Collectors.groupingBy(Employee::getDept,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted((a, b) -> b.getSalary() - a.getSalary())
                                                .limit(3)
                                                .toList()
                                )
                        )
                );

        System.out.println("Results: " + results);
    }

    private static void fistNonRepeatingChar() {
        var input = "aabbcdde";
        var nonRepeatingChar = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .findFirst()
                .orElse(null);
        System.out.println("First non repeating char is " + nonRepeatingChar);
    }
}
