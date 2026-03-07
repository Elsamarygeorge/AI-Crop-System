package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import app.Main;

public class InputPage {

    private StackPane view;

    public InputPage(Main main) {

        // -------- TITLE --------
        Label title = new Label("🌾 AI Crop Recommendation System 🌾");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setTextFill(Color.DARKGREEN);

        // -------- INPUT FIELDS --------
        TextField nitrogen = new TextField();
        nitrogen.setPromptText("Nitrogen (N)");

        TextField phosphorus = new TextField();
        phosphorus.setPromptText("Phosphorus (P)");

        TextField potassium = new TextField();
        potassium.setPromptText("Potassium (K)");

        TextField temperature = new TextField();
        temperature.setPromptText("Temperature (°C)");

        TextField humidity = new TextField();
        humidity.setPromptText("Humidity (%)");

        TextField ph = new TextField();
        ph.setPromptText("Soil pH");

        TextField rainfall = new TextField();
        rainfall.setPromptText("Rainfall (mm)");

        TextField[] fields = {
                nitrogen, phosphorus, potassium,
                temperature, humidity, ph, rainfall
        };

        for (TextField f : fields) {
            f.setMaxWidth(220);
            f.setStyle(
                    "-fx-background-radius: 6;" +
                    "-fx-padding: 8;" +
                    "-fx-font-size: 13px;"
            );
        }

        // -------- GRID FOR INPUTS --------
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Nitrogen"), 0, 0);
        grid.add(nitrogen, 1, 0);

        grid.add(new Label("Phosphorus"), 0, 1);
        grid.add(phosphorus, 1, 1);

        grid.add(new Label("Potassium"), 0, 2);
        grid.add(potassium, 1, 2);

        grid.add(new Label("Temperature"), 0, 3);
        grid.add(temperature, 1, 3);

        grid.add(new Label("Humidity"), 0, 4);
        grid.add(humidity, 1, 4);

        grid.add(new Label("pH"), 0, 5);
        grid.add(ph, 1, 5);

        grid.add(new Label("Rainfall"), 0, 6);
        grid.add(rainfall, 1, 6);

        // -------- BUTTON --------
        Button submit = new Button("Get Crop Recommendation 🌱");

        submit.setStyle(
                "-fx-background-color: linear-gradient(#2e8b57,#3cb371);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 25 10 25;"
        );

        // Hover effect
        submit.setOnMouseEntered(e ->
                submit.setStyle(
                        "-fx-background-color:#228B22;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8;" +
                        "-fx-padding:10 25 10 25;"
                )
        );

        submit.setOnMouseExited(e ->
                submit.setStyle(
                        "-fx-background-color: linear-gradient(#2e8b57,#3cb371);" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:14px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8;" +
                        "-fx-padding:10 25 10 25;"
                )
        );

        // -------- BUTTON ACTION (SIMULATED RESULTS) --------
        submit.setOnAction(e -> {

            List<String> results = new ArrayList<>();

            results.add("1. Rice - 92.13%");
            results.add("2. Maize - 5.02%");
            results.add("3. Chickpea - 2.85%");

            String advisory =
                    "Temperature is slightly higher than ideal for some crops.\n" +
                    "Humidity levels are suitable.\n" +
                    "Rainfall may need monitoring for optimal yield.";

            main.showOutputPage(results, advisory);

        });

        // -------- CARD LAYOUT --------
        VBox card = new VBox(25);
        card.getChildren().addAll(title, grid, submit);
        card.setAlignment(Pos.CENTER);

        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.95);" +
                "-fx-padding: 40;" +
                "-fx-background-radius: 12;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15,0,0,4);"
        );

        // -------- BACKGROUND IMAGE --------
        Image image = new Image("file:images/farm_.jpg");

        BackgroundSize size = new BackgroundSize(
                100,100,true,true,true,false
        );

        BackgroundImage bg = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
        );

        StackPane root = new StackPane();
        root.setBackground(new Background(bg));
        root.getChildren().add(card);

        view = root;
    }

    public StackPane getView() {
        return view;
    }
}