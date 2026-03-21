package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public final class FinnishSalesAnalyser extends SalesAnalyser {

    public FinnishSalesAnalyser(List<SalesRecord> records) {
        super(records);
        setDatesTaxes(
                new LocalDate[] {
                        LocalDate.parse("1994-06-01"),
                        LocalDate.parse("2010-01-01"),
                        LocalDate.parse("2013-01-01"),
                        LocalDate.parse("2024-09-01")
                },
                new double[] {22, 23, 24, 25.5}
        );    }
}
