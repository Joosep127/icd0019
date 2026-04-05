package fp.examples;

record SalesRecord(String productId, int itemsSold) {

    @Override
    public String toString() {
        return "(%s; %s)".formatted(productId, itemsSold);
    }
}