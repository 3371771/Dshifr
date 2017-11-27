import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CloseDialog {

    static String n;


    public static void display(String title_close) {
        Stage window_close = new Stage();
        if (title_close.equals("Файл зашифрован!")) {
            window_close.getIcons().add(new Image("closet.jpg"));
        } else {
            window_close.getIcons().add(new Image("open.jpg"));
        }

        window_close.initModality(Modality.APPLICATION_MODAL);
        window_close.setTitle("Что сделать?");
        window_close.setHeight(180);
        window_close.setWidth(300);

        window_close.setOnCloseRequest(e -> {
            close_req();
            e.consume();
        });

        Label label_how_close = new Label(title_close);
        label_how_close.setFont(Font.font("Courier New", 17));

        Button btn_no_save = new Button("Не сохранять");
        btn_no_save.setStyle("-fx-base: #71DF89; ");
        btn_no_save.setFont(Font.font("Courier New", 15));
        btn_no_save.setOnAction(e -> {
            window_close.close();
            AlertWindow.close();
        });

        Button btn_save = new Button("Сохранить");
        btn_save.setStyle("-fx-base: #71DF89; ");
        btn_save.setFont(Font.font("Courier New", 15));
//        //btn_save.setOnAction(e-> JOptionPane.showMessageDialog(null, "Сохранение", "Файл сохранен" , JOptionPane.ERROR_MESSAGE));
        btn_save.setOnAction(e -> {
            if (AlertWindow.to_do.equals("zash")) {
                if (AlertWindow.title1.equals("Алгоритм AES")) {
                    try {
                        Controller.AES_zash(1);
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException e1) {
                        e1.printStackTrace();
                    }
                } else if (AlertWindow.title1.equals("Алгоритм DES")) {

                } else if (AlertWindow.title1.equals("Алгоритм RSA")){

                }

            } else {
                if (AlertWindow.title1.equals("Алгоритм AES")) {
                    try {
                        Controller.AES_zash(2);
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException | InvalidKeyException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            label_how_close.setText("Сохранено!");
            btn_save.setVisible(false);
            btn_no_save.setText("Понятно!");
        });

        VBox layout_close = new VBox(15);
        layout_close.getChildren().addAll(label_how_close, btn_save, btn_no_save);
        layout_close.setAlignment(Pos.CENTER);

        Scene scene_close = new Scene(layout_close);

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

        String adwise = randomiz();

        Label do_not_do_this = new Label(adwise);
        do_not_do_this.setFont(Font.font("Courier New", 17));

        Button ok_button = new Button("Ok");
        ok_button.setStyle("-fx-base: #71DF89; ");
        ok_button.setFont(Font.font("Courier New", 15));
        ok_button.setOnAction(e -> window_do_not.close());

        VBox do_not_layout = new VBox(15);
        do_not_layout.setPadding(new Insets(13));
        do_not_layout.getChildren().addAll(do_not_do_this, ok_button);
        do_not_layout.setAlignment(Pos.CENTER);

        Scene scene_do_not = new Scene(do_not_layout);

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
