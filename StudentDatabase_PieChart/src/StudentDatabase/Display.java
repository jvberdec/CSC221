package StudentDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Display {

    public void getDisplay() throws SQLException {
        Connection conn = ConnectToDatabase.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student.classes WHERE semester = 'Fall' AND year = 2019 AND courseID = 'CSC 211'");
        System.out.println("courseID       studentID     GPA");

        int numStudents = 0;

        while (rs.next()) {
            String course = rs.getString("courseID");
            int id = rs.getInt("studentID");
            String grade = rs.getString("GPA");
            System.out.println(course + "        " + id + "           " + grade);
            numStudents += 1;
        }
        System.out.println("The number of students taking CSC 211 during Fall 2019 is: " + numStudents + ".");


    }


}
