import manager.AuthManager;
import manager.StudentManager;

import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        Scanner scanner = new Scanner(System.in);

        AuthManager authManager = new AuthManager();

        System.out.println("Welcome! Please login first!");

        boolean isAuthenticated = true;

        while (!isAuthenticated){
           System.out.print("Enter your username: ");
           String username = scanner.nextLine();
           System.out.print("Enter your password: ");
           String password = scanner.nextLine();
           isAuthenticated = authManager.login(username, password);
           if(!isAuthenticated){
               System.out.println("Invalid username or password!");
           }
        }



       do {
           System.out.println();
           System.out.println("Welcome to the Student Manager!");
           System.out.println("Press 1 to show all students");
           System.out.println("Press 2 to add new student");
           System.out.println("Press 3 to search student by id");
           System.out.println("Press 0 to exit");
           System.out.println();


           int key = -1;
           System.out.print("Enter your choice: ");
           while (key < 0) {
               try {
                   key = scanner.nextInt();
                   scanner.nextLine(); // this is because of auto skip next scan for integer issue
               } catch (Exception e) {
                   scanner.nextLine(); // this is because of auto skip next scan for integer issue
                   System.out.println("Choice should be an integer number!");
                   System.out.print("Enter your choice: ");
               }

           }


           StudentManager studentManager = new StudentManager();

           switch(key) {
               case 1:
                   studentManager.showAllStudents();
                   break;
               case 2:
                   studentManager.addStudent();
                   break;
               case 3:
                   studentManager.searchStudentByIDAndPrint();
                   break;
               case 0:
                   System.out.println("Goodbye!");
                   System.exit(0);
                   break;
               default:
                   System.out.println("Invalid choice! Please try again!");
           }

       }while (isAuthenticated);

    }
}
