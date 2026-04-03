package generics.wildcards;

public class ExampleSuper {

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();

        Box<? super Integer> mysteryBox = intBox;
        // a box of some superclass of Integer, but which is not known.

        mysteryBox.put(11); // a subclass object can always be assigned to a super class variable

        Object item = mysteryBox.get();
        // we can take out objects as everything is an object

    }
}
