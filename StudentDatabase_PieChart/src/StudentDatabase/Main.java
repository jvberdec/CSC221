package StudentDatabase;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {

    int canvasWidth;
    int canvasHeight;

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> studentID = new ArrayList<>();
        ArrayList<String> firstN = new ArrayList<String>();
        ArrayList<String> lastN = new ArrayList<String>();
        ArrayList<String> s = new ArrayList<String>();

        ArrayList<String> courseID = new ArrayList<String>();
        ArrayList<String> courseTitle = new ArrayList<String>();
        ArrayList<String> department = new ArrayList<String>();

        ArrayList<String> classCode = new ArrayList<>();
        ArrayList<String> courseID_classes = new ArrayList<String>();
        ArrayList<Integer> studentID_classes = new ArrayList<>();
        ArrayList<Integer> year = new ArrayList<>();
        ArrayList<String> semester = new ArrayList<String>();
        ArrayList<String> gpa = new ArrayList<String>();

        File studentsTable = new File("C:\\Users\\julia\\Desktop\\Student_Database\\Students_real.xlsx");
        FileInputStream excel1 = new FileInputStream(studentsTable);
        XSSFWorkbook workbook1 = new XSSFWorkbook(excel1);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);

        Iterator<Row> rowIt1 = sheet1.iterator();

        while (rowIt1.hasNext()) {
            Row row = rowIt1.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    cell.setCellType(CellType.NUMERIC);
                    studentID.add((int) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    firstN.add(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 2) {
                    lastN.add(cell.getStringCellValue());
                } else {
                    s.add(cell.getStringCellValue());
                }

            }
        }


        File coursesTable = new File("C:\\Users\\julia\\Desktop\\Student_Database\\Courses.xlsx");
        FileInputStream excel2 = new FileInputStream(coursesTable);
        XSSFWorkbook workbook2 = new XSSFWorkbook(excel2);
        XSSFSheet sheet2 = workbook2.getSheetAt(0);


        Iterator<Row> rowIt2 = sheet2.iterator();

        while (rowIt2.hasNext()) {
            Row row = rowIt2.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    courseID.add(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    courseTitle.add(cell.getStringCellValue());
                } else {
                    department.add(cell.getStringCellValue());
                }
            }
        }


        File classesTable = new File("C:\\Users\\julia\\Desktop\\Student_Database\\Classes.xlsx");
        FileInputStream excel3 = new FileInputStream(classesTable);
        XSSFWorkbook workbook3 = new XSSFWorkbook(excel3);
        XSSFSheet sheet3 = workbook3.getSheetAt(0);


        Iterator<Row> rowIt3 = sheet3.iterator();

        while (rowIt3.hasNext()) {
            Row row = rowIt3.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    classCode.add(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    courseID_classes.add(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 2) {
                    cell.setCellType(CellType.NUMERIC);
                    studentID_classes.add((int) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 3) {
                    cell.setCellType(CellType.NUMERIC);
                    year.add((int) cell.getNumericCellValue());
                }
                if (cell.getColumnIndex() == 4) {
                    semester.add(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 5) {
                    gpa.add(cell.getStringCellValue());
                }
            }
        }


        SetTables c = new SetTables();
        c.createStudentsTable();
        c.createCoursesTable();
        c.createClassesTable();

        PopulateTables p = new PopulateTables();
        p.populateStudentsTable(studentID, firstN, lastN, s);
        p.populateCoursesTable(courseID, courseTitle, department);
        p.populateClassesTable(classCode, courseID_classes, studentID_classes, year, semester, gpa);

        Display d = new Display();
        d.getDisplay();

        launch(args);

        c.dropAllTables();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FINAL PROJECT: STUDENT DATABASE");

        String courses[] = {"ENGL 250", "CSC 103", "CSC 211", "CHEM 330"};
        String semesters[] = {"Fall", "Spring"};
        Integer years[] = {2017, 2018, 2019};


        VBox v = new VBox();
        HBox h = new HBox();
        Text txt = new Text("Please enter the course, semester and year to be displayed: ");
        txt.setFont(Font.font(18));


        Button bGenerate = new Button("Generate!");
        ChoiceBox<String> chooseCourse = new ChoiceBox(FXCollections.observableArrayList(courses));
        chooseCourse.setValue("CSC 211");
        ChoiceBox<String> chooseSemester = new ChoiceBox(FXCollections.observableArrayList(semesters));
        chooseSemester.setValue("Fall");
        ChoiceBox<Integer> chooseYear = new ChoiceBox(FXCollections.observableArrayList(years));
        chooseYear.setValue(2019);
        ToolBar tool = new ToolBar(txt, chooseCourse, chooseSemester, chooseYear, bGenerate);
        tool.setPadding(new Insets(10, 30, 10, 95));

        Text t1 = new Text("Would you like to insert an entry?");
        t1.setFont(Font.font(18));
        ToolBar tool1 = new ToolBar(t1);
        tool1.setPadding(new Insets(10, 30, 10, 95));
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        VBox v1 = new VBox();
        HBox hStudents = new HBox();
        TextField field1 = new TextField("studentID");
        TextField field2 = new TextField("firstName");
        TextField field3 = new TextField("lastName");
        TextField field4 = new TextField("M or F");
        hStudents.getChildren().addAll(field1, field2, field3, field4);
        Button bStudents = new Button("Add to Students Table");

        HBox hCourses = new HBox();
        TextField field5 = new TextField("CourseID");
        TextField field6 = new TextField("Course Description");
        TextField field7 = new TextField("Department");
        hCourses.getChildren().addAll(field5, field6, field7);
        Button bCourses = new Button("Add to Courses Table");

        HBox hClasses = new HBox();
        HBox hClasses2 = new HBox();
        TextField field8 = new TextField("Course Code");
        TextField field9 = new TextField("CourseID");
        TextField field10 = new TextField("StudentID");
        TextField field11 = new TextField("Year");
        TextField field12 = new TextField("Semester");
        TextField field13 = new TextField("GPA");
        hClasses.getChildren().addAll(field8, field9, field10);
        hClasses2.getChildren().addAll(field11, field12, field13);
        Button bClasses = new Button("Add to Classes Table");


        v1.getChildren().addAll(tool1, hStudents, bStudents, hCourses, bCourses, hClasses, hClasses2, bClasses);

        canvasWidth = 900;
        canvasHeight = 700;
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        v.getChildren().addAll(tool, canvas);
        v1.setSpacing(10);
        h.getChildren().addAll(v, v1);

        Scene s = new Scene(h, 1500, 700);
        primaryStage.setScene(s);


        bGenerate.setOnAction(e -> {

            gc.clearRect(10, 10, 900, 700);
            String a = getCourse(chooseCourse);
            String d = getSemester(chooseSemester);
            int c = getYear(chooseYear);


            PieChart pie = new PieChart();
            pie.retrieveCanvasSize(canvasWidth, canvasHeight);
            pie.setCourse(a);
            pie.setSemester(d);
            pie.setYear(c);

            try {
                pie.draw(gc);
//
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        });

        bStudents.setOnAction(e ->{

            AddIndividualRows add = new AddIndividualRows();
            add.addToStudentsTable(Integer.parseInt(field1.getText()),field2.getText(), field3.getText(), field4.getText());
//            System.out.println(field1.getText());
            field1.setText("studentID");
            field2.setText("firstName");
            field3.setText("lastName");
            field4.setText("sex");

        });

        bCourses.setOnAction(e ->{

            AddIndividualRows add = new AddIndividualRows();
            add.addToCoursesTable(field5.getText(), field6.getText(), field7.getText());
            field5.setText("courseID");
            field6.setText("Course Description");
            field7.setText("Department");

        });

        bClasses.setOnAction(e ->{
            AddIndividualRows add = new AddIndividualRows();
            add.addToClassesTable(field8.getText(), field9.getText(), Integer.parseInt(field10.getText()), Integer.parseInt(field11.getText()), field12.getText(), field13.getText());
            field8.setText("courseCode");
            field9.setText("courseID");
            field10.setText("studentID");
            field11.setText("Year");
            field12.setText("Semester");
            field13.setText("GPA");
        });


        primaryStage.show();
    }


    private String getCourse(ChoiceBox<String> chooseCourse) {
        return chooseCourse.getValue();
    }

    private String getSemester(ChoiceBox<String> chooseSemester) {
        return chooseSemester.getValue();
    }

    private int getYear(ChoiceBox<Integer> chooseYear) {
        return chooseYear.getValue();
    }


}
