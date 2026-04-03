package generics.wildcards;

public class ExampleExtends {

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();

        intBox.put(10);

        Box<? extends Number> mysteryBox = intBox;
        // a box of Number, but which subclass of Number is not know

        Number number = mysteryBox.get();
        // we can take out Numbers

        // mysteryBox.put(11); // Does not compile!
        // Required type: capture of ? extends Number
        // Provided: int
        // public void put(T item) { ...
        // public void put(? extends Number item) { ...

    }
}
