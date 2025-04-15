package javanexuspots.services;

import java.io.*;
import java.util.*;

public abstract class AbstractService<T> {
    protected final String filePath;

    public AbstractService(String filePath) {
        this.filePath = filePath;
    }

    // abstract method for saving
    public abstract List<T> loadData();
    public abstract void saveData(List<T> data);

    // reading the data from txt
    protected List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // writing data into the database txt
    protected void writeFile(List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
