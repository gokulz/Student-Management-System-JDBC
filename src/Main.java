import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("couldn't open datasource");
        }
        Scanner sc = new Scanner(System.in);
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


    }
}