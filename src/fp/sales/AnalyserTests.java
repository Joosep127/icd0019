package fp.sales;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class AnalyserTests {

    private final AccountingService accountingService = new AccountingService();
    private final Analyser analyser = new Analyser(new Repository(), accountingService);

    @Test
    public void findsTotalSales() {
        assertThat(analyser.getTotalSales())
                .isCloseTo(201374.12, within(0.01));
    }

    @Test
    public void findsSalesByCategory() {
        assertThat(analyser.getSalesByCategory("Office Supplies"))
                .isCloseTo(56147.03, within(0.01));
    }

    @Test
    public void findsSalesBetweenDates() {
        Double salesBetween = analyser.getSalesBetween(
                LocalDate.parse("2025-01-01"),
                LocalDate.parse("2025-03-31"));

        assertThat(salesBetween).isCloseTo(40828.25, within(0.01));
    }

    @Test
    public void findsMostExpensiveItems() {
        assertThat(analyser.mostExpensiveItems())
                .isEqualTo("OFF-BI-10003527, OFF-BI-10004995, TEC-MA-10004125");
    }

    @Test
    public void statesWithBiggestSales() {
        assertThat(analyser.statesWithBiggestSales())
                .isEqualTo("California, Texas, New York");
    }

    @Test
    public void findsMostProfitableItems() {
        assertThat(analyser.findMostProfitableItems())
                .isEqualTo("TEC-MA-10000822, TEC-CO-10003237, OFF-BI-10003527");
    }

    @Test
    public void shouldFetchProfitMarginOnlyOnceForEachProduct() {
        analyser.findMostProfitableItems();

        assertThat(accountingService.getCallCount()).isEqualTo(650);
    }

    @Test
    public void allRecordsSortedByDatePaged() {
        List<Entry> pageZeroBy3 = analyser.getAllRecordsPaged(0, 3);

        assertThat(rowNumbers(pageZeroBy3)).containsExactly(740, 742, 741);

        List<Entry> pageOneBy4 = analyser.getAllRecordsPaged(1, 4);

        assertThat(rowNumbers(pageOneBy4)).containsExactly(594, 717, 764, 765);

        List<Entry> pageHundredBy5 = analyser.getAllRecordsPaged(100, 5);

        assertThat(rowNumbers(pageHundredBy5)).containsExactly(312, 313, 90, 91, 92);

        assertThat(rowNumbers(analyser.getAllRecordsPaged(1000, 10))).isEmpty();
    }

    private List<Integer> rowNumbers(List<Entry> list) {
        return list.stream().map(Entry::rowNo).toList();
    }
}
