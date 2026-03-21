package inheritance.analyser;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.*;
import java.time.LocalDate;

public class SalesAnalyser {

  private LocalDate[] dates;
  private double[] taxes;
  private List<SalesRecord> records;

  public SalesAnalyser(List<SalesRecord> records) {
    this.records = records;
  }

  protected void setDatesTaxes(LocalDate[] dates, double[] taxes) {
    this.dates = dates;
    this.taxes = taxes;
  }

  private int getTaxIndex(SalesRecord record) {
    for (int i = taxes.length - 1; i > -1; i--) {
      if (!record.date().isBefore(dates[i])) {
        return i;
      }
    }
    return -1;
  }

  private HashMap<String, Double> getTotalSalesHelper() {
    int index;
    double tax;
    Double total;
    HashMap<String, Double> output = new HashMap<String, Double>(0);
    for (SalesRecord record : records) {
      index = getTaxIndex(record);
      tax = index != -1 ? taxes[index] : 0;
      total = (record.productPrice() * record.itemsSold() / (1 + (double) (tax / 100)));
      if (!output.containsKey(record.productId())) {
        output.put(record.productId(), total);
      } else {
        output.put(record.productId(), output.get(record.productId()) + total);
      }
    }
    for (Map.Entry<String, Double> entry : output.entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
    return output;
  }

  public Double getTotalSales() {
    double sum = 0.0;
    for (Double value : getTotalSalesHelper().values()) {
      sum += value;
    }
    return sum;
  }

  public Double getTotalSalesByProductId(String id) {
    return getTotalSalesHelper().get(id);
  }

  public List<String> getTop3PopularItems() {
    List<String> list = new ArrayList<>();

    for (SalesRecord r : records) {
      list.add(r.productId());
    }
    // Step 1: Count frequencies
    Map<String, Integer> freq = new HashMap<>();
    // Step 2: Track last occurrence index
    Map<String, Integer> lastIndex = new HashMap<>();

    for (int i = 0; i < list.size(); i++) {
      String s = list.get(i);
      freq.put(s, freq.getOrDefault(s, 0) + 1);
      lastIndex.put(s, i); // overwrite → keeps latest index
    }

    // Step 3: Sort
    List<String> result = freq.keySet()
        .stream()
        .sorted((a, b) -> {
          int freqCompare = Integer.compare(freq.get(b), freq.get(a)); // DESC
          if (freqCompare != 0)
            return freqCompare;

          // If same frequency → later one first
          return Integer.compare(lastIndex.get(b), lastIndex.get(a));
        })
        .collect(Collectors.toList());

    return result.subList(0, Math.min(3, result.size()));
  }

  public Double getLargestTotalSalesAmountForSingleItem() {
    Double highest_value = -1.0;

    for (Double value : getTotalSalesHelper().values()) {
      if (value > highest_value) {
        highest_value = value;
      }
    }

    return highest_value;
  }

  protected static LocalDate getDate(String date) {
    return LocalDate.parse(date);
  }
}
