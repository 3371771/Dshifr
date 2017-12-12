import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BadFile {

    static Button buttonYes;
    static Stage windowError = new Stage();

    static void display (String err) {

        windowError.getIcons().add(new Image("icon_.jpg"));

        windowError.initModality(Modality.APPLICATION_MODAL);
        windowError.setTitle("Ошибка!!");
        windowError.setHeight(200);
        windowError.setWidth(750);

        Label labelError = new Label(err);
        labelError.setFont(Font.font("Courier New",17));

        Button buttonNo = new Button("ОЙ, я передумал");
        buttonNo.setStyle("-fx-base: #71DF89;");
        buttonNo.setFont(Font.font("Courier New",17));
        buttonNo.setOnAction((ActionEvent e) -> {
            windowError.close();
            AlertWindow.close();
        });

        buttonYes = new Button("Ну и пусть!");
        buttonYes.setStyle("-fx-base: #71DF89;");
        buttonYes.setFont(Font.font("Courier New",17));
        buttonYes.setOnAction((ActionEvent e) -> {
            windowError.close();
            AlertWindow.fun();
        });
//        if (err.equals("Так сделать нельзя. Файл зашифрован другим алгоритмом!")){
//            buttonYes.setVisible(false);
//        }
        ImageView imageView1 = new ImageView("atten_red.jpg");

        VBox vLayoutErr = new VBox(15);
        vLayoutErr.setAlignment(Pos.CENTER);
        vLayoutErr.setPadding(new Insets(13));
        vLayoutErr.getChildren().addAll(labelError,buttonYes, buttonNo);

        HBox hLayoutErr = new HBox(15);
        hLayoutErr.setAlignment(Pos.CENTER);
        hLayoutErr.setPadding(new Insets(13));
        hLayoutErr.getChildren().addAll(imageView1, vLayoutErr);

        Scene sceneErr = new Scene(hLayoutErr, 350, 220);

        windowError.setScene(sceneErr);
        windowError.show();
    }
}
