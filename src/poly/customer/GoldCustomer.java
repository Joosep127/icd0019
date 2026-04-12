package poly.customer;

import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    public GoldCustomer(String[] items) {
        super(items[1], items[2], Integer.parseInt(items[3]));
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        bonusPoints += (int) (order.total() * 1.5);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GoldCustomer other = (GoldCustomer) obj;

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
        return String.format("GOLD;%s;%s;%d;",
                id,
                name,
                bonusPoints);
    }

}