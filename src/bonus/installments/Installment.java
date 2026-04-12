package bonus.installments;

import java.time.LocalDate;

public record Installment(Integer amount, LocalDate date) {
}