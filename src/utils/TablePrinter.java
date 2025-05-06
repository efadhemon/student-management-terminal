package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TablePrinter {

    private final List<String> headers;
    private final List<List<String>> rows = new ArrayList<>();
    private final List<Integer> columnWidths;

    public TablePrinter(List<String> headers) {
        this.headers = headers;
        this.columnWidths = new ArrayList<>();

        // Initialize column widths with header lengths
        for (String header : headers) {
            columnWidths.add(header.length());
        }
    }

    public void addRow(List<String> row) {
        // Adjust column widths and extend if needed
        for (int i = 0; i < row.size(); i++) {
            String cell = row.get(i);
            if (i >= columnWidths.size()) {
                columnWidths.add(cell.length());
            } else {
                columnWidths.set(i, Math.max(columnWidths.get(i), cell.length()));
            }
        }
        rows.add(row);
    }

    public void print() {
        // Adjust header width if rows had more columns
        while (headers.size() < columnWidths.size()) {
            headers.add("Column" + (headers.size() + 1));
        }

        printSeparator();
        printRow(headers);
        printSeparator();

        for (List<String> row : rows) {
            printRow(row);
        }

        printSeparator();
    }

    private void printSeparator() {
        for (int width : columnWidths) {
            System.out.print("+" + "-".repeat(width + 2));
        }
        System.out.println("+");
    }

    private void printRow(List<String> row) {
        for (int i = 0; i < columnWidths.size(); i++) {
            String cell = (i < row.size()) ? row.get(i) : "";
            System.out.printf("| %-" + columnWidths.get(i) + "s ", cell);
        }
        System.out.println("|");
    }

//    // Example usage
//    public static void main(String[] args) {
//        TablePrinter tp = new TablePrinter(Arrays.asList("ID", "Name"));
//
//        tp.addRow(Arrays.asList("1", "Alice", "alice@example.com")); // Extra column
//        tp.addRow(Arrays.asList("2")); // Missing column
//        tp.addRow(Arrays.asList("3", "Charlie", "charlie@example.com", "Extra"));
//
//        tp.print();
//    }
}