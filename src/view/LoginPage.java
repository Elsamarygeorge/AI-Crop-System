package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginPage {

    private VBox view;

    public LoginPage(Main main){

        Image logoImg = new Image("file:images/logo.jpg");
        ImageView logo = new ImageView(logoImg);

        logo.setFitHeight(80);
        logo.setFitWidth(80);

        Label title = new Label("FARMER LOGIN");

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");

        loginBtn.setOnAction(e -> {
            main.showDashboard();
        });

        view = new VBox(15,logo,title,username,password,loginBtn);

        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("panel");
    }

    public VBox getView(){
        return view;
    }
}