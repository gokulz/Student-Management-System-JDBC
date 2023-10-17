import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * this class will be used to connect with database
 * and insert operations will be added.
 */
public class DataSource {
    public static final String DB_NAME = "student.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/Adminstrator/Desktop/Student Management System" + DB_NAME;

    public static final String TABLE_NAME = "student";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENT_NAME = "student_name";
    public static final String COLUMN_STUDENT_GRADE = "student_grade";
    public static final String COLUMN_STUDENT_GENDER = "student_gender";

   public static final String INSERT_STUDENT_DETAILS = "INSERT INTO " + TABLE_NAME + '(' + COLUMN_STUDENT_ID + ", " +
           COLUMN_STUDENT_NAME + ", " + COLUMN_STUDENT_GRADE + ", " + COLUMN_STUDENT_GENDER + ") VALUES(?,?,?,?)";

    private Connection connection;
    private PreparedStatement insertStudentDetails;


    /**
     *
     * @return database connection
     */
    public boolean open(){
         try{

             connection = DriverManager.getConnection(CONNECTION_STRING);

             String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                     COLUMN_STUDENT_ID + " INTEGER, " +
                     COLUMN_STUDENT_NAME + " TEXT, " +
                     COLUMN_STUDENT_GRADE + " TEXT, " +
                     COLUMN_STUDENT_GENDER + " TEXT)";

             connection.createStatement().execute(createTableSQL);

             insertStudentDetails = connection.prepareStatement(INSERT_STUDENT_DETAILS);
             return true;
         } catch (SQLException e){
             System.out.println("Couldn't connected to database " + e.getMessage());
             e.printStackTrace();
             return false;
         }
    }

    public void insertStudentDetails(int student_id, String student_name,String student_grade, String student_gender) throws SQLException{
        insertStudentDetails = connection.prepareStatement(INSERT_STUDENT_DETAILS);
        insertStudentDetails.setInt(1,student_id);
        insertStudentDetails.setString(2,student_name);
        insertStudentDetails.setString(3,student_grade);
        insertStudentDetails.setString(4,student_gender);

        int rowsAffected = insertStudentDetails.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Data Inserted Successfully into the database");
        } else{
            System.out.println("Data not inserted");
        }

    }


}
