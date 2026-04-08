package fp.sales;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private final Repository repository;

    private final AccountingService accountingService;

    public Analyser(Repository repository,
                    AccountingService accountingService) {
        this.repository = repository;
        this.accountingService = accountingService;
    }

    public Double getTotalSales() {
        return repository.getEntries().stream().mapToDouble(Entry::amount).sum();
    }

    public Double getSalesByCategory(String category) {
        return repository.getEntries().stream().filter(x -> x.category().equals(category)).mapToDouble(Entry::amount).sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.getEntries().stream()
                .filter(x -> !x.date().isBefore(start) && !x.date().isAfter(end))
                .mapToDouble(Entry::amount)
                .sum();
    }

    public String mostExpensiveItems() {
        return repository.getEntries().stream()
                .sorted(Comparator.comparingDouble(Entry::amount).reversed())
                .map(Entry::productId)
                .distinct()
                .limit(3)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public String statesWithBiggestSales() {
        return repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::state,
                        Collectors.summingDouble(Entry::amount)
                )).entrySet().stream()
                .sorted(Comparator.comparingDouble((Map.Entry<String, Double> e) -> e.getValue()).reversed())
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.joining(", "));
    }

    public String findMostProfitableItems() {
        return repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::productId,
                        Collectors.summingDouble(Entry::amount)
                )).entrySet().stream()
                .map(x -> Map.entry(
                        x.getKey(),
                        x.getValue() * accountingService.getProfitMargin(x.getKey())
                ))
                .sorted(Comparator.comparing((Map.Entry<String, Double> e) -> e.getValue()).reversed())
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.joining(", "));
    }

    public List<Entry> getAllRecordsPaged(int pageNumber, int pageSize) {
        return repository.getEntries().stream()
                .sorted(Comparator.comparing(Entry::date))
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<String> getCategoryList() {
        // only needed for icd0019app

        return List.of();
    }

    public int getRecordCount() {
        // only needed for icd0019app

        return 0;
    }

}
