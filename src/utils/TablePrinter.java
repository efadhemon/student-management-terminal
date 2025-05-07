package utils;

import java.util.ArrayList;
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
        List<String> trimmedRow = row.size() > headers.size() ? row.subList(0, headers.size()) : row;

        for (int i = 0; i < trimmedRow.size(); i++) {
            String cell = trimmedRow.get(i);
            columnWidths.set(i, Math.max(columnWidths.get(i), cell.length()));
        }
        rows.add(trimmedRow);
    }

    public void print() {
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
        for (int i = 0; i < headers.size(); i++) {
            String cell = (i < row.size() && row.get(i) != null) ? row.get(i) : "";
            System.out.printf("| %-" + columnWidths.get(i) + "s ", cell);
        }
        System.out.println("|");
    }
}