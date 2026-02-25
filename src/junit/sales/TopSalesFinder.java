package junit.sales;

public class TopSalesFinder {

    SalesRecordResult[] listSRR = new SalesRecordResult[0];
    SalesRecord[] listSR = new SalesRecord[0];

    public void registerSale(SalesRecord record) {

        boolean found = false;

        int total = record.productPrice() * record.itemsSold();
        
        for (int i = 0; i < listSRR.length; i++) {
          if (listSRR[i].productId().equals(record.productId())) {
            listSRR[i] = listSRR[i].addTotal(total);
            found = true;
            break;
          }
        }

        int sizeSR = listSRR.length;
        SalesRecord[] newSR = new SalesRecord[sizeSR + 1];

        for (int i = 0; i < sizeSR; i++) {
            newSR[i] = listSR[i];
        }

        newSR[sizeSR] = record;
        listSR = newSR;

        if (!found) {
            int sizeSRR = listSRR.length;
            SalesRecordResult[] newSRR = new SalesRecordResult[sizeSRR + 1];

            for (int i = 0; i < sizeSRR; i++) {
                newSRR[i] = listSRR[i];
            }

            newSRR[sizeSRR] = new SalesRecordResult(record.productId(), total);
            listSRR = newSRR;
        }
    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {

        SalesRecordResult[] filtered = new SalesRecordResult[0];

        for (SalesRecordResult srr : listSRR) {
            if (srr.total() > amount) {

                SalesRecordResult[] newArr = new SalesRecordResult[filtered.length + 1];

                for (int i = 0; i < filtered.length; i++) {
                    newArr[i] = filtered[i];
                }

                newArr[filtered.length] = srr;
                filtered = newArr;
            }
        }

        return filtered;
    }

    public void removeSalesRecordsFor(String productId) {
        int count = 0;
        for (SalesRecordResult srr : listSRR) {
            if (srr != null && !srr.productId().equals(productId)) {
              count++;
            }
        }

        SalesRecordResult[] newSRR = new SalesRecordResult[count];

        int i = 0;
        for (SalesRecordResult srr : listSRR) {
            if (!srr.productId().equals(productId)) {
                newSRR[i] = srr;
                i++;
            }
        }

        listSRR = newSRR;
    }
    public SalesRecord[] getAllRecordsPaged(int pageNumber, int pageSize) {
        int startingIdx = pageNumber * pageSize;

        if (startingIdx < 0 || listSR.length < startingIdx) {
            return new SalesRecord[0];
        }

        int remainingRecords = listSR.length - startingIdx;
        int currentPageSize = Math.min(pageSize, remainingRecords);
        SalesRecord[] paginatedSR = new SalesRecord[currentPageSize];

        System.out.println(currentPageSize);

        for (int i = 0; i < currentPageSize; i++) {
            paginatedSR[i] = listSR[i+startingIdx];
        }

        return paginatedSR;
    }

    public int getRecordCount() {

        // only needed for the sample application

        // returns the count of all records

        return listSRR.length;
    }

    public void removeRecord(String id) {
        // only needed for the sample application

        // removes record with specific id
        removeSalesRecordsFor(id);
    }

}


