package poly.customer;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryTests {

    @Test
    public void canAddCustomers() {
        String randomId = UUID.randomUUID().toString();

        RegularCustomer customer = new RegularCustomer(
                randomId, "David", 0, LocalDate.now());

        getRepository().save(customer);

        assertThat(getRepository().getCustomerById(randomId).get()).isEqualTo(customer);
    }

    @Test
    public void canDeleteCustomers() {
        RegularCustomer customer = new RegularCustomer(
                "c4", "David", 0, LocalDate.now());

        getRepository().save(customer);

        getRepository().remove("c4");

        assertThat(getRepository().getCustomerById("c4").isPresent()).isFalse();
    }

    @Test
    public void canUpdateCustomers() {

        RegularCustomer customer = new RegularCustomer(
                "c4", "David", 0, LocalDate.now());

        getRepository().save(customer);

        int countBefore = getRepository().getCustomerCount();

        getRepository().save(customer);

        assertThat(getRepository().getCustomerCount()).isEqualTo(countBefore);
        assertThat(getRepository().getCustomerById("c4").get()).isEqualTo(customer);
    }

    @Test
    public void customerIsChangedOnlyWhenSaved() {
        String randomId = UUID.randomUUID().toString();

        getRepository().save(new RegularCustomer(
                randomId, "David", 0, LocalDate.now()));

        AbstractCustomer customer = getRepository().getCustomerById(randomId).get();

        assertThat(customer.getBonusPoints()).isEqualTo(0);

        customer.collectBonusPointsFrom(new Order(200, LocalDate.now()));

        assertThat(customer.getBonusPoints()).isNotEqualTo(0);

        AbstractCustomer loaded = getRepository().getCustomerById(randomId).get();

        assertThat(loaded.getBonusPoints()).isEqualTo(0);
    }

    private CustomerRepository getRepository() {
        return new CustomerRepository();
    }

    @Test
    public void repositoryShouldNotUseStaticFields() {
        List<Field> fieldsNotAllowed = Arrays.stream(CustomerRepository.class.getDeclaredFields())
                .filter(field -> isStatic(field.getModifiers()))
                .filter(field -> !"FILE_PATH".equals(field.getName()))
                .toList();

        assertThat(fieldsNotAllowed).isEmpty();
    }

    private boolean isStatic(int modifierValue) {
        return (modifierValue & Modifier.STATIC) > 0;
    }
}
