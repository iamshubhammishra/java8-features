package optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args){

        // Empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isEmpty());

        //Optional with non null value
        Optional<String> name = Optional.of("Shubham");
        System.out.println(name.isPresent());

        // Optional with numllable value
        Optional<String> mayBeName = Optional.ofNullable(null);
        System.out.println(mayBeName.isPresent());



        Optional<String> programming = Optional.of("java");
        if(programming.isPresent()){
            System.out.println(programming.get());
        }
        programming.ifPresent(n->System.out.println(n));

        String value1 = mayBeName.orElse("Default");
        System.out.println(value1);
        String value2 = mayBeName.orElseGet(() -> "Generated Default");
        System.out.println(value2);
//        String value3 = mayBeName.orElseThrow(() -> new IllegalArgumentException("Value is missing"));

        Optional<String> nameOne = Optional.of("Shubham");
        Optional<Integer> length = nameOne.map(String::length);
        length.ifPresent(l->System.out.println(l));

        Optional<String> filtered = name.filter(n -> "Shivam".startsWith("S"));
        filtered.ifPresent(l->System.out.println(l));

        String result1 = Optional.of("Java")
                .orElse(getDefault()); // getDefault() is always called

        String result2 = Optional.of("Java")
                .orElseGet(() -> getDefault()); // getDefault() is NOT called
    }

    private static String getDefault() {
        System.out.println("Computing default value...");
        return "Default";
    }
}
