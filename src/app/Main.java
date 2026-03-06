package app;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.LoginPage;
import view.DashboardPage;
import view.InputPage;
import view.OutputPage;
import view.FeedbackPage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {

        primaryStage = stage;
        showLoginPage();
    }

    public void showLoginPage() {

        LoginPage login = new LoginPage(this);

        Scene scene = new Scene(login.getView(),600,400);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("AI Crop Recommendation System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showDashboard(){

        DashboardPage dashboard = new DashboardPage(this);

        Scene scene = new Scene(dashboard.getView(),700,500);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
    }

    public void showInputPage(){

        InputPage input = new InputPage(this);

        Scene scene = new Scene(input.getView(),700,500);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
    }

    public void showOutputPage(String result){

        OutputPage output = new OutputPage(this, result);

        Scene scene = new Scene(output.getView(),700,500);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
    }

    public void showFeedbackPage(){

        FeedbackPage feedback = new FeedbackPage(this);

        Scene scene = new Scene(feedback.getView(),600,400);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
    }

    public static void main(String[] args){
        launch(args);
    }
}