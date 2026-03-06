package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FeedbackPage {

    private VBox view;

    public FeedbackPage(Main main){

        Label label = new Label("Did this recommendation help?");

        RadioButton yes = new RadioButton("Yes");
        RadioButton no = new RadioButton("No");
        RadioButton partial = new RadioButton("Partially");

        ToggleGroup group = new ToggleGroup();

        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        partial.setToggleGroup(group);

        Button submit = new Button("Submit");

        Button back = new Button("Back");

        back.setOnAction(e -> main.showDashboard());

        VBox panel = new VBox(15,label,yes,no,partial,submit,back);

        panel.setAlignment(Pos.CENTER);

        panel.getStyleClass().add("panel");

        view = new VBox(panel);

        view.setAlignment(Pos.CENTER);

        view.getStyleClass().add("feedbackpage");
    }

    public VBox getView(){
        return view;
    }
}