package junit.sales;

public record SalesRecordResult(String productId, int total) {

    public SalesRecordResult addTotal(int add) {
        return new SalesRecordResult(productId, total + add);
    }
}
