package poly.customer;

import namespace.A;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    public Optional<AbstractCustomer> getCustomerById(String id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts[1].equals(id)) {
                    if (parts[0].equals("GOLD")) {
                        return Optional.of(new GoldCustomer(parts));
                    }
                    if (parts[0].equals("REGULAR")) {
                        return Optional.of(new RegularCustomer(parts));
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("yep");
        }
        return Optional.empty();
    }

    public void save(AbstractCustomer customer) {
        try {
            if (getCustomerById(customer.id).isEmpty()) {
                create(customer);
            }
            File inputFile = new File(FILE_PATH);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");


                if (parts.length >= 3 && parts[1].equals(customer.id)) {
                    writer.write(customer.asString());
                } else {
                    writer.write(line);
                }

                writer.newLine();

            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("gei");
        }
    }

    private void create(AbstractCustomer customer) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH, true)
        );

        writer.newLine();
        writer.write(customer.toString());

        writer.close();
    }

    public void remove(String id) {
        try {
            File inputFile = new File(FILE_PATH);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length >= 3 && !parts[1].equals(id)) {
                    writer.write(line);
                }

                writer.newLine();

            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("gei");
        }

    }

    public int getCustomerCount() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));
            return (int) bf.lines().count();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return List.of(); // only needed for icd0019app project
    }
}
