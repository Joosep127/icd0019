package fp.sales;

import namespace.A;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {

        // reads lines form the file and creates entry objects for each line.
        List<Entry> output = new ArrayList<Entry>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (!Character.isDigit(values[0].charAt(0))) {
                    continue;
                }

                try {
                    int rowNo = Integer.parseInt(values[0]);
                    Entry entry = new Entry(
                            rowNo,
                            values[3],
                            LocalDate.parse(values[1], formatter),
                            values[2],
                            values[4],
                            Double.parseDouble(values[6].replace(',', '.')) // Sales ✅
                    );
                    output.add(entry);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in row: " + line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing columns in row: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: data.csv");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return output;
    }

}
