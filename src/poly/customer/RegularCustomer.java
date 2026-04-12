package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {

    public LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;
    }

    public RegularCustomer(String[] items) {
        super(items[1], items[2], Integer.parseInt(items[3]));
        this.lastOrderDate = LocalDate.parse(items[4]);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        lastOrderDate = order.date();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        if (order.total() >= 100) {
            if (!lastOrderDate.isBefore(oneMonthAgo)) {
                this.bonusPoints += (int) (order.total() * 1.5);
            } else {
                this.bonusPoints += (int) order.total();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RegularCustomer other = (RegularCustomer) obj;

        return Objects.equals(id, other.getId()) &&
                Objects.equals(name, other.getName()) &&
                bonusPoints == other.getBonusPoints();
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public String asString() {
        String date = lastOrderDate.toString();

        return String.format("REGULAR;%s;%s;%d;%s",
                id,
                name,
                bonusPoints,
                date);
    }
}