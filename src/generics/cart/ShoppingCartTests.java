package generics.cart;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ShoppingCartTests {

    @Test
    public void canGetCartTotal() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 10));
        cart.add(new Product("p2", 5));

        assertThat(cart.getTotal()).isCloseTo(15, within(0.1));
    }

    @Test
    public void canStoreServicesToo() {

        ShoppingCart<Service> cart = new ShoppingCart<>();

        cart.add(new Service("s1", 3));
        cart.add(new Service("s2", 2));

        assertThat(cart.getTotal()).isCloseTo(5, within(0.1));
    }

    @Test
    public void canRemoveItemsFromCart() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 10));

        cart.removeById("p1");

        assertThat(cart.getTotal()).isCloseTo(0.0, within(0.1));
    }

    @Test
    public void canAddMultipleItemsAtOnce() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.addAll(List.of(
                new Product("p1", 10),
                new Product("p2", 2)));

        assertThat(cart.getTotal()).isCloseTo(12.0, within(0.1));
    }

    @Test
    public void canReadCartContents() {

        ShoppingCart<Service> cart = new ShoppingCart<>();

        cart.add(new Service("s1", 3));
        cart.add(new Service("s2", 2));

        List<CartEntry> contents = cart.getContents();

        assertThat(contents.size()).isEqualTo(2);
        assertThat(contents.getFirst().item.id()).isEqualTo("s1");
        assertThat(contents.getFirst().item.price()).isCloseTo(3.0, within(0.1));
        assertThat(contents.getFirst().quantity).isEqualTo(1);
    }

    @Test
    public void cartHasStringRepresentation() {

        ShoppingCart<Service> cart = new ShoppingCart<>();

        cart.add(new Service("s1", 3));
        cart.add(new Service("s2", 2));

                                // (identifier, price, quantity), ...
        assertThat(cart.toString()).isEqualTo("(s1, 3.0, 1), (s2, 2.0, 1)");
    }

    @Test
    public void canIncreaseQuantityById() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 5));
        cart.add(new Product("p2", 10));

        cart.increaseQuantity("p2");

        assertThat(cart.getTotal()).isCloseTo(25, within(0.1));
        assertThat(cart.toString()).isEqualTo("(p1, 5.0, 1), (p2, 10.0, 2)");
    }

    @Test
    public void addingTheSameProductAgainIncreasesItsQuantity() {

        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 5));
        cart.add(new Product("p1", 5));

        assertThat(cart.getTotal()).isCloseTo(10.0, within(0.1));
        assertThat(cart.toString()).isEqualTo("(p1, 5.0, 2)");
    }

    @Test
    public void canAddDiscountToWholeCart() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 10));

        cart.applyDiscountPercentage(10.0);

        assertThat(cart.getTotal()).isCloseTo(9.0, within(0.1));
        assertThat(cart.toString()).isEqualTo("(p1, 10.0, 1)");
    }

    @Test
    public void canAddMultipleDiscounts() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 10));

        cart.applyDiscountPercentage(10.0);

        cart.applyDiscountPercentage(10.0);

        assertThat(cart.getTotal()).isCloseTo(8.1, within(0.1));
        assertThat(cart.toString()).isEqualTo("(p1, 10.0, 1)");
    }

    @Test
    public void canRemoveLastDiscount() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.add(new Product("p1", 10));

        cart.applyDiscountPercentage(10.0);
        cart.applyDiscountPercentage(8.0);

        assertThat(cart.getTotal()).isCloseTo(8.28, within(0.01));

        cart.removeLastDiscount();

        assertThat(cart.getTotal()).isCloseTo(9.0, within(0.01));
    }

    @Test
    public void canApplyCoupons() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.applyCoupon("Sale5");
        cart.applyCoupon("Sale8"); // overwrites previous

        assertThat(cart.getTotalDiscount()).isCloseTo(8.0, within(0.1));
    }

    @Test
    public void resultIndicatesWhetherCouponIsApplied() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        assertThat(cart.applyCoupon("Sale8")).isTrue();
        assertThat(cart.applyCoupon("non existent")).isFalse();
    }

    @Test
    public void returnsTotalDiscount() {
        ShoppingCart<Product> cart = new ShoppingCart<>();

        cart.applyDiscountPercentage(8.0);
        cart.applyDiscountPercentage(10.0);
        cart.applyCoupon("Sale5");

        assertThat(cart.getTotalDiscount()).isCloseTo(21.34, within(0.01));
    }

}
