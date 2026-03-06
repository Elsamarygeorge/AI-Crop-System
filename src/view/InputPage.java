package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InputPage {

    private VBox view;

    public InputPage(Main main){

        TextField nitrogen = new TextField();
        nitrogen.setPromptText("Nitrogen (N)");

        TextField temperature = new TextField();
        temperature.setPromptText("Temperature (°C)");

        TextField humidity = new TextField();
        humidity.setPromptText("Humidity (%)");

        Button submit = new Button("Get Recommendation");

        submit.setOnAction(e -> {

            if(nitrogen.getText().isEmpty() ||
               temperature.getText().isEmpty() ||
               humidity.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setHeaderText("Missing Data");

                alert.setContentText("Please fill all fields.");

                alert.show();

            }else{

                String result = "Recommended Crop: Rice 🌾";

                main.showOutputPage(result);
            }
        });

        VBox panel = new VBox(15,nitrogen,temperature,humidity,submit);

        panel.setAlignment(Pos.CENTER);

        panel.getStyleClass().add("panel");

        view = new VBox(panel);

        view.setAlignment(Pos.CENTER);

        view.getStyleClass().add("inputpage");
    }

    public VBox getView(){
        return view;
    }
}