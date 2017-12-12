
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
import java.security.NoSuchAlgorithmException;


class AlertWindow {
    private static Scene sceneTodo;
    private static Label fileNameLabel;
    private static Stage windowChoose;
    private static String fileName;
    static String toDo;
    static File file;
    static String titleOut;
    private static String err;

    static void display(String title) {
        windowChoose = new Stage();
        windowChoose.initModality(Modality.APPLICATION_MODAL);
        windowChoose.setTitle(title);
        titleOut = title;
        //задаем размер окна
        windowChoose.setHeight(200);
        windowChoose.setWidth(700);
        //задаем иконку окна
        windowChoose.getIcons().add(new Image("icon_mini.jpg"));

        //задаем картинку для отображения
        ImageView imageView = new ImageView("question.jpg");

        //объявляем надпись и задаем текст стиль и шрифт
        Label labelChoose = new Label("С каким файлом будем работать?");
        labelChoose.setFont(Font.font("Courier New",17));

        //объявляем кнопку и задаем текст цвет и шрифт
        Button buttonChoose = new Button("Выбор файла");
        buttonChoose.setStyle("-fx-base: #71DF89; ");
        buttonChoose.setFont(Font.font("Courier New",15));
        //объявляем действие для кнопки
        buttonChoose.setOnAction(e-> {
            try {
                //получаем имя выбранного файла для отображения
                fileName = openFile().getName();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            // передаем полученное имя в новую сцену
            fileNameLabel.setText("Вы выбрали: " + fileName);
            //задаем для окна новую сцену
            windowChoose.setScene(sceneTodo);});

        //объявляем кнопку и задаем текст цвет и шрифт
        Button buttonKayRsaGen = new Button("Сгенерировать ключи");
        buttonKayRsaGen.setStyle("-fx-base: #71DF89; ");
        buttonKayRsaGen.setFont(Font.font("Courier New",15));
        buttonKayRsaGen.setOnAction(e -> {
            try {
                //генерация ключей
                Controller.RsaKayGen();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        //проверка: если алгоритм RSA, то показываем кнопку генерации ключей
        if (!title.equals("RSA")) {
            buttonKayRsaGen.setVisible(false);
        }

        //собираем элементы в боксы и распределяем их там
        VBox vLayoutChoose = new VBox(15);
        vLayoutChoose.setPadding(new Insets(13));
        vLayoutChoose.getChildren().addAll(labelChoose, buttonChoose, buttonKayRsaGen);
        vLayoutChoose.setAlignment(Pos.CENTER);

        HBox hLayoutChoose = new HBox(15);
        hLayoutChoose.setPadding(new Insets(13));
        hLayoutChoose.getChildren().addAll(imageView, vLayoutChoose);
        hLayoutChoose.setAlignment(Pos.CENTER);

        //привязываем бокс с сцене, а сцену к окну и показываем
        Scene sceneChoose = new Scene(hLayoutChoose);
        windowChoose.setScene(sceneChoose);
        windowChoose.show();

        //разметка и логика окна действия с файлом
        Label labelTodo = new Label("Что сделать с этим файлом?");
        labelTodo.setFont(Font.font("Courier New",17));

        Button buttonTodoEncrypt = new Button("Зашифровать");
        buttonTodoEncrypt.setStyle("-fx-base: #71DF89;");
        buttonTodoEncrypt.setFont(Font.font("Courier New",17));
        buttonTodoEncrypt.setOnAction(e -> {
            if (title.equals("RSA") && (AlertWindow.file.length()>501)) {
                    BadFile.display("Такой большой файл я зашифровать не смогу :( До 501б максимум");
                    BadFile.buttonYes.setVisible(false);
                    BadFile.windowError.setWidth(850);
            } else {
            toDo = "encrypt";
            CloseDialog.display("Файл зашифрован!");
            windowChoose.close(); }
        });

        Button buttonTodoDecrypt = new Button("Расшифровать");
        buttonTodoDecrypt.setStyle("-fx-base: #71DF89;");
        buttonTodoDecrypt.setFont(Font.font("Courier New",17));
        buttonTodoDecrypt.setOnAction((ActionEvent e) -> {

            //сюда проверку на то ,чем зашифрован
            //и зашифрован ли вообще

            if (!fileName.contains("_зашифрован")) {
                System.out.println("Файл не зашифрован. При расшифровке будет фигня!");
                err = "Файл не зашифрован. При расшифровке будет фигня!";
                BadFile.display(err);
            } else if (fileName.contains("расшифрован")) {
                err = "Файл уже был расшифрован. При расшифровке может выйти фигня!";
                BadFile.display(err);
            } else if (!fileName.contains(title)) {
                err = "Так сделать нельзя. Файл зашифрован другим алгоритмом!";
                BadFile.display(err);
                BadFile.buttonYes.setVisible(false);
            } else fun();
        });

        fileNameLabel = new Label();
        fileNameLabel.setFont(Font.font("Courier New",15));
        fileNameLabel.setStyle("-fx-font-weight:bold;");

        ImageView imageView1 = new ImageView("question.jpg");

        VBox vLayoutTodo = new VBox(15);
        vLayoutTodo.setAlignment(Pos.CENTER);
        vLayoutTodo.setPadding(new Insets(13));
        vLayoutTodo.getChildren().addAll(fileNameLabel, labelTodo, buttonTodoEncrypt, buttonTodoDecrypt);

        HBox hLayoutTodo = new HBox(15);
        hLayoutTodo.setAlignment(Pos.CENTER);
        hLayoutTodo.setPadding(new Insets(13));
        hLayoutTodo.getChildren().addAll(imageView1, vLayoutTodo);

        sceneTodo = new Scene(hLayoutTodo, 350, 220);
    }

    private static File openFile() throws IOException  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        FileChooser.ExtensionFilter extFilter =
               new FileChooser.ExtensionFilter("Все типы файлов (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(windowChoose);
        return file;
    }

    static void fun () {
        toDo = "decrypt";
        CloseDialog.display("Файл расшифрован!");
    }

    static void close() {
        windowChoose.close();
    }
}
