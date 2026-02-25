package junit.sales;

public class TopSalesFinder {

    SalesRecordResult[] listSRR = new SalesRecordResult[0];

    public void registerSale(SalesRecord record) {

        boolean found = false;

        int total = record.productPrice * record.itemsSold;
        
        for (int i = 0; i < listSR.length; i++) {
          if (listSRR[i].productId.equals(record.productId)) {
            listSRR[i] += total;
            found = true;
            break;
          }
        }

        if (found) {
            return;
        }

        int size = listSR.length;
        SalesRecordResult[] newSRR = new SalesRecordResult[size + 1];

        for (int i = 0; i < size; i++) {
            newSRR[i] = listSRR[i];
        }

        newSRR[size] = new SalesRecordResult(record.productId, total);
        listSRR = newSRR;
    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {

        SalesRecordResult[] filtered = new SalesRecordResult[0];

        for (SalesRecordResult srr : listSRR) {
            if (srr.total > amount) {

                SalesRecordResult[] newArr = new SalesRecordResult[filtered.length + 1];

                for (int i = 0; i < filtered.length; i++) {
                    newArr[i] = filtered[i];
                }

                newArr[filtered.length] = sr;
                filtered = newArr;
            }
        }

        return filtered;
    }

    public void removeSalesRecordsFor(String productId) {
        int count = 0;
        for (SalesRecordResult srr : listSRR) {
            if (sr != null && !Objects.equals(srr.productId, productId)) {
              count++;
            }
        }

        SalesRecordResult[] newSRR = new SalesRecordResult[count];

        int i = 0;
        for (SalesRecordResult srr : listSRR) {
            if (!srr.productId.equals(productId)) {
                newSRR[i] = srr;
                i++;
            }
        }

        listSR = newSR;
    }
    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {
        int startingIdx = (pageNumber - 1) * pageSize;

        if (startingIdx < 0 || listSR.length < startingIdx) {
            return new SalesRecord[0];
        }

        int remainingRecords = listSR.length - startingIdx;
        int currentPageSize = Math.min(pageSize, remainingRecords);
        SalesRecord[] paginatedSR = new SalesRecord[currentPageSize];

        for (int i = 0; i < currentPageSize; i++) {
            paginatedSR[i] = listSR[i+startingIdx];
        }

        return paginatedSR;
    }

    public int getRecordCount() {

        // only needed for the sample application

        // returns the count of all records

        return listSR.length;
    }

    public void removeRecord(String id) {
        // only needed for the sample application

        // removes record with specific id
        removeSalesRecordsFor(id);
    }

}


