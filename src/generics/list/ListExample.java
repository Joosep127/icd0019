package generics.list;

import java.util.List;

public class ListExample {

    public static void main(String[] args) {

        String sourceType = "main";

        List<? extends Number> source = sourceType.equals("main") ? getDataMain() : getDataBackup();

        double sum = 0;
        for (Number number : source) {
            sum += number.doubleValue();
        }
        System.out.println(sum);

    }

    public static List<Integer> getDataMain() {
        return List.of(1, 2, 3);
    }

    public static List<Double> getDataBackup() {
        return List.of(1.0, 2.4, 5.1);
    }

}
