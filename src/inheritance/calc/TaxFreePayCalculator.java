package inheritance.calc;

public class TaxFreePayCalculator extends PayCalculator {

    @Override
    public Double getWeeklyPayAfterTaxes(Integer hoursWorked) {
        double multiplier = (1 - TAX_RATE / 100);
        return (straightPay(hoursWorked) +
                overtimePay(hoursWorked));
    }
}
