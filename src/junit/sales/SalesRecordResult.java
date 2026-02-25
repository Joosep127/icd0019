package junit.sales;

public record SalesRecordResult(String productId, int total) {

    public SalesRecordResult AddTotal(int add) {
        return new SalesRecordResult(productId, total + add);
    }
}
