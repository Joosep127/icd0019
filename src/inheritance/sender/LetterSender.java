package inheritance.sender;

import java.time.LocalTime;

public class LetterSender {

    private final LocalTime currentTime;
    private String name = "sender";

    public LetterSender(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public void sendLetter() {
        String greeting = "Good " + getTimeOfDayString();
        String contents = "Dead customer, ...";


        System.out.printf("Sending %s ...%n", name);
        System.out.println("Title: " + greeting);
        System.out.println("Text: " + contents);
    }

    private String getTimeOfDayString() {

        int hour = currentTime.getHour();

        if (hour > 5 && hour <= 11) {
            return "morning";
        } else if (hour > 11 && hour <= 17) {
            return "afternoon";
        } else if (hour > 17 && hour <= 20) {
            return "evening";
        } else {
            return "night";
        }
    }


}
