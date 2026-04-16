package bonus.installments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator {

    public List<Installment> generateRowsFor(
            Integer amount, LocalDate periodStart, LocalDate periodEnd) {

        if (amount < 3) {
            return List.of(new Installment(amount, periodStart));
        }

        int months = periodEnd.getDayOfMonth() - periodStart.getDayOfMonth() + (periodEnd.getYear() - periodStart.getYear()) * 12;

        int tempPay = amount/months;
        int normalPay;
        int abovePay;

        int aboveMonths;
        int belowMonths;

        if (amount/months < 3) {
            normalPay = 3;
            abovePay = 4;

            aboveMonths = amount%3;
            belowMonths = (amount/3)-1;
        } else {
            normalPay = tempPay;
            abovePay = normalPay + 1;

            aboveMonths = amount%months;
            belowMonths = (months-aboveMonths)-1;
        }

        List<Installment> output = new ArrayList<Installment>();
        output.add(new Installment(normalPay, periodStart));


        LocalDate start = periodStart.withDayOfMonth(1);

        for (int i = 0; i < belowMonths; i++) {
            start = start.plusMonths(1);

            output.add(new Installment(normalPay, start));
        }

        for (int i = 0; i < aboveMonths; i++) {
            start = start.plusMonths(1);

            output.add(new Installment(abovePay, start));
        }

        return output;
    }
}
