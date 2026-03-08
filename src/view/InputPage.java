package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import app.Main;
import backend.CropRecommendationSystem;

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
            f.getStyleClass().add("text-field");
        }

        // -------- GRID --------
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        String[] labels = {
                "Nitrogen",
                "Phosphorus",
                "Potassium",
                "Temperature",
                "Humidity",
                "pH",
                "Rainfall"
        };

        for (int i = 0; i < fields.length; i++) {

            Label l = new Label(labels[i]);
            l.getStyleClass().add("input-label");

            grid.add(l, 0, i);
            grid.add(fields[i], 1, i);
        }

        // -------- BUTTON --------
        Button submit = new Button("Get Crop Recommendation 🌱");
        submit.getStyleClass().add("button");

        submit.setOnAction(e -> {

            // 🔴 CHECK FOR EMPTY FIELDS
            for (TextField field : fields) {
                if (field.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all input fields before proceeding.");
                    alert.showAndWait();
                    return;
                }
            }

            try {
                // Parse input values
                double n = Double.parseDouble(nitrogen.getText());
                double p = Double.parseDouble(phosphorus.getText());
                double k = Double.parseDouble(potassium.getText());
                double temp = Double.parseDouble(temperature.getText());
                double hum = Double.parseDouble(humidity.getText());
                double phv = Double.parseDouble(ph.getText());
                double rain = Double.parseDouble(rainfall.getText());

                // Call backend
                CropRecommendationSystem model = new CropRecommendationSystem();
                List<String> results = model.getRecommendation(n, p, k, temp, hum, phv, rain);

                // Prepare climate advisory for top 3 crops
                StringBuilder advisoryBuilder = new StringBuilder();
                for (String r : results) {
                    String cropName = r.split("-")[0].trim();
                    cropName = cropName.substring(cropName.indexOf(".") + 1).trim();
                    advisoryBuilder.append(
                            cropName.substring(0, 1).toUpperCase() + cropName.substring(1) + ":\n"
                    );
                    advisoryBuilder.append(model.getClimateAdvice(cropName, temp, hum, rain)).append("\n\n");
                }

                main.showOutputPage(results, advisoryBuilder.toString().trim());

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numeric values for all fields.");
                alert.showAndWait();
            }
        });

        // -------- CARD --------
        VBox card = new VBox(25, title, grid, submit);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(800);
        card.getStyleClass().add("crop-card");

        // -------- ROOT --------
        view = new StackPane(card);
        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("inputpage");
    }

    public StackPane getView() {
        return view;
    }
}