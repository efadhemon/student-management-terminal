package manager;

import model.Student;
import utils.TablePrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class StudentManager {
    private final String STUDENT_DB_PATH = "src/database/student.txt";

    private Student extractStudentFromLine(String line) {
        String[] data = line.split(",");

        try {
            // For empty save error handling I did this extra condition to assign value in variable
            int id =  (data.length > 0 && data[0] != null) ? Integer.parseInt(data[0]) : 0;
            String name =  (data.length > 1 && data[1] != null) ? data[1] : "";
            String program = (data.length > 2 && data[2] != null) ? data[2] : "";
            int batch = (data.length > 3 && data[3] != null) ? Integer.parseInt(data[3]) : 0;
            float cgpa = (data.length > 4 && data[4] != null) ? Float.parseFloat(data[4]) : 0;

            return new Student(id, name, program, batch, cgpa);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    private Student searchStudentByID(int id) {
        try {
            FileReader fileReader = new FileReader(STUDENT_DB_PATH);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while ((line = reader.readLine()) != null) {
                Student student = this.extractStudentFromLine(line);

                if (student.getId() == id) {
                    return  student;
                }
            }

            fileReader.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public void addStudent() {
        System.out.println();
        System.out.println("Student added processing....");

        Scanner scanner = new Scanner(System.in);

        int id = -1;
        System.out.print("Enter student id: ");
        while (id < 0) {
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // this is because of auto skip next scan for integer issue

                Student isExist =  searchStudentByID(id);

                if (isExist != null) {
                    id = -1;
                    System.out.println("Student already exists!");
                    System.out.print("Enter another id: ");
                    continue;
                }

            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
                System.out.println("The id will be an integer number!");
                System.out.print("Please correctly enter student id: ");
            }

        }


        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student program: ");
        String program = scanner.nextLine();

        int batch = -1;
        System.out.print("Enter student batch: ");
        while (batch < 0) {
            try {
                batch = scanner.nextInt();
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
                System.out.println("Batch will be an integer  number!");
                System.out.print("Please correctly enter student batch: ");
            }

        }

        float cgpa = -1;
        System.out.print("Enter student cgpa: ");
        while (cgpa < 0) {
            try {
                cgpa = scanner.nextFloat();
                scanner.nextLine(); // this is because of auto skip next scan for flot issue
            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for flot issue
                System.out.println("Cgpa will be an flot  number!");
                System.out.print("Please correctly enter student cgpa: ");
            }

        }

        System.out.print("Enter student password: ");
        String password = scanner.nextLine();


        Student student = new Student(id, name, program, batch, cgpa,password);


        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_DB_PATH, true));
            writer.write(student.toDBString());
            writer.close();
        }
        catch(Exception e){
             System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Student added successfully.");
        System.out.println();
    }



    public void showAllStudents() {
        try {
            System.out.println();
            System.out.println("Student List: ");

            FileReader fileReader = new FileReader(STUDENT_DB_PATH);
            BufferedReader reader = new BufferedReader(fileReader);

            TablePrinter tp = new TablePrinter(Arrays.asList("ID", "Name", "Program", "Batch", "CGPA"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                Student student = extractStudentFromLine(line);

                List<String> dataItems = new ArrayList<String>();
                dataItems.add(String.valueOf(student.getId()));
                dataItems.add(student.getName());
                dataItems.add(student.getProgram());
                dataItems.add(String.valueOf(student.getBatch()));
                dataItems.add(String.valueOf(student.getCgpa()));

                tp.addRow(dataItems);
            }

            tp.print();

            fileReader.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }

    public void searchStudentByIDAndPrint() {
        System.out.println();
        System.out.println("Searching student by ID...");

        Scanner scanner = new Scanner(System.in);

        int id = -1;
        System.out.print("Enter student id: ");
        while (id < 0) {
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
                System.out.println("The id will be an integer number!");
                System.out.print("Please correctly enter student id: ");
            }

        }

        Student student =  searchStudentByID(id);

        if (student != null) {
            TablePrinter tp = new TablePrinter(Arrays.asList("ID", "Name", "Program", "Batch", "CGPA"));
            List<String> dataItems = new ArrayList<String>();
            dataItems.add(String.valueOf(student.getId()));
            dataItems.add(student.getName());
            dataItems.add(student.getProgram());
            dataItems.add(String.valueOf(student.getBatch()));
            dataItems.add(String.valueOf(student.getCgpa()));

            tp.addRow(dataItems);
            tp.print();
        }else {
            System.out.println("Student with ID " + id + " not found.");

        }
        System.out.println();
    }
}
