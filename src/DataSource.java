import java.sql.*;

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

    public static final String UPDATE_STUDENT_DETAILS = "UPDATE " + TABLE_NAME + " SET " + COLUMN_STUDENT_NAME + " =?," +
            COLUMN_STUDENT_GRADE + " =?, " + COLUMN_STUDENT_GENDER + " =? " + " WHERE " + COLUMN_STUDENT_ID + " =?";

    public static final String DELETE_STUDENT_DETAILS = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_STUDENT_ID + " =?";
    public static final String VIEW_STUDENT_DETAILS = "SELECT * FROM " + TABLE_NAME;



    private Connection connection;
    private PreparedStatement insertStudentDetails;
    private PreparedStatement updateStudentDetails;
    private PreparedStatement deleteStudentDetails;
    private PreparedStatement viewStudentDetails;


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

            return true;
        } catch (SQLException e){
            System.out.println("Couldn't connected to database " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method to insert the student details into the database.
     * @param student_id  get the student id
     * @param student_name get the student name
     * @param student_grade get the student grade
     * @param student_gender get the student gender
     * @throws SQLException catch the exception if it goes wrong
     */

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

    /**
     * update the details per given details
     * @param student_id to get student id
     * @param student_name to get student name
     * @param student_grade to get student grade
     * @param student_gender to get student gender
     * @throws SQLException catch the exception if it goes wrong
     */
    public void updateStudentDetails(int student_id, String student_name, String student_grade, String student_gender) throws SQLException{
        updateStudentDetails = connection.prepareStatement(UPDATE_STUDENT_DETAILS);
        updateStudentDetails.setString(1,student_name);
        updateStudentDetails.setString(2,student_grade);
        updateStudentDetails.setString(3,student_gender);
        updateStudentDetails.setInt(4,student_id);

        int rowAffected = updateStudentDetails.executeUpdate();
        if(rowAffected>0){
            System.out.println("Data Updated Successfully into the database");
        } else{
            System.out.println("Data not updated");
        }

    }

    /**
     * delete the student details for given id
     * @param student_id to get student id
     * @throws SQLException catch the exception if it goes wrong
     */
    public void deleteStudentDetails(int student_id) throws SQLException{
        deleteStudentDetails = connection.prepareStatement(DELETE_STUDENT_DETAILS);
        deleteStudentDetails.setInt(1,student_id);

        int rowAffected = deleteStudentDetails.executeUpdate();
        if(rowAffected >0){
            System.out.println("The data of " + student_id + " is successfully deleted ");
        } else {
            System.out.println("The data of " + student_id +  " is not deleted ");
        }
    }

    public void viewStudentDetails() throws SQLException{
        viewStudentDetails = connection.prepareStatement(VIEW_STUDENT_DETAILS);
        ResultSet results = viewStudentDetails.executeQuery();

        while (results.next()){
            int studentId = results.getInt(COLUMN_STUDENT_ID);
            String studentName = results.getString(COLUMN_STUDENT_NAME);
            String studentGrade = results.getString(COLUMN_STUDENT_GRADE);
            String studentGender = results.getString(COLUMN_STUDENT_GENDER);

            System.out.println("Student Id: " +  studentId + " " + " Student Name: " + studentName +  " " + "Student Grade: " + studentGrade + " " +
                    "Student Gender: " + studentGender);
        }


    }


}
