package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.List;

public class OutputPage {

    private VBox root;

    public OutputPage(Main main, List<String> results, String advisory) {

        Label title = new Label("TOP 3 CROP RECOMMENDATIONS");
        title.getStyleClass().add("output-title");

        // Container for crop cards
        HBox cropContainer = new HBox(40);
        cropContainer.setAlignment(Pos.CENTER);

        for(String r : results){

            // Crop Image
            Image img = new Image("file:images/" + r.toLowerCase() + ".jpg");
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(140);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);

            // Crop Name
            Label cropLabel = new Label(r);
            cropLabel.getStyleClass().add("crop-name");

            // Card layout
            VBox cropCard = new VBox(10);
            cropCard.setAlignment(Pos.CENTER);
            cropCard.getChildren().addAll(imageView, cropLabel);
            cropCard.getStyleClass().add("crop-card");

            cropContainer.getChildren().add(cropCard);
        }

        Label advisoryTitle = new Label("CLIMATE ADVISORY");
        advisoryTitle.getStyleClass().add("advisory-title");

        Label advisoryText = new Label(advisory);
        advisoryText.getStyleClass().add("advisory-text");
        advisoryText.setWrapText(true);
        advisoryText.setMaxWidth(500);

        Button backBtn = new Button("Back to Dashboard");

        backBtn.setOnAction(e -> main.showDashboard());

        root = new VBox(35,
                title,
                cropContainer,
                advisoryTitle,
                advisoryText,
                backBtn
        );

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("panel");
        root.getStyleClass().add("outputpage");
    }

    public Parent getView() {
        return root;
    }
}