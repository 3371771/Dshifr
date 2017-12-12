import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main extends Application{

    public Main() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("D' Шифр");

        primaryStage.setOnCloseRequest(e -> Player.stopMusic());

        primaryStage.getIcons().add(new Image("icon_mini.jpg"));

        ImageView imageView = new ImageView("icon_.jpg");
        ImageView imageViewSky = new ImageView("fon.jpg");
        imageViewSky.setFitHeight(20);
        imageViewSky.setFitWidth(450);

        Label labelMain = new Label("Выберите алгоритм шифрования:");
        labelMain.setFont(Font.font("Courier New", 17));

        Label labelProgName = new Label("D' ШИФР");
        labelProgName.setFont(Font.font("Courier New", 18));
        labelProgName.setStyle("-fx-font-weight:bold;");
        labelProgName.setTextFill(Color.BLACK);

        Button buttonRsa = new Button("RSA");
        buttonRsa.setStyle("-fx-base: #71DF89; ");
        buttonRsa.setFont(Font.font("Courier New", 17));
        buttonRsa.setOnAction(e -> AlertWindow.display("RSA"));

        Button buttonDes = new Button("DES");
        buttonDes.setStyle("-fx-base: #71DF89;");
        buttonDes.setFont(Font.font("Courier New", 17));
        buttonDes.setOnAction(e -> AlertWindow.display("DES"));

        Button buttonAes = new Button("AES");
        buttonAes.setFont(Font.font("Courier New", 17));
        buttonAes.setStyle("-fx-base: #71DF89; ");
        buttonAes.setOnAction(e -> AlertWindow.display("AES"));

        Button buttonStopMario = new Button("Я не люблю Марио!");
        buttonStopMario.setFont(Font.font("Courier New", 12));
        buttonStopMario.setStyle("-fx-base: #71DF89; ");
        buttonStopMario.setOnAction(e -> stop());

        HBox buttonBox = new HBox(40);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(buttonAes, buttonDes, buttonRsa);

        VBox leftBox = new VBox(2);
        leftBox.setPadding(new Insets(0, 15, 0, 0));
        leftBox.getChildren().addAll(imageView, labelProgName);
        leftBox.setAlignment(Pos.TOP_CENTER);

        VBox centerBox = new VBox(15);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.getChildren().addAll(labelMain,  buttonBox);

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().addAll(buttonStopMario);


        //разметка главного окна
        BorderPane layoutMain = new BorderPane();
        layoutMain.setPadding(new Insets(20));
        //определение места отображения Боксов
        layoutMain.setLeft(leftBox);
        layoutMain.setCenter(centerBox);
        layoutMain.setBottom(bottomBox);
        //layoutMain.setTop(imageViewSky);

        //добавить новый имг с облаками

        //применяем разметку к первому окну
        Scene sceneMain = new Scene(layoutMain, 450, 180);

        //задания первого окна при открытии приложения
        primaryStage.setScene(sceneMain);
        primaryStage.show();

        // фон
        BackgroundImage myBI = new BackgroundImage(new Image("fon1.jpg", 450, 180, true, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        layoutMain.setBackground(new Background(myBI));
        play();
    }

    public void play () {
        Player.playMusic();
    }

    public void stop() {
        Player.stopMusic();
    }
}
