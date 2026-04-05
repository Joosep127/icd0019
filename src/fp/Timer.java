package fp;

import org.junit.jupiter.api.Test;

public class Timer {

    @Test
    public void measuresExecutionTime() {

        executeAndTime(() -> methodToBeTimed());

    }

    private void executeAndTime(Runnable code) {
        long start = System.currentTimeMillis();

        code.run();

        System.out.println(System.currentTimeMillis() - start);
    }

    public void howToMeasureExecutionTimeExample() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1e9; i++) {
        }

        double passedMills = System.currentTimeMillis() - start;
        String result = String.format("%s sek", passedMills / 1000);
        System.out.println(result);
    }


    private void methodToBeTimed() {
        for (int i = 0; i < 1e9; i++) {

        }
    }

}
