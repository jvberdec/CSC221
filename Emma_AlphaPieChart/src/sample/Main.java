package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox v = new VBox();
        Text txt = new Text("Please enter the number of characters to be displayed on the pie chart and then press enter.");
        txt.setFont(Font.font(18));

        Button b = new Button("Generate!");
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        choiceBox.setValue(0);

        v.getChildren().add(txt);
        v.getChildren().add(choiceBox);
        v.getChildren().add(b);

        primaryStage.setTitle("CSC 22100: Exercise 3 by Julia Berdecia");
        primaryStage.setScene(new Scene(v,1000,800));

        primaryStage.show();
        Canvas canvas = new Canvas(1000.0D, 800);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        v.getChildren().add(canvas);



        b.setOnAction(e -> {
            gc.clearRect(10,10,1000,800);

            int n = getChoice(choiceBox);
            System.out.println(n);
            PieChart abc = new PieChart();
            abc.setCharNumber(n);

            File text = new File("Emma.txt");

            Scanner input = null;
            try {
                input = new Scanner(text);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            while (input.hasNext()) {
            String userInput = input.next();
            abc.add(userInput);
        }

        System.out.println(abc);



        abc.draw(gc);

        });

        primaryStage.show();

    }

    private int getChoice(ChoiceBox<Integer> choiceBox){
        int i = choiceBox.getValue();
        return i;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
