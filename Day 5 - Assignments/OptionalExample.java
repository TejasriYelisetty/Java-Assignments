import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        // Create an Optional using of()
        Optional<String> optionalValue = Optional.of("Hello, Optional!");
        System.out.println("Optional Value using of(): " + optionalValue.get());

        // Create an Optional using ofNullable()
        Optional<String> nullableValue = Optional.ofNullable(null);
        System.out.println("Optional using ofNullable() is present: " + nullableValue.isPresent());

        // Use isPresent() to check if value is present
        if (optionalValue.isPresent()) {
            System.out.println("Value in optionalValue is: " + optionalValue.get());
        }

        // Use ifPresent() to perform an action if value is present
        optionalValue.ifPresent(value -> System.out.println("ifPresent: Value is " + value));

        // Use orElse() to provide a default value
        String defaultValue = nullableValue.orElse("Default Value");
        System.out.println("Value with orElse: " + defaultValue);

        // Use orElseGet() to provide a default value using a supplier
        String suppliedValue = nullableValue.orElseGet(() -> "Value from Supplier");
        System.out.println("Value with orElseGet: " + suppliedValue);

        // Use empty() to create an empty Optional
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Empty Optional is present: " + emptyOptional.isPresent());

        // Use map() to transform the value
        Optional<Integer> stringLength = optionalValue.map(String::length);
        stringLength.ifPresent(length -> System.out.println("Length of string in optionalValue: " + length));

        // Use flatMap() for nested Optionals
        Optional<Optional<String>> nestedOptional = Optional.of(Optional.of("Nested Value"));
        nestedOptional.flatMap(opt -> opt)
                      .ifPresent(value -> System.out.println("FlatMapped value: " + value));

        // Use filter() to apply a condition
        optionalValue.filter(value -> value.startsWith("Hello"))
                     .ifPresent(value -> System.out.println("Filtered value: " + value));
    }
}
