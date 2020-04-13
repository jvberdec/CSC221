package StudentDatabase;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class PieChart {
    private String course;
    private String semester;
    private int year;

    private int canvasWidth;
    private int canvasHeight;


    void setCourse(String c) {
        course = c;
    }

    void setSemester(String s) {
        semester = s;
    }

    void setYear(int y) {
        year = y;
    }


    private Map<String, Integer> getNumPerGrade() throws SQLException {
        Connection conn = ConnectToDatabase.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT GPA FROM student.classes WHERE courseID = '" + course + "' AND year = '" + year + "' AND semester = '" + semester + "'");
        Map<String, Integer> numPerGrade = new HashMap<>();

        while (rs.next()) {
            String k = rs.getString("GPA");
            if (numPerGrade.containsKey(k)) {
                numPerGrade.put(k, numPerGrade.get(k) + 1);
            } else {
                numPerGrade.put(k, 1);
            }

        }
        return numPerGrade;
    }

    private int getTotalStudents() throws SQLException {
        int totalStudents = 0;
        for (Map.Entry<String, Integer> entry : getNumPerGrade().entrySet()) {
            totalStudents += entry.getValue();
        }
        return totalStudents;
    }

    private Map<String, Double> getPieSlices() throws SQLException {
        Map<String, Double> gradeAngles = new HashMap<>();

        for (Map.Entry<String, Integer> entry : getNumPerGrade().entrySet()) {
//            Converting to a double to calculate the inner angle
            double d = entry.getValue();
            gradeAngles.put(entry.getKey(), (d / getTotalStudents()) * 360);
        }
        return gradeAngles;
    }

    public void retrieveCanvasSize(int w, int h) {
        canvasWidth = w;
        canvasHeight = h;
    }


    void draw(GraphicsContext gc) throws SQLException {
        double startingAngle = 0;
        double rectPosition = 40;
        double height = 0;
        int n = 0;

        int x = canvasWidth - 820;
        int y = canvasHeight - 610;
        int arcWidth = canvasWidth - 400;
        int arcHeight = canvasHeight - 200;

        ArrayList<Color> currentColor = new ArrayList<>();

        for (MyColor colors : MyColor.values()) {
            currentColor.add(colors.getColor());
        }

        for (Map.Entry<String, Double> entry : getPieSlices().entrySet()) {
            gc.setFill(currentColor.get(n));
//            Outputting arcs that make the pie
            gc.fillArc(x, y, arcWidth, arcHeight, startingAngle, entry.getValue(), ArcType.ROUND);
//            Outputting small square that make the colors of the legend
            gc.fillRect(650, rectPosition + 1.3 * (height), 20, 20);
//            Outputting the labels for the legend
            gc.strokeText("Grade: " + entry.getKey() + "  Number of Students: " + getNumPerGrade().get(entry.getKey()), 675, rectPosition + 1.3 * (height + 10));
            startingAngle += entry.getValue();
            n += 1;
            height = 20 * n;
        }


//        Message in the case that no students match the selected criteria in the choice box selections...
        if (startingAngle == 0) {
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Times", 30));
            gc.fillText("THERE ARE NO MATCHES TO THIS CRITERIA...", 130, 100);
            gc.fillText("PLEASE SELECT AGAIN!", 130, 130);
            gc.setFont(Font.font("Times", 12));
        }
//        AddIndividualRows r = new AddIndividualRows();
//        r.addToStudentsTable();
    }


}
