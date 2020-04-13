package StudentDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class PopulateTables {

    public void populateStudentsTable(ArrayList<Integer> studentID, ArrayList<String> first, ArrayList<String> last,
                                      ArrayList<String> sex) throws Exception {
        Connection conn = ConnectToDatabase.getConnection();
        String outline = "INSERT INTO Students(studentID, firstName, lastName, sex) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(outline);
            for (int i = 0; i < first.size(); ++i) {
                ps.setInt(1, studentID.get(i));
                ps.setString(2, first.get(i));
                ps.setString(3, last.get(i));
                ps.setString(4, sex.get(i));
                ps.execute();
            }
            System.out.println("Students table populated.");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void populateCoursesTable(ArrayList<String> courseID, ArrayList<String> courseTitle,
                                     ArrayList<String> department) throws Exception {
        Connection conn = ConnectToDatabase.getConnection();
        String outline = "INSERT INTO Courses(courseID, courseTitle, department) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(outline);
            for (int i = 0; i < courseID.size(); ++i) {
                ps.setString(1, courseID.get(i));
                ps.setString(2, courseTitle.get(i));
                ps.setString(3, department.get(i));
                ps.execute();
            }
            System.out.println("Courses table populated.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void populateClassesTable(ArrayList<String> classCode, ArrayList<String> courseID_c, ArrayList<Integer> studentID_classes, ArrayList<Integer> year,
                                     ArrayList<String> semester, ArrayList<String> gpa) throws Exception {

        Connection conn = ConnectToDatabase.getConnection();
        String outline = "INSERT INTO Classes(classCode, courseID, studentID, year, semester, GPA) VALUES (?, ? , ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(outline);
            for (int i = 0; i < classCode.size(); ++i) {
                ps.setString(1, classCode.get(i));
                ps.setString(2, courseID_c.get(i));
                ps.setInt(3, studentID_classes.get(i));
                ps.setInt(4, year.get(i));
                ps.setString(5, semester.get(i));
                ps.setString(6, gpa.get(i));
                ps.execute();

            }
            System.out.println("Classes table populated.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

}
