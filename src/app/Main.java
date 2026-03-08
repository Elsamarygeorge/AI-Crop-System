package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

import view.LoginPage;
import view.DashboardPage;
import view.InputPage;
import view.OutputPage;
import view.FeedbackPage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene;   // ONE scene for entire app

    @Override
    public void start(Stage stage) {

        primaryStage = stage;

        // create scene only once
        scene = new Scene(new LoginPage(this).getView(), 900, 650);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("AI Crop Recommendation System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLoginPage() {
        scene.setRoot(new LoginPage(this).getView());
    }

    public void showDashboard(){
        scene.setRoot(new DashboardPage(this).getView());
    }

    public void showInputPage(){
        scene.setRoot(new InputPage(this).getView());
    }

    public void showOutputPage(List<String> results, String advisory){
        scene.setRoot(new OutputPage(this, results, advisory).getView());
    }

    public void showFeedbackPage(){
        scene.setRoot(new FeedbackPage(this).getView());
    }

    public static void main(String[] args){
        launch(args);
    }
}