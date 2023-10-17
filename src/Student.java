/**
 * this class will keep tracking of student details
 * name, id, gender, grade
 */
public class Student {
    private int student_id;
    private String student_name;
    private String student_grade;
    private String student_gender;

    /**
     *
     * @return the student id
     */
    public int getStudent_id() {
        return student_id;
    }

    /**
     *
     * @return the student name
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     *
     * @return student grade
     */
    public String getStudent_grade() {
        return student_grade;
    }

    /**
     *
     * @return the student gender
     */
    public String getStudent_gender() {
        return student_gender;
    }
}
