package webp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final Button button = new Button();
        button.setText("버튼");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                button.setText("입력됨");
            }
        });
        BorderPane pane = new BorderPane();
        pane.setCenter(button);

        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Kangmin JavaFX");
        stage.show();
    }
}
