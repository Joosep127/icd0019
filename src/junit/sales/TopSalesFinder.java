package junit.sales;

public class TopSalesFinder {

    SalesRecord[] listSR = new SalesRecord[0];

    public void registerSale(SalesRecord record) {

        record.productPrice *= record.itemsSold;
        record.itemsSold = 1;
        for (int i = 0; i < listSR.length; i++) {
          if (!listSR[i].productId.equals(record.productId)) {
            continue
          }
          listSR[i] += record.productPrice;
          return
        }

        int size = listSR.length;
        SalesRecord[] newSR = new SalesRecord[size + 1];

        for (int i = 0; i < size; i++) {
            newSR[i] = listSR[i];
        }

        newSR[size] = record;
        listSR = newSR;
    }

    public SalesRecordResult[] listSRToListRecordResult(SalesRecord[] listSR) {
        SalesRecordResult[] listSRR = new SalesRecordResult[listSR.length];
        SalesRecord sr;
        for (int i = 0; i < listSR.length; i++) {
            sr = listSR[i];
            listSRR[i] = new SalesRecordResult(sr.productId(), sr.itemsSold());
        }
        return listSRR;
        //return new SalesRecordResult[] {new SalesRecordResult("gei",1)};
    }

    public SalesRecordResult[] findItemsSoldOver(int amount) {

        SalesRecord[] filtered = new SalesRecord[0];

        for (SalesRecord sr : listSR) {
            if (sr.itemsSold() > amount) {

                SalesRecord[] newArr = new SalesRecord[filtered.length + 1];

                for (int i = 0; i < filtered.length; i++) {
                    newArr[i] = filtered[i];
                }

                newArr[filtered.length] = sr;
                filtered = newArr;
            }
        }

        return listSRToListRecordResult(filtered);
    }

    public void removeSalesRecordsFor(String productId) {
        int count = 0;
        for (SalesRecord sr : listSR) {
            if (sr != null && !Objects.equals(sr.productId(), productId)) {
              count++;
            }
        }

        SalesRecord[] newSR = new SalesRecord[count];

        int i = 0;
        for (SalesRecord sr : listSR) {
            if (!sr.productId().equals(productId)) {
                newSR[i] = sr;
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


