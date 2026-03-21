package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public class TaxFreeSalesAnalyser extends SalesAnalyser {

    public TaxFreeSalesAnalyser(List<SalesRecord> records) {
        super(records);
        setDatesTaxes(new LocalDate[0], new double[0]);
    }
}
