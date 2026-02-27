package oo.hide;

public class Timer {

    public final long createdTime = System.currentTimeMillis();

    public String getPassedTime() {
        long tempTime = System.currentTimeMillis();

        return "" + ((float) (tempTime - createdTime) / 1000);
    }
}
