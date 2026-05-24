package reflection.serializer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SerializerTests {
    @Test
    public void serializesAndDeserializesObjects() {

        Customer customer = new Customer("Alice", "Smith");

        String asString = new Serializer().serialize(customer);

        assertThat(asString).isEqualTo("firstName:Alice|lastName:Smith");

        Customer restored = new Serializer()
                .deserialize(asString, Customer.class);

        assertThat(restored.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(restored.getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    public void canHandleConflictingSymbols() {
        Customer customer = new Customer("Alice%25", "Sm|th:");

        String asString = new Serializer().serialize(customer);

        assertThat(asString).isEqualTo("firstName:Alice%2525|lastName:Sm%7cth%3a");

        Customer restored = new Serializer()
                .deserialize(asString, Customer.class);

        assertThat(restored.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(restored.getLastName()).isEqualTo(customer.getLastName());
    }
}