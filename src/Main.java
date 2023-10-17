import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("couldn't open datasource");
        }

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println();
            System.out.println("1. Insert the student");
            System.out.println("2. Update the student");
            System.out.println("3. Delete the student");
            System.out.println("4. View the student");
            System.out.println("5. Exit the student");
            System.out.println();

            System.out.print("Enter the choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the Student ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter The Student Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter The Student grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter The Student gender: ");
                    String gender = sc.nextLine();

                    try {
                        dataSource.insertStudentDetails(id,
                                name,
                                grade,
                                gender);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.print("Enter the Student ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter The Student Name: ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.print("Enter The Student grade: ");
                    grade = sc.nextLine();
                    System.out.print("Enter The Student gender: ");
                    gender = sc.nextLine();
                    try{
                        dataSource.updateStudentDetails(id,
                                name,
                                grade,
                                gender);
                    } catch (SQLException e) {
                        System.out.println("Failed " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.print("Enter the Student id to delete: ");
                    id = sc.nextInt();
                    try{
                        dataSource.deleteStudentDetails(id);
                    } catch (SQLException e){
                        System.out.println("Failed " + e.getMessage());
                        e.printStackTrace();
                    } break;
                case 4:
                    try{
                        dataSource.viewStudentDetails();
                    } catch (SQLException e){
                        System.out.println("Failed to load students " + e.getMessage());
                        e.printStackTrace();
                    } break;
                case 5:
                    System.out.println("Exit");
                    System.out.println("Thank You");
                    System.exit(0);
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid Choice");

            }

        }



    }
}