import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class Game_Client extends Application {
    public ArrayList<String> questions = new ArrayList();
    public HashMap<String,String[]> answers = new HashMap();
    public HashMap<String,String> answer = new HashMap();
    public Label result;
    public boolean going = true;
    public BorderPane borderPane;
    public Button correct;
    public boolean guessed = false;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        questions.add("How old are you");
        questions.add("What is your name");
        questions.add("Where do you live");
        String[] live = {"California","Texas","Vermont","Minnesota"};
        String[] name = {"Bob","Ted","Derek","Bill","Ron"};
        String[] age = {"12","14","16","11","25","22"};
        answers.put("Where do you live",live);
        answers.put("What is your name",name);
        answers.put("How old are you",age);
        answer.put("Where do you live","Missouri");
        answer.put("What is your name","Hunter");
        answer.put("How old are you","21");

            Random rand = new Random();
            String q = questions.get(rand.nextInt(questions.size()));

            GridPane gridPane = new GridPane();
            int one = rand.nextInt(2);
            int two = rand.nextInt(2);
            String[] as = answers.get(q);
            String[][] board = new String[2][2];
            gridPane.setAlignment(CENTER);
            gridPane.setVgap(80);
            gridPane.setHgap(80);
            Button correct = new Button(answer.get(q));
            correct.setPrefSize(150, 30);
            Button[] incorrect = new Button[3];
            int count = 0;
            int btns = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++, count++) {
                    if (one == i && two == j) {
                        board[i][j] = answer.get(q);
                        gridPane.add(correct, i, j);
                    } else {
                        board[i][j] = as[count];
                        incorrect[btns] = new Button(as[count]);
                        incorrect[btns].setPrefSize(150, 30);
                        gridPane.add(incorrect[btns], i, j);
                        btns++;
                        //gridPane.add(new Label(board[i][j]),i,j);
                    }
                }
            }


            Label que = new Label(q);
            que.setAlignment(TOP_CENTER);
            que.setFont(Font.font("Courier", FontWeight.BOLD, 24));

            BorderPane.setAlignment(que, CENTER);
            BorderPane.setAlignment(gridPane, CENTER);
            BorderPane borderPane = new BorderPane();

            borderPane.setTop(que);
            borderPane.setCenter(gridPane);

            correct.setOnAction(e -> {
                result = new Label("Correct");
                BorderPane.setAlignment(result, CENTER);
                borderPane.setBottom(result);
                setBoard();
            });
            for (int i = 0; i < 3; i++) {
                incorrect[i].setOnAction(e -> {
                    result = new Label("Incorrect");
                    BorderPane.setAlignment(result, CENTER);
                    borderPane.setBottom(result);
                    setBoard();
                });
            }
            correct.setOnAction(e -> {
                result = new Label("Correct");
                BorderPane.setAlignment(result, CENTER);
                borderPane.setBottom(result);
            });
            setBoard();
            //while(true){
            //}



            Scene scene = new Scene(borderPane, 500, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sports Trivia");

            primaryStage.show();

        }

        public void setBoard(){
            Random rand = new Random();
            int num = rand.nextInt(questions.size());
            String q = questions.get(num);

            GridPane gridPane = new GridPane();
            int one = rand.nextInt(2);
            int two = rand.nextInt(2);
            String[] as = answers.get(q);
            String[][] board = new String[2][2];
            gridPane.setAlignment(CENTER);
            gridPane.setVgap(80);
            gridPane.setHgap(80);
            correct = new Button(answer.get(q));
            correct.setPrefSize(150, 30);
            Button[] incorrect = new Button[3];
            int count = 0;
            int btns = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++, count++) {
                    if (one == i && two == j) {
                        board[i][j] = answer.get(q);
                        gridPane.add(correct, i, j);
                    } else {
                        board[i][j] = as[count];
                        incorrect[btns] = new Button(as[count]);
                        incorrect[btns].setPrefSize(150, 30);
                        gridPane.add(incorrect[btns], i, j);
                        btns++;
                        //gridPane.add(new Label(board[i][j]),i,j);
                    }
                }
            }


            Label que = new Label(q);
            que.setAlignment(TOP_CENTER);
            que.setFont(Font.font("Courier", FontWeight.BOLD, 24));

            BorderPane.setAlignment(que, CENTER);
            BorderPane.setAlignment(gridPane, CENTER);
            borderPane = new BorderPane();

            borderPane.setTop(que);
            borderPane.setCenter(gridPane);

            correct.setOnAction(e -> {
                result = new Label("Correct");
                BorderPane.setAlignment(result, CENTER);
                borderPane.setBottom(result);
                guessed=true;
                System.out.println("Hello");
            });
            for (int i = 0; i < 3; i++) {
                incorrect[i].setOnAction(e -> {
                    result = new Label("Incorrect");
                    BorderPane.setAlignment(result, CENTER);
                    borderPane.setBottom(result);
                    System.out.println("Hi");
                });
            }
            Scene scene = new Scene(borderPane, 500, 500);

        }
   // }
}
