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

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class CloseDialog {

    static void display(String title_close) {
        Stage window_close = new Stage();
        if (title_close.equals("Файл зашифрован!")) {
            window_close.getIcons().add(new Image("closet.jpg"));
        } else {
            window_close.getIcons().add(new Image("open.jpg"));
        }

        window_close.initModality(Modality.APPLICATION_MODAL);
        window_close.setTitle("Что сделать?");
        window_close.setHeight(190);
        window_close.setWidth(350);

        window_close.setOnCloseRequest(e -> {
            close_req();
            e.consume();
        });

        Label label_how_close = new Label(title_close);
        label_how_close.setFont(Font.font("Courier New", 17));

        ImageView imageView = new ImageView("atten.jpg");

        Button btn_no_save = new Button("Не сохранять");
        btn_no_save.setStyle("-fx-base: #71DF89; ");
        btn_no_save.setFont(Font.font("Courier New", 17));
        btn_no_save.setOnAction(e -> {
            window_close.close();
            AlertWindow.close();
        });

        Button btn_save = new Button("Сохранить");
        btn_save.setStyle("-fx-base: #71DF89; ");
        btn_save.setFont(Font.font("Courier New", 17));

        btn_save.setOnAction(e -> {
            if (AlertWindow.to_do.equals("zash")) {
                switch (AlertWindow.title1) {
                    case "AES":
                        try {
                            Controller.AES(1);
                        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "DES":
                        try {
                            Controller.DES(1);
                        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException | ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "RSA":
                        try {
                            Controller.RSA(1);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    default: break;
                }

            } else

            {
                switch (AlertWindow.title1) {
                    case  "AES":
                        try {
                            Controller.AES(2);
                        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "DES":
                        try {
                            Controller.DES(2);
                        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException | ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "RSA":
                        try {
                            Controller.RSA(2);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    default: break;
                }
            }
            label_how_close.setText("Сохранено!");
            btn_save.setVisible(false);
            btn_no_save.setText("Понятно!");
            window_close.setTitle("Результат");
        });

        VBox v_layout_close = new VBox(15);
        v_layout_close.getChildren().addAll(label_how_close, btn_save, btn_no_save);
        v_layout_close.setAlignment(Pos.CENTER);

        HBox h_layout_close = new HBox(15);
        h_layout_close.getChildren().addAll(imageView, v_layout_close);
        h_layout_close.setAlignment(Pos.CENTER);

        Scene scene_close = new Scene(h_layout_close);

        window_close.setScene(scene_close);
        window_close.show();
    }

    //окно ВНИМАНИЕ!!
    private static void close_req() {

        Stage window_do_not = new Stage();
        window_do_not.initModality(Modality.APPLICATION_MODAL);
        window_do_not.setTitle("ВНИМАНИЕ!!");
        window_do_not.setMinHeight(170);
        window_do_not.setMinWidth(170);

        window_do_not.getIcons().add(new Image("icon_.jpg"));

        String adwise = randomiz();

        Label do_not_do_this = new Label(adwise);
        do_not_do_this.setFont(Font.font("Courier New", 17));

        ImageView imageView1 = new ImageView("atten_red.jpg");

        Button ok_button = new Button("Ok");
        ok_button.setStyle("-fx-base: #71DF89; ");
        ok_button.setFont(Font.font("Courier New", 17));
        ok_button.setOnAction(e -> window_do_not.close());

        VBox v_do_not_layout = new VBox(15);
        v_do_not_layout.setPadding(new Insets(13));
        v_do_not_layout.getChildren().addAll(do_not_do_this, ok_button);
        v_do_not_layout.setAlignment(Pos.CENTER);

        HBox h_do_not_layout = new HBox(15);
        h_do_not_layout.getChildren().addAll(imageView1, v_do_not_layout);
        h_do_not_layout.setPadding(new Insets(13));
        h_do_not_layout.setAlignment(Pos.CENTER);

        Scene scene_do_not = new Scene(h_do_not_layout);

        window_do_not.setScene(scene_do_not);
        window_do_not.show();
    }

    //разные варианты аттеншена :))
    private static String randomiz() {
        int a = 1; // Начальное значение диапазона - "от"
        int b = 4; // Конечное значение диапазона - "до"
        int random_number = a + (int) (Math.random() * b);

        String adwise = "";

        if (random_number == 1) {
            adwise = "Сперва сохрани! Или нет, я ж просто программа, не мне тебе указывать";
        } else if (random_number == 2) {
            adwise = "Так нельзя, нужно что-то выбрать!";
        } else if (random_number == 3) {
            adwise = "Выбери, что делать!";
        }  else  {
            adwise = "A A A! А сохранить?";
        }

        return adwise;
    }
}
