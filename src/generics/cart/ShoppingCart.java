package generics.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart<T extends CartItem> {

    private ArrayList<CartEntry> cart = new ArrayList<CartEntry>();
    private Double activeCoupon = 0.0;
    private List<Double> activeDiscount = new ArrayList<>();

    private final Map<String, Double> couponToDiscount = Map.of(
            "Sale5", 5.0,
            "Sale8", 8.0,
            "Sale10", 10.0);

    public void add(T item) {
        for (CartEntry c : cart) {
            if (c.item.id().equals(item.id())) {
                c.quantity += 1;
                return;
            }
        }

        CartEntry newItem = new CartEntry(item, 1);

        cart.add(newItem);
    }

    public void removeById(String id) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).item.id().equals(id)) {
                cart.remove(i);
                return;
            }
        }
    }

    public Double getTotal() {
        return cart.stream()
                .mapToDouble(c -> c.item.price()*c.quantity)
                .sum() * (100 - getTotalDiscount()) / 100;
    }

    public List<CartEntry> getContents() {
        return cart;
    }

    public void increaseQuantity(String id) {
        for (CartEntry c : cart) {
            if (c.item.id().equals(id)) {
                c.quantity += 1;
                return;
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        activeDiscount.add(discount);
    }

    public boolean applyCoupon(String coupon) {
        if (couponToDiscount.containsKey(coupon)) {
            activeCoupon = couponToDiscount.get(coupon);
            return true;
        }
        return false;
    }

    public Double getTotalDiscount() {
        return 100 * (1 - activeDiscount.stream()
                .reduce(1.0, (a, b) -> a * (1 - b / 100))
                * (1 - activeCoupon / 100));
    }

    public void removeLastDiscount() {
        activeDiscount.removeLast();
    }

    public void addAll(Iterable<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public String toString() {
        return cart.stream()
                .map(c -> "(%s, %.1f, %d)".formatted(c.item.id(), c.item.price(), c.quantity))
                .collect(Collectors.joining(", "));
    }
}
