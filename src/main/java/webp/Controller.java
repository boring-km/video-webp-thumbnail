package webp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txtField;
    @FXML
    private Label resultLabel;
    private String inputValue;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        inputValue = txtField.getText();
        txtField.setText("");
        resultLabel.setText(inputValue);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
