package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import app.Main;

public class DashboardPage {

    private StackPane view;

    public DashboardPage(Main main) {

        // Title
        Label title = new Label("AI CROP RECOMMENDATION SYSTEM");
        title.getStyleClass().add("dashboard-title");

        Label subtitle = new Label("Smart Farming Assistant");
        subtitle.getStyleClass().add("dashboard-subtitle");

        // Buttons
        Button newRec = new Button("🌱 New Recommendation");
        Button feedback = new Button("💬 Give Feedback");
        Button logout = new Button("🚪 Logout");

        // Button actions
        newRec.setOnAction(e -> main.showInputPage());
        feedback.setOnAction(e -> main.showFeedbackPage());
        logout.setOnAction(e -> main.showLoginPage());

        // Panel layout
        VBox panel = new VBox(30, title, subtitle, newRec, feedback, logout);
        panel.setAlignment(Pos.CENTER);
        panel.getStyleClass().add("panel");

        // Root container
        view = new StackPane(panel);
        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("dashboard");
    }

    public StackPane getView() {
        return view;
    }
}