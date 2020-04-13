package StudentDatabase;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddIndividualRows {

    public void addToStudentsTable(int studentID, String firstName, String lastName, String sex) {
        Connection conn = ConnectToDatabase.getConnection();
        try {
            PreparedStatement inserted = conn.prepareStatement("INSERT INTO Students(studentID, firstName, lastName, sex) VALUES (?, ?, ?, ?)");
            inserted.setInt(1, studentID);
            inserted.setString(2, firstName);
            inserted.setString(3, lastName);
            inserted.setString(4, sex);
            inserted.execute();
            System.out.println("The students table has been updated with your values.");

        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your studentID is distinct from everyone else's. Please try again.");
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your studentID is distinct from everyone else's. Please try again.");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void addToCoursesTable(String courseID, String courseTitle, String department) {
        Connection conn = ConnectToDatabase.getConnection();
        try {
            PreparedStatement inserted = conn.prepareStatement("INSERT INTO Courses(courseID, courseTitle, department) VALUES (?, ?, ?)");
            inserted.setString(1, courseID);
            inserted.setString(2, courseTitle);
            inserted.setString(3, department);
            inserted.execute();
            System.out.println("The courses table has been updated with your values.");
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your courseID is distinct from the rest. Please try again.");
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your courseID is distinct from the rest. Please try again.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addToClassesTable(String courseCode, String courseID, int studentID, int year, String semester, String GPA){
        Connection conn = ConnectToDatabase.getConnection();
        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO Classes(classCode, courseID, studentID, year, semester, GPA) VALUES (?, ? , ?, ?, ?, ?)");
            insert.setString(1, courseCode);
            insert.setString(2, courseID);
            insert.setInt(3, studentID);
            insert.setInt(4, year);
            insert.setString(5, semester);
            insert.setString(6, GPA);
            insert.execute();
            System.out.println("The classes table has been updated with your values.");
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your courseID is distinct from the rest. Please try again.");
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Please make sure that your courseID is distinct from the rest. Please try again.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
