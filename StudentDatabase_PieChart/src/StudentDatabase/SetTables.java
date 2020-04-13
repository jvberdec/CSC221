package StudentDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SetTables {

    public void createStudentsTable() throws Exception {
        try {
            Connection conn = ConnectToDatabase.getConnection();
            PreparedStatement ps = conn.prepareStatement(

                    "create table IF NOT EXISTS Students(" + "studentID INT UNSIGNED NOT NULL,"
                            + "firstName varchar(255)," + "lastName varchar(255)," + "sex varchar(255),"
                            + "PRIMARY KEY(studentID))");
            ps.executeUpdate();
            System.out.println("Students table created.");
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void createCoursesTable() throws Exception {
        Connection conn = ConnectToDatabase.getConnection();
        PreparedStatement ps = conn.prepareStatement(

                "create table IF NOT EXISTS Courses " + " (courseID varchar(255)," + " courseTitle varchar(255),"
                        + "department varchar(225)," + "PRIMARY KEY (courseID))");
        ps.executeUpdate();
        System.out.println("Courses table created.");
        conn.close();
    }

    public void createClassesTable() throws Exception {
        Connection conn = ConnectToDatabase.getConnection();
        PreparedStatement ps = conn.prepareStatement(

                "create table IF NOT EXISTS Classes " + "(classCode varchar (255),"
                        + "courseID varchar(225), studentID INT UNSIGNED NOT NULL,"
                        + "year INT UNSIGNED NOT NULL, semester varchar(225), GPA varchar(1),"
                        + "PRIMARY KEY (classCode))");
        ps.executeUpdate();
        System.out.println("Classes table created.");
        conn.close();
    }

    public void dropAllTables() throws Exception {
        Connection conn = ConnectToDatabase.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DROP TABLE students");
        stmt.executeUpdate("DROP TABLE classes");
        stmt.executeUpdate("DROP TABLE courses");

    }

}
