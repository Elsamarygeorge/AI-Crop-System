package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import app.Main;

import java.util.ArrayList;
import java.util.List;

public class InputPage {

    private StackPane view;

    public InputPage(Main main) {

        // -------- TITLE --------
        Label title = new Label("ENTER YOUR SOIL & CLIMATE DETAILS");
        title.getStyleClass().add("input-title");

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
            f.getStyleClass().add("text-field"); // Use CSS class
        }

        // -------- GRID FOR INPUTS --------
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        // Add labels and fields
        String[] labels = {"Nitrogen", "Phosphorus", "Potassium", "Temperature", "Humidity", "pH", "Rainfall"};
        for (int i = 0; i < fields.length; i++) {
            Label l = new Label(labels[i]);
            l.getStyleClass().add("label");  // CSS label styling
            grid.add(l, 0, i);
            grid.add(fields[i], 1, i);
        }

        // -------- BUTTON --------
        Button submit = new Button("Get Crop Recommendation 🌱");
        submit.getStyleClass().add("button"); // CSS button styling

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
        VBox card = new VBox(25, title, grid, submit);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(800);           // Wider panel to avoid clipping
        card.getStyleClass().add("crop-card");

        // -------- ROOT LAYOUT WITH BACKGROUND --------
        view = new StackPane(card);
        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("inputpage"); // Background image class
    }

    public StackPane getView() {
        return view;
    }
}