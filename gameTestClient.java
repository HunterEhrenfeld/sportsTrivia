import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class gameTestClient extends Application {
    public ArrayList<String> questions = new ArrayList();
    public HashMap<String,String[]> answers = new HashMap();
    public HashMap<String,String> answer = new HashMap();
    public Label result;
    public Button correct;
    public int score = 0;
    public int total = 0;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws InterruptedException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/quiz","javaproject","javaproject");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from questions");
        while(resultSet.next()){
            questions.add(resultSet.getString(1));
            String[] temp = new String[3];
            temp[0]=resultSet.getString(3);
            temp[1]=resultSet.getString(4);
            temp[2]=resultSet.getString(5);
            answers.put(resultSet.getString(1),temp);
            answer.put(resultSet.getString(1),resultSet.getString(2));
        }
        connection.close();

        startGame(primaryStage);

//            Random rand = new Random();
//            String q = questions.get(rand.nextInt(questions.size()));
//
//
//            int one = rand.nextInt(2);
//            int two = rand.nextInt(2);
//            String[] as = answers.get(q);
//            String[][] board = new String[2][2];
//            gridPane.setAlignment(CENTER);
//            gridPane.setVgap(80);
//            gridPane.setHgap(80);
//            Button correct = new Button(answer.get(q));
//            correct.setPrefSize(150, 30);
//            Button[] incorrect = new Button[3];
//            int count = 0;
//            int btns = 0;
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < 2; j++, count++) {
//                    if (one == i && two == j) {
//                        board[i][j] = answer.get(q);
//                        gridPane.add(correct, i, j);
//                    } else {
//                        board[i][j] = as[count];
//                        incorrect[btns] = new Button(as[count]);
//                        incorrect[btns].setPrefSize(150, 30);
//                        gridPane.add(incorrect[btns], i, j);
//                        btns++;
//                        //gridPane.add(new Label(board[i][j]),i,j);
//                    }
//                }
//            }
//
//
//            Label que = new Label(q);
//            que.setAlignment(TOP_CENTER);
//            que.setFont(Font.font("Courier", FontWeight.BOLD, 24));
//
//            BorderPane.setAlignment(que, CENTER);
//            BorderPane.setAlignment(gridPane, CENTER);
//
//            borderPane.setTop(que);
//            borderPane.setCenter(gridPane);
//
//            for (int i = 0; i < 3; i++) {
//                incorrect[i].setOnAction(e -> {
//                    resetBoard();
//                    result = new Label("Incorrect");
//                    BorderPane.setAlignment(result, CENTER);
//                    borderPane.setBottom(result);
//                });
//            }
//            correct.setOnAction(e -> {
//                resetBoard();
//                result = new Label("Correct");
//                BorderPane.setAlignment(result, CENTER);
//                borderPane.setBottom(result);
//
//            });
//            //while(true){
//            //}
//
//
//
//            Scene scene = new Scene(borderPane, 500, 500);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Sports Trivia");
//
//            primaryStage.show();

    }

    private void newGame(Stage primaryStage) {
        startGame(primaryStage);
    }

    public void startGame(Stage primaryStage){
        Random rand = new Random();
        String q = questions.get(rand.nextInt(questions.size()));

        GridPane gridPane = new GridPane();
        //sets the coordinates of the correct answer
        int one = rand.nextInt(2);
        int two = rand.nextInt(2);

        String[] as = answers.get(q);

        //creates board
        String[][] board = new String[2][2];

        gridPane.setAlignment(CENTER);
        gridPane.setVgap(80);
        gridPane.setHgap(80);
        Button correct = new Button(answer.get(q));
        correct.setPrefSize(150, 30);
        Button[] incorrect = new Button[3];
        //count used to index incorrect buttons
        int count = 0;
        int btns = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (one == i && two == j) {
                    board[i][j] = answer.get(q);
                    gridPane.add(correct, i, j);
                } else {
                    System.out.println(count + " " + as.length);
                    board[i][j] = as[count];
                    incorrect[btns] = new Button(as[count]);
                    incorrect[btns].setPrefSize(150, 30);
                    gridPane.add(incorrect[btns], i, j);
                    btns++;
                    count++;
                }
            }
        }
        //tracks total score
        gridPane.add(new Label("Score: " + score + "/" + total),0,2);
        correct.setOnAction(e->{
            add(score);
            inc(total);
            newGame(primaryStage);
        });
        for(int i = 0; i < 3; i++){
            incorrect[i].setOnAction(e->{
                inc(total);
                newGame(primaryStage);
            });
        }


        Label que = new Label(q);
        que.setAlignment(TOP_CENTER);
        que.setFont(Font.font("Courier", FontWeight.BOLD, 24));
        que.setWrapText(true);

        BorderPane.setAlignment(que, CENTER);
        BorderPane.setAlignment(gridPane, CENTER);
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(que);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sports Trivia");

        primaryStage.show();
    }

    private void add(int score) {
        this.score+=1;
    }
    private void inc(int total){
        this.total+=1;
    }

}
