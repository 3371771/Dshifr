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

    static void display (String err) {

        Stage window_error = new Stage();

        window_error.getIcons().add(new Image("icon_.jpg"));

        window_error.initModality(Modality.APPLICATION_MODAL);
        window_error.setTitle("Ошибка!!");
        window_error.setHeight(200);
        window_error.setWidth(750);

        Label label_error = new Label(err);
        label_error.setFont(Font.font("Courier New",17));

        Button button_no = new Button("ОЙ, я передумал");
        button_no.setStyle("-fx-base: #71DF89;");
        button_no.setFont(Font.font("Courier New",17));
        button_no.setOnAction((ActionEvent e) -> {
            window_error.close();
            AlertWindow.close();
        });

        Button button_yes = new Button("Ну и пусть!");
        button_yes.setStyle("-fx-base: #71DF89;");
        button_yes.setFont(Font.font("Courier New",17));
        button_yes.setOnAction((ActionEvent e) -> {
            window_error.close();
            AlertWindow.fun();
        });

        if (err.equals("Так сделать нельзя. Файл зашифрован другим алгоритмом!")){
            button_yes.setVisible(false);
        }

        ImageView imageView1 = new ImageView("atten_red.jpg");


        VBox v_layout_err = new VBox(15);
        v_layout_err.setAlignment(Pos.CENTER);
        v_layout_err.setPadding(new Insets(13));
        v_layout_err.getChildren().addAll(label_error,button_yes, button_no);

        HBox h_layout_err = new HBox(15);
        h_layout_err.setAlignment(Pos.CENTER);
        h_layout_err.setPadding(new Insets(13));
        h_layout_err.getChildren().addAll(imageView1, v_layout_err);

        Scene scene_err = new Scene(h_layout_err, 350, 220);

        window_error.setScene(scene_err);
        window_error.show();
    }
}
