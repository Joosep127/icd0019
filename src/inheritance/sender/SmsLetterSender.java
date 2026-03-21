package inheritance.sender;

import java.time.LocalTime;

public class SmsLetterSender extends LetterSender {

    public SmsLetterSender(LocalTime currentTime) {
        super(currentTime);
        setName("sms");
    }
}
