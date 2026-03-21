package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public final class EstonianTaxSalesAnalyser extends SalesAnalyser {

    public EstonianTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
        setDatesTaxes(
                new LocalDate[] {
                        LocalDate.parse("2009-07-01"),
                        LocalDate.parse("2024-01-01"),
                        LocalDate.parse("2025-07-01")
                },
                new double[] {20, 22, 24}
        );    }


}
