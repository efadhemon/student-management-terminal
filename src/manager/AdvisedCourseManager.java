package manager;

import model.AdvisedCourse;
import model.Student;
import utils.TablePrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdvisedCourseManager {

    private final String ADVISED_DB_PATH = "src/database/advised-course.txt";

    private final StudentManager studentManager = new StudentManager();

    private AdvisedCourse extractAdvisedFromLine(String line) {
        String[] data = line.split(",");

        try {
            // For empty save error handling I did this extra condition to assign value in variable
            int studentId =  (data.length > 0 && data[0] != null) ? Integer.parseInt(data[0]) : 0;
            String courseCode =  (data.length > 1 && data[1] != null) ? data[1] : "";


            return new AdvisedCourse(studentId, courseCode);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    private AdvisedCourse[] searchByStudentId(int studentId) {
        try {
            FileReader fileReader = new FileReader(ADVISED_DB_PATH);
            BufferedReader reader = new BufferedReader(fileReader);

            List<AdvisedCourse> courseAdvisedList = new ArrayList<>();

            String line = null;
            while ((line = reader.readLine()) != null) {
                AdvisedCourse advised = this.extractAdvisedFromLine(line);

                if (advised.getStudentId() == studentId) {
                    courseAdvisedList.add(advised);
                }
            }

            fileReader.close();
            reader.close();

            if(!courseAdvisedList.isEmpty()) {
                return courseAdvisedList.toArray(new AdvisedCourse[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public void addAdvised() {
        System.out.println();
        System.out.println("Course added processing....");

        Scanner scanner = new Scanner(System.in);

        int studentId = -1;
        System.out.print("Enter student id: ");
        while (studentId < 0) {
            try {
                studentId = scanner.nextInt();
                scanner.nextLine(); // this is because of auto skip next scan for integer issue

                Student student =  this.studentManager.searchStudentByID(studentId);

                if (student == null) {
                    studentId = -1;
                    System.out.println("Student not found with  this id");
                    System.out.print("Enter another id: ");
                    continue;
                }

            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
                System.out.println("The student id will be an integer number!");
                System.out.print("Please correctly enter student id: ");
            }

        }


        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();


        AdvisedCourse advised = new AdvisedCourse(studentId, courseCode);


        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ADVISED_DB_PATH, true));
            writer.write(advised.toDBString());
            writer.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Course added successfully.");
        System.out.println();
    }



    public void showAllAdvised() {
        try {
            System.out.println();
            System.out.println("Advised List: ");

            FileReader fileReader = new FileReader(ADVISED_DB_PATH);
            BufferedReader reader = new BufferedReader(fileReader);

            TablePrinter tp = new TablePrinter(Arrays.asList("Course Code", "Student Id", "Student Name", "Program", "Batch", "CGPA"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                AdvisedCourse advised = this.extractAdvisedFromLine(line);
                Student student = this.studentManager.searchStudentByID(advised.getStudentId());

                List<String> dataItems = new ArrayList<String>();

                dataItems.add(advised.getCourseCode());
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

    public void searchAdvisedByStudentIdAndPrint() {
        System.out.println();
        System.out.println("Searching advised  by student ID...");

        Scanner scanner = new Scanner(System.in);

        int studentId = -1;
        System.out.print("Enter student id: ");
        while (studentId < 0) {
            try {
                studentId = scanner.nextInt();
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
            } catch (Exception e) {
                scanner.nextLine(); // this is because of auto skip next scan for integer issue
                System.out.println("The id will be an integer number!");
                System.out.print("Please correctly enter student id: ");
            }

        }

        AdvisedCourse[] advisedCourses =  this.searchByStudentId(studentId);

        if (advisedCourses != null) {
            TablePrinter tp = new TablePrinter(Arrays.asList("Course Code", "Student Id", "Student Name", "Program", "Batch", "CGPA"));
            for (AdvisedCourse advised : advisedCourses) {
                Student student = this.studentManager.searchStudentByID(advised.getStudentId());

                List<String> dataItems = new ArrayList<String>();

                dataItems.add(advised.getCourseCode());
                dataItems.add(String.valueOf(student.getId()));
                dataItems.add(student.getName());
                dataItems.add(student.getProgram());
                dataItems.add(String.valueOf(student.getBatch()));
                dataItems.add(String.valueOf(student.getCgpa()));

                tp.addRow(dataItems);
            }
            tp.print();
        }else {
            System.out.println("Advised with Student ID " + studentId + " not found.");

        }
        System.out.println();
    }
}
