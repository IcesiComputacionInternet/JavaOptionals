# JavaOptionals
Simple exercises to understand Java Optionals features

Optionals as described by ChatGPT as: 

> In Java, an Optional is a container object which may or may not contain a non-null value. It is used to represent a value that may be absent or present. An Optional object can be used as an alternative to null values, to indicate that a value may or may not be present. This can help to prevent null pointer exceptions, by making it explicit when a value is absent. Optional can be used with methods that return a value that may not always be present. The methods of the Optional class can be used to check if a value is present, and to retrieve the value if it is.

this abstraction let us handle null values in java in an easier way.



>// Creating an empty Optional
>Optional<String> empty = Optional.empty();
>
>// Creating an Optional with a non-null value
>Optional<String> value = Optional.of("Hello");
>
>// Creating an Optional that may or may not contain a non-null value
>Optional<String> nullableValue = Optional.ofNullable(null);

We can use this to avoid null cheking in every step

For example if we expect a String and we want to have a default value if it's null we can do this:

>public void printString(String mayBeNull){
>
>  String printable = Optional.ofNullable(mayBeNull).orElse("default");
>
>  System.out.println("The value of the string is: " + printable);
>
>}
