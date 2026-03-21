package inheritance.sender;

import java.time.LocalTime;

public class EmailLetterSender extends LetterSender {

    public EmailLetterSender(LocalTime currentTime) {
        super(currentTime);
        setName("email");
    }
}