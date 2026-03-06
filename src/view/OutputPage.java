package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class OutputPage {

    private VBox view;

    public OutputPage(Main main,String result){

        Label output = new Label(result);

        Button feedbackBtn = new Button("Give Feedback");

        Button dashboardBtn = new Button("Back to Dashboard");

        feedbackBtn.setOnAction(e -> main.showFeedbackPage());

        dashboardBtn.setOnAction(e -> main.showDashboard());

        view = new VBox(20,output,feedbackBtn,dashboardBtn);

        view.setAlignment(Pos.CENTER);

        view.getStyleClass().add("panel");
    }

    public VBox getView(){
        return view;
    }
}