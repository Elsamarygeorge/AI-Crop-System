package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FeedbackPage {

    private StackPane view;

    public FeedbackPage(Main main) {

        // ===== TITLE =====
        Label title = new Label("DID THIS RECOMMENDATION HELP YOU?");
        title.getStyleClass().add("title");

        // ===== OPTIONS =====
        RadioButton yes = new RadioButton("Yes");
        RadioButton no = new RadioButton("No");
        RadioButton partial = new RadioButton("Partially");

        ToggleGroup group = new ToggleGroup();
        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        partial.setToggleGroup(group);

        HBox options = new HBox(40, yes, no, partial);
        options.setAlignment(Pos.CENTER);

        // ===== COMMENTS =====
        TextArea comments = new TextArea();
        comments.setPromptText("Additional comments (optional)...");
        comments.setPrefRowCount(4);
        comments.setWrapText(true);
        comments.setMaxWidth(400);
        comments.getStyleClass().add("text-field");

        // ===== BUTTONS =====
        Button submit = new Button("Submit");
        Button back = new Button("Back");

        submit.getStyleClass().add("button");
        back.getStyleClass().add("button");

        HBox buttonBox = new HBox(20, back, submit);
        buttonBox.setAlignment(Pos.CENTER);

        // ===== BUTTON ACTIONS =====
        back.setOnAction(e -> main.showDashboard());

        submit.setOnAction(e -> {
            Toggle selectedToggle = group.getSelectedToggle();

            if (selectedToggle == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Feedback Required");
                alert.setHeaderText(null);
                alert.setContentText("Please select an option before submitting.");
                alert.showAndWait();
            } else {
                RadioButton selected = (RadioButton) selectedToggle;
                String feedback = selected.getText();
                String extraComments = comments.getText().trim();

                System.out.println("User Feedback: " + feedback);

                if (!extraComments.isEmpty()) {
                    System.out.println("Comments: " + extraComments);
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thank You!");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for your feedback!");
                alert.showAndWait();

                main.showDashboard();
            }
        });

        // ===== MAIN LAYOUT =====
        VBox panel = new VBox(25, title, options, comments, buttonBox);
        panel.setAlignment(Pos.CENTER);
        panel.setMaxWidth(900);
        panel.getStyleClass().add("feedback-panel");

        view = new StackPane(panel);
        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("feedbackpage");
    }

    public StackPane getView() {
        return view;
    }
}