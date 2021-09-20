package webp;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {

    private String loadPath = "";
    private String targetPath = "";
    private int duration = 10;
    private TextField intervalTextField;
    private TextField durationTextField;
    private long time;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {

        HBox loadBox = setLoadPathBox(stage);
        loadBox.setAlignment(Pos.CENTER);
        HBox targetBox = setTargetPathBox(stage);
        targetBox.setAlignment(Pos.CENTER);
        final HBox durationBox = setDurationBox();
        durationBox.setAlignment(Pos.CENTER);
        HBox timeIntervalBox = setTimeIntervalBox();
        timeIntervalBox.setAlignment(Pos.CENTER);

        final Label waitLabel = new Label();
        final Button generateButton = new Button();
        generateButton.setText("생성");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (loadPath.isEmpty() || targetPath.isEmpty()) {
                    waitLabel.setText("파일 경로를 지정해주세요");
                } else {
                    waitLabel.setText("생성중...");
                    try {
                        if (!durationTextField.getText().isEmpty())
                            duration = Integer.parseInt(durationTextField.getText());
                        time = System.currentTimeMillis();

                        new Webp().encodeToAnimatedWebp(new File(loadPath), targetPath, duration, 8);

                        long end = System.currentTimeMillis();
                        waitLabel.setText("생성완료, 걸린시간: " + (end - time) / 1000.0 + "초");
                    } catch (Exception e) {
                        waitLabel.setText("에러발생");
                        e.printStackTrace();
                    }
                }
            }
        });
        HBox generateBox = new HBox();
        generateBox.getChildren().addAll(waitLabel, generateButton);
        generateBox.setAlignment(Pos.CENTER);

        VBox boxes = new VBox();
        boxes.getChildren().addAll(loadBox, targetBox, durationBox, timeIntervalBox, generateBox);
        boxes.setSpacing(8);

        BorderPane pane = new BorderPane();
        pane.setCenter(boxes);

        Scene scene = new Scene(pane, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Kangmin JavaFX");
        stage.show();
    }

    private HBox setTimeIntervalBox() {
        HBox box = new HBox();
        final Label label = new Label();
        label.setText("시간 간격 설정 0.x초 (default: 0.1)");
        label.setFont(new Font("또또사랑 OTF Medium", 12));
        intervalTextField = makeNumberTextField();
        box.getChildren().addAll(label, intervalTextField);
        return box;
    }

    private TextField makeNumberTextField() {
        final TextField textField = new TextField();
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        return textField;
    }

    private HBox setLoadPathBox(Stage stage) {
        HBox box = new HBox();
        final Label label = new Label();
        label.setText("변환할 동영상을 불러오세요 ");
        label.setFont(new Font("또또사랑 OTF Medium", 12));
        final Button button = setLoadButton(stage, label);
        box.getChildren().addAll(label, button);
        box.setSpacing(8);
        return box;
    }

    private HBox setTargetPathBox(Stage stage) {
        HBox box = new HBox();
        final Label label = new Label();
        label.setText("저장할 위치를 선택하세요 ");
        label.setFont(new Font("또또사랑 OTF Medium", 12));
        final Button button = setTargetButton(stage, label);
        box.getChildren().addAll(label, button);
        box.setSpacing(8);
        return box;
    }

    private HBox setDurationBox() {
        HBox box = new HBox();
        Label label = new Label();
        label.setText("webp 애니메이션 길이(s, default: 10) ");
        durationTextField = makeNumberTextField();
        box.getChildren().addAll(label, durationTextField);
        return box;
    }

    private Button setLoadButton(final Stage stage, final Label loadedLabel) {
        final Button button = new Button();
        button.setText("동영상 가져오기 ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
                loadPath = file.toURI().toString().replace("file:/", "");
                if (System.getProperty("os.name").contains("Windows")) {
                    loadPath = loadPath.replace("/", "\\");
                }
                loadedLabel.setText("가져온 동영상: " + loadPath);
            }
        });
        return button;
    }

    private Button setTargetButton(final Stage stage, final Label targetLabel) {
        final Button button = new Button();
        button.setText("저장 위치 설정 ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File directory = directoryChooser.showDialog(stage);
                targetPath = directory.toURI().toString().replace("file:/", "");
                if (System.getProperty("os.name").contains("Windows")) {
                    targetPath = targetPath.replace("/", "\\");
                }
                targetPath += "result.webp";
                targetLabel.setText("저장할 위치: " + targetPath);
            }
        });
        return button;
    }
}
