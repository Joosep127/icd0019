package poly.demo;

import java.util.List;

public class IterableExample {

    // https://mvnrepository.com/artifact/com.jetbrains.intellij.java/java-decompiler-engine
    // version 231.9392.1

    // java -jar lib/fern.jar ./IterableExample.class .

    public static void main(String[] args) {

        List<Integer> integers = List.of(1, 2);

        for (Integer each : integers) {
            System.out.println(each);
        }

    }

}
