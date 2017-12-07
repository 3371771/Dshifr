
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


class AlertWindow {
    private static Scene scene_todo;
    private static Label file_name_label;
    private static Stage window_choose;
    private static String file_name_1;
    static String to_do;
    static File file;
    static String title1;
    private static String err;

    static void display(String title){
        window_choose = new Stage();
        window_choose.initModality(Modality.APPLICATION_MODAL);
        window_choose.setTitle(title);
        title1 = title;
        window_choose.setHeight(200);
        window_choose.setWidth(700);

        window_choose.getIcons().add(new Image("icon_mini.jpg"));

        ImageView imageView = new ImageView("question.jpg");

        Label label_choose = new Label("С каким файлом будем работать?");
        label_choose.setFont(Font.font("Courier New",17));

        Button button_choose = new Button("Выбор файла");
        button_choose.setStyle("-fx-base: #71DF89; ");
        button_choose.setFont(Font.font("Courier New",15));
        button_choose.setOnAction(e-> {
            try {
                //получаем путь к открываемому файлу для отображения
                file_name_1 = hndlOpenFile().getName();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            file_name_label.setText("Вы выбрали: " + file_name_1);
        window_choose.setScene(scene_todo);});

//        Button serch_file = new Button("...");
//        serch_file.setStyle("-fx-base: #b6e7c9; ");
//        serch_file.setFont(Font.font("Courier New",15));


        VBox v_layout_choose = new VBox(15);
        v_layout_choose.setPadding(new Insets(13));
        v_layout_choose.getChildren().addAll(label_choose, button_choose);
        v_layout_choose.setAlignment(Pos.CENTER);
       // layout_choose.setStyle("-fx-backround-color: #000000");

        HBox h_layout_choose = new HBox(15);
        h_layout_choose.setPadding(new Insets(13));
        h_layout_choose.getChildren().addAll(imageView, v_layout_choose);
        h_layout_choose.setAlignment(Pos.CENTER);


        Scene scene_choose = new Scene(h_layout_choose);
        window_choose.setScene(scene_choose);
        window_choose.show();

//разметка и логика окна действия с файлом

        Label label_todo = new Label("Что сделать с этим файлом?");
        label_todo.setFont(Font.font("Courier New",17));


        Button button_todo_zash = new Button("Зашифровать");
        button_todo_zash.setStyle("-fx-base: #71DF89;");
        button_todo_zash.setFont(Font.font("Courier New",17));
        button_todo_zash.setOnAction(e -> {


                            to_do = "zash";

                    CloseDialog.display("Файл зашифрован!");
                    window_choose.close();
                    }
                );

        Button button_todo_rash = new Button("Расшифровать");
        button_todo_rash.setStyle("-fx-base: #71DF89;");
        button_todo_rash.setFont(Font.font("Courier New",17));
        button_todo_rash.setOnAction((ActionEvent e) -> {

            //сюда проверку на то ,чем зашифрован
            //окно вы восклицательным знаком + ОК + отмена
            //и зашифрован ли вообще (по названию)
            //-файл заишфрован другим алгоритмом. Получится белебирда, но я могу. Хотите?
            //-файл не зашифрован. Получится белебирда, но я могу. Хотите?

            if (!file_name_1.contains("_зашифрован")) {
                System.out.println("Файл не зашифрован. При расшифровке будет фигня!");
                err = "Файл не зашифрован. При расшифровке будет фигня!";
                BadFile.display(err);
            } else if (file_name_1.contains("расшифрован")) {
                err = "Файл уже был расшифрован. При расшифровке может выйти фигня!";
                BadFile.display(err);
            } else if (!file_name_1.contains(title)) {
                System.out.println(title);
                System.out.println(file_name_1);
                err = "Так сделать нельзя. Файл зашифрован другим алгоритмом!";
                BadFile.display(err);
            } else fun();

//            switch (title) {
//                case "Алгоритм AES":
//                    System.out.println("запуск AES");
//                    to_do = "rassh";
//                    break;
//                case "Алгоритм DES":
//                    System.out.println("запуск DES");
//                    to_do = "rassh";
//                    break;
//                case "Алгоритм RSA":
//                    System.out.println("запуск RSA");
//                    break;
//            }
//            CloseDialog.display("Файл расшифрован!");
        });

        file_name_label = new Label();
        file_name_label.setFont(Font.font("Courier New",15));
        file_name_label.setStyle("-fx-font-weight:bold;");

        ImageView imageView1 = new ImageView("question.jpg");

        VBox v_layout_todo = new VBox(15);
        v_layout_todo.setAlignment(Pos.CENTER);
        v_layout_todo.setPadding(new Insets(13));
        v_layout_todo.getChildren().addAll(file_name_label, label_todo, button_todo_rash, button_todo_zash);

        HBox h_layout_todo = new HBox(15);
        h_layout_todo.setAlignment(Pos.CENTER);
        h_layout_todo.setPadding(new Insets(13));
        h_layout_todo.getChildren().addAll(imageView1, v_layout_todo);


        scene_todo = new Scene(h_layout_todo, 350, 220);
    }

    static void close() {
        window_choose.close();
    }

    private static File hndlOpenFile() throws IOException  {
        //String file_name = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        FileChooser.ExtensionFilter extFilter =
               new FileChooser.ExtensionFilter("Все типы файлов (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(window_choose);
//        if (file != null) {
//           // file_name = file.getAbsolutePath();
//        }
        return file;
    }

    static void fun() {
        to_do = "rassh";
        CloseDialog.display("Файл расшифрован!");
    }

}
