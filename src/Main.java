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


import java.io.IOException;
import javax.sound.sampled.*;




public class Main extends Application{




    public Main() throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException {
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setOnCloseRequest(e -> Player.stop_music());

        primaryStage.setTitle("D' Шифр");

        primaryStage.getIcons().add(new Image("icon_mini.jpg"));

        ImageView imageView = new ImageView("icon_.jpg");


        Label label_main = new Label("Выберите алгоритм шифрования:");
        label_main.setFont(Font.font("Courier New", 17));


        Label label_prog_name = new Label("D' ШИФР");
        label_prog_name.setFont(Font.font("Courier New", 18));
        label_prog_name.setStyle("-fx-font-weight:bold;");
        label_prog_name.setTextFill(Color.BLACK);


        Button button_rsa = new Button("RSA");
        button_rsa.setStyle("-fx-base: #71DF89; ");
        button_rsa.setFont(Font.font("Courier New", 15));
        button_rsa.setOnAction(e -> AlertWindow.display("Алгоритм RSA"));

        Button button_des = new Button("DES");
        button_des.setStyle("-fx-base: #71DF89;");
        button_des.setFont(Font.font("Courier New", 15));
        button_des.setOnAction(e -> AlertWindow.display("Алгоритм DES"));

        Button button_aes = new Button("AES");
        button_aes.setFont(Font.font("Courier New", 15));
        button_aes.setStyle("-fx-base: #71DF89; ");
        button_aes.setOnAction(e -> AlertWindow.display("Алгоритм AES"));

        Button button_stop_m = new Button("Я не люблю Марио!");
        button_stop_m.setFont(Font.font("Courier New", 15));
        button_stop_m.setStyle("-fx-base: #71DF89; ");
        button_stop_m.setOnAction(e -> stop1());


        HBox button_box = new HBox(40);
        button_box.setPadding(new Insets(10, 0, 0, 0));
        button_box.setAlignment(Pos.CENTER);
        button_box.getChildren().addAll(button_aes, button_des, button_rsa);

        VBox left_box = new VBox(2);
        left_box.setPadding(new Insets(0, 15, 0, 0));
        left_box.getChildren().addAll(imageView, label_prog_name);
        left_box.setAlignment(Pos.TOP_CENTER);

        VBox center_box = new VBox(15);
        center_box.setAlignment(Pos.TOP_CENTER);
        center_box.getChildren().addAll(label_main, button_box);

        HBox bottom_box = new HBox(10);
        bottom_box.setAlignment(Pos.BOTTOM_RIGHT);
        bottom_box.getChildren().addAll(button_stop_m);

        //разметка главного окна
        BorderPane layout_main = new BorderPane();
        layout_main.setPadding(new Insets(20));
        //определение места отображения Боксов
        layout_main.setLeft(left_box);
        layout_main.setCenter(center_box);
        layout_main.setBottom(bottom_box);

        //применяем разметку к первому окну
        Scene scene_main = new Scene(layout_main, 450, 180);

        //подключение стиля
        //scene_main.getStylesheets().add((getClass().getResource("style.css")).toExternalForm());

        //задания первого окна при открытии приложения
        primaryStage.setScene(scene_main);
        primaryStage.show();

        BackgroundImage myBI = new BackgroundImage(new Image("fon.jpg", 450, 180, true, false),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        layout_main.setBackground(new Background(myBI));


        play();

    }

    public void play () {
        Player.play_music();
    }

    private void stop1() {
        Player.stop_music();
    }

}
