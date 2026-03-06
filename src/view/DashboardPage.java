package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DashboardPage {

    private VBox view;

    public DashboardPage(Main main){

        Button newRec = new Button("New Recommendation");

        Button feedback = new Button("Give Feedback");

        Button logout = new Button("Logout");

        newRec.setOnAction(e -> main.showInputPage());

        feedback.setOnAction(e -> main.showFeedbackPage());

        logout.setOnAction(e -> main.showLoginPage());

        VBox panel = new VBox(20,newRec,feedback,logout);

        panel.setAlignment(Pos.CENTER);
        panel.getStyleClass().add("panel");

        view = new VBox(panel);

        view.setAlignment(Pos.CENTER);

        view.getStyleClass().add("dashboard");
    }

    public VBox getView(){
        return view;
    }
}