package junit.sales;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TopSalesFinderTests {

    @Test
    public void findTopSales() {

        TopSalesFinder tsf = new TopSalesFinder();
        tsf.registerSale(new SalesRecord("p1", 20, 1));
        tsf.registerSale(new SalesRecord("p2", 20, 1));
        tsf.registerSale(new SalesRecord("p2", 20, 1));
        tsf.registerSale(new SalesRecord("p1", 10, 1));
        tsf.registerSale(new SalesRecord("p3", 50, 1));

        assertThat(ids(tsf.findItemsSoldOver(10)))
                .containsExactlyInAnyOrder("p1", "p2", "p3");

        assertThat(ids(tsf.findItemsSoldOver(39)))
                .containsExactlyInAnyOrder("p2", "p3");

        assertThat(ids(tsf.findItemsSoldOver(40)))
                .containsExactlyInAnyOrder("p3");

        assertThat(tsf.findItemsSoldOver(50)).isEmpty();
    }

    @Test
    public void canRemoveSalesRecords() {

        TopSalesFinder tsf = new TopSalesFinder();
        tsf.registerSale(new SalesRecord("p1", 5, 1));
        tsf.registerSale(new SalesRecord("p2", 20, 1));
        tsf.registerSale(new SalesRecord("p2", 10, 2));
        tsf.registerSale(new SalesRecord("p1", 4, 1));
        tsf.registerSale(new SalesRecord("p3", 50, 1));
        tsf.registerSale(new SalesRecord("p4", 2, 8));

        tsf.removeSalesRecordsFor("p2");

        assertThat(ids(tsf.findItemsSoldOver(10)))
                .containsExactlyInAnyOrder("p3", "p4");

        assertThat(ids(tsf.findItemsSoldOver(40)))
                .containsExactlyInAnyOrder("p3");

        assertThat(tsf.findItemsSoldOver(50)).isEmpty();
    }

    @Test
    public void removeShouldNotWasteMemory() {
        TopSalesFinder tsf = new TopSalesFinder();
        for (int i = 0; i < 10_000_000; i++) {
            tsf.registerSale(new SalesRecord("p1", 20, 1));

            if (i % 100_000 == 0) {
                tsf.registerSale(new SalesRecord("p2", 1, 1));
            }

            tsf.removeSalesRecordsFor("p1");
        }

        assertThat(ids(tsf.findItemsSoldOver(90)))
                .containsExactlyInAnyOrder("p2");
    }

    @Test
    public void findTopSalesFromGeneratedData() {

        TopSalesFinder tsf = new TopSalesFinder();
        for (SalesRecord record : generateRecords(10, 10)) {
            tsf.registerSale(record);
        }

        assertThat(ids(tsf.findItemsSoldOver(100)))
                .containsExactlyInAnyOrder("p1", "p3", "p4");
    }

    @Test
    public void findTopSalesFromMoreGeneratedData() {
        TopSalesFinder tsf = new TopSalesFinder();
        for (SalesRecord record : generateRecords(100, 20)) {
            tsf.registerSale(record);
        }

        assertThat(tsf.findItemsSoldOver(100).length)
                .withFailMessage("There should be 19 items that sold over 100")
                .isEqualTo(19);

        assertThat(tsf.findItemsSoldOver(700).length)
                .withFailMessage("There should be 5 items that sold over 100")
                .isEqualTo(5);

        assertThat(ids(tsf.findItemsSoldOver(700)))
                .withFailMessage("Items sold over 700 are not correct")
                .containsExactlyInAnyOrder("p5", "p6", "p11", "p14", "p15");
    }

    @Test
    public void canGetAllRecordsAsSlices() {
        TopSalesFinder tsf = new TopSalesFinder();
        tsf.registerSale(new SalesRecord("p1", 1, 1));
        tsf.registerSale(new SalesRecord("p2", 1, 1));
        tsf.registerSale(new SalesRecord("p3", 1, 1));
        tsf.registerSale(new SalesRecord("p4", 1, 1));
        tsf.registerSale(new SalesRecord("p5", 1, 1));

        SalesRecord[] firstPage = tsf.getAllRecordsPaged(0, 2);

        assertThat(firstPage.length).isEqualTo(2);
        assertThat(firstPage[0].productId()).isEqualTo("p1");
        assertThat(firstPage[1].productId()).isEqualTo("p2");

        SalesRecord[] secondPage = tsf.getAllRecordsPaged(1, 2);

        assertThat(secondPage.length).isEqualTo(2);
        assertThat(secondPage[0].productId()).isEqualTo("p3");
        assertThat(secondPage[1].productId()).isEqualTo("p4");

        SalesRecord[] thirdPage = tsf.getAllRecordsPaged(2, 2);

        assertThat(thirdPage.length).isEqualTo(1);
        assertThat(thirdPage[0].productId()).isEqualTo("p5");
    }

    private String[] ids(SalesRecordResult[] results) {
        String[] ids = new String[results.length];
        for (int i = 0; i < results.length; i++) {
            ids[i] = results[i].productId();
        }
        return ids;
    }

    private SalesRecord[] generateRecords(int recordCount, int differentProductCount) {
        SalesRecord[] records = new SalesRecord[recordCount];

        Random rand = new Random(0);
        for (int i = 0; i < recordCount; i++) {
            String id = "p" + rand.nextInt(differentProductCount);
            int price = rand.nextInt(50) + 1;
            int quantity = rand.nextInt(8) + 1;

            records[i] = new SalesRecord(id, price, quantity);
        }

        return records;
    }
}
