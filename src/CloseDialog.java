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

class CloseDialog {

    private static Boolean fileSave = false;

    static void display(String titleClose) {
        Stage windowClose = new Stage();
        if (titleClose.equals("Файл зашифрован!")) {
            windowClose.getIcons().add(new Image("closet.jpg"));
        } else {
            windowClose.getIcons().add(new Image("open.jpg"));
        }

        windowClose.initModality(Modality.APPLICATION_MODAL);
        windowClose.setTitle("Что сделать?");
        windowClose.setHeight(190);
        windowClose.setWidth(350);

        windowClose.setOnCloseRequest(e -> {
            if (!fileSave) {
                close_req();
                e.consume();
            } else windowClose.close();
        });

        Label labelHowClose = new Label(titleClose);
        labelHowClose.setFont(Font.font("Courier New", 17));

        ImageView imageView = new ImageView("atten.jpg");

        Button buttonNoSave = new Button("Не сохранять");
        buttonNoSave.setStyle("-fx-base: #71DF89; ");
        buttonNoSave.setFont(Font.font("Courier New", 17));
        buttonNoSave.setOnAction(e -> {
            windowClose.close();
            AlertWindow.close();
        });

        Button buttonSave = new Button("Сохранить");
        buttonSave.setStyle("-fx-base: #71DF89; ");
        buttonSave.setFont(Font.font("Courier New", 17));

        buttonSave.setOnAction(e -> {
            fileSave = true;
            if (AlertWindow.toDo.equals("encrypt")) {
                switch (AlertWindow.titleOut) {
                    case "AES":
                        try {
                            Controller.AES(1);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "DES":
                        try {
                            Controller.DES(1);
                        } catch (Exception e1) {
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
                { switch (AlertWindow.titleOut) {
                    case  "AES":
                        try {
                            Controller.AES(2);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case "DES":
                        try {
                            Controller.DES(2);
                        } catch (Exception e1) {
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
            //ToDo: сюда ожидание на секунды 2
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            labelHowClose.setText("Сохранено!");
            buttonSave.setVisible(false);
            buttonNoSave.setText("Понятно!");
            windowClose.setTitle("Результат");
        });

        VBox vLayoutClose = new VBox(15);
        vLayoutClose.getChildren().addAll(labelHowClose, buttonSave, buttonNoSave);
        vLayoutClose.setAlignment(Pos.CENTER);

        HBox hLayoutClose = new HBox(15);
        hLayoutClose.getChildren().addAll(imageView, vLayoutClose);
        hLayoutClose.setAlignment(Pos.CENTER);

        Scene sceneClose = new Scene(hLayoutClose);

        windowClose.setScene(sceneClose);
        windowClose.show();
    }

    //окно ВНИМАНИЕ!!
    private static void close_req() {

        Stage windowDoNot = new Stage();
        windowDoNot.initModality(Modality.APPLICATION_MODAL);
        windowDoNot.setTitle("ВНИМАНИЕ!!");
        windowDoNot.setMinHeight(170);
        windowDoNot.setMinWidth(170);

        windowDoNot.getIcons().add(new Image("icon_.jpg"));

        String advice = random();

        Label doNotDoThis = new Label(advice);
        doNotDoThis.setFont(Font.font("Courier New", 17));

        ImageView imageViewAttRed = new ImageView("atten_red.jpg");

        Button buttonOk = new Button("Ok");
        buttonOk.setStyle("-fx-base: #71DF89; ");
        buttonOk.setFont(Font.font("Courier New", 17));
        buttonOk.setOnAction(e -> windowDoNot.close());

        VBox vDoNotLayout = new VBox(15);
        vDoNotLayout.setPadding(new Insets(13));
        vDoNotLayout.getChildren().addAll(doNotDoThis, buttonOk);
        vDoNotLayout.setAlignment(Pos.CENTER);

        HBox hDoNotLayout = new HBox(15);
        hDoNotLayout.getChildren().addAll(imageViewAttRed, vDoNotLayout);
        hDoNotLayout.setPadding(new Insets(13));
        hDoNotLayout.setAlignment(Pos.CENTER);

        Scene scene_do_not = new Scene(hDoNotLayout);

        windowDoNot.setScene(scene_do_not);
        windowDoNot.show();
    }

    //разные варианты аттеншена :))
    private static String random() {
        int a = 1; // Начальное значение диапазона - "от"
        int b = 4; // Конечное значение диапазона - "до"
        int randomNumber = a + (int) (Math.random() * b);

        String advice;

        if (randomNumber == 1) {
            advice = "Сперва сохрани! Или нет, я ж просто программа, не мне тебе указывать";
        } else if (randomNumber == 2) {
            advice = "Так нельзя, нужно что-то выбрать!";
        } else if (randomNumber == 3) {
            advice = "Выбери, что делать!";
        }  else  {
            advice = "A A A! А сохранить?";
        }
        return advice;
    }
}
