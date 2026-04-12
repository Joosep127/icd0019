package poly.demo;

public class Timer {

    private final Long start = System.currentTimeMillis();

    public String getPassedTime() {
        double passedMills = System.currentTimeMillis() - start;
        return "%s sek".formatted(passedMills / 1000);
    }
}
