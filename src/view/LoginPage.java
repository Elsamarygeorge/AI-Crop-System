package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginPage {

    private StackPane view;

    public LoginPage(Main main){

        Image logoImg = new Image(new java.io.File("images/logo.png").toURI().toString());
        ImageView logo = new ImageView(logoImg);

        logo.setFitHeight(250);
        logo.setPreserveRatio(true);

        Label title = new Label("FARMER LOGIN");
        title.getStyleClass().add("title");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {

            // 🔴 VALIDATION
            if(username.getText().trim().isEmpty() || password.getText().trim().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter both Username and Password.");
                alert.showAndWait();

                return;
            }

            // if fields are filled
            main.showDashboard();
        });

        VBox panel = new VBox(15, logo, title, username, password, loginBtn);
        panel.setAlignment(Pos.CENTER);
        panel.getStyleClass().add("feedback-panel");

        panel.setMaxWidth(600);
        panel.setPrefWidth(600);

        username.setMaxWidth(500);
        password.setMaxWidth(500);

        view = new StackPane(panel);
        view.setAlignment(Pos.CENTER);

        view.setStyle(
            "-fx-background-image: url('file:images/login_bg.jpg');" +
            "-fx-background-size: cover;" +
            "-fx-background-position: center center;");
    }

    public StackPane getView(){
        return view;
    }
}