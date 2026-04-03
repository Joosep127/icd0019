package generics.list;

public class BoxExample {

    public static void main(String[] args) {

        Box box = new Box<>(0);

//        box = new Box<Double>(1.5);
//        box = new Box<Integer>(1);

        box.contents = 1.5;

        System.out.println(box.contents);
    }

    static class Box<T> {
        Box(T contents) {
            this.contents = contents;
        }

        T contents;
    }

}
